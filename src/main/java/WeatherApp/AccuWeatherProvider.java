package WeatherApp;

import WeatherApp.Enum.Entity.WeatherData;
import WeatherApp.Enum.Periods;
import WeatherApp.dto.WeatherResponse;
import WeatherApp.repository.DataBaseRepository;
import WeatherApp.repository.DataBaseRepositorySQLite;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuWeatherProvider implements WeatherProvider{

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getAPI_KEY();
    private static final String FORECAST = "forecasts";
    private static final String FORECAST_PERIOD = "5 day";
    private static final String FORECAST_TYPE = "daily";
    private static final ObjectMapper responseMapper = new ObjectMapper();

    private DataBaseRepository repository = new DataBaseRepositorySQLite();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void getWeather(Periods periods) throws IOException, SQLException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String responseList = client.newCall(request).execute().toString();

            List<WeatherResponse> weatherResponseList = responseMapper.readValue(responseList, new
                    TypeReference<List<WeatherResponse>>() {
                    });
            WeatherResponse weather = weatherResponseList.get(0);
            System.out.println("Сейчас в городе " + ApplicationGlobalState.getInstance().getSelectedCity() +
                    " температура " + weather.getTemperature().getMetric().getValue() + " C, и " + weather.getWeatherText() + " .");


        }


        if (periods.equals(Periods.FIVE_DAYS)) {
            cityKey = detectCityKey();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String responseList = client.newCall(request).execute().body().string();

            int firstIndexBody = responseList.indexOf("[{\"Date\"");
            int lastIndexBody = responseList.lastIndexOf("}");
            responseList = responseList.substring(firstIndexBody, lastIndexBody);

            List<WeatherResponse> weatherResponseList = responseMapper.readValue(responseList, new TypeReference<List<WeatherResponse>>() {
            });

            for (WeatherResponse weather : weatherResponseList) {
                System.out.println("В городе " + ApplicationGlobalState.getInstance().getSelectedCity() + " на следующую дату " + weather.getDate().substring(0, 10) +
                        " ожидается такая погода: Минимальная температура " + weather.getTemperature().getMinimum().getValue() + "°С. Максимальная температура " +
                        weather.getTemperature().getMaximum().getValue() + "°С. Днём - " + weather.getDay().getIconPhrase() + ". Ночью - " + weather.getNight().getIconPhrase() + ".");

                WeatherData weatherData = new WeatherData(ApplicationGlobalState.getInstance().getSelectedCity(),
                        weather.getDate().substring(0, 10), weather.getDay().getIconPhrase(), weather.getNight().getIconPhrase(),
                        castFloatToDouble(weather.getTemperature().getMinimum().getValue()), castFloatToDouble(weather.getTemperature().getMaximum().getValue()));

                repository.saveWeatherData(weatherData);
            }

        }
        if (periods.equals(Periods.BASE)) {
            getAllFromDb();
        }

        if (periods.equals(Periods.ZERO)) {
            exitApp();
        }
    }


    private void exitApp() {
        System.out.println("Завершаю работу");
        repository.closeConnection();
        System.exit(0);
    }

    private double castFloatToDouble(float value) {
        return (double) value;

    }

    @Override
    public List<WeatherData> getAllFromDb() throws SQLException, IOException {
        List<WeatherData> weatherDataList = repository.getAllSaveData();
        for (WeatherData weatherData : weatherDataList) {
            System.out.println(weatherData);
        }
        return weatherDataList;
    }


    public String detectCityKey() throws IOException {
            String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

            HttpUrl detectLocationURL = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment("locations")
                    .addPathSegment(API_VERSION)
                    .addPathSegments("cities")
                    .addPathSegments("autocomplete")
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("q", selectedCity)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(detectLocationURL)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Невозможно прочесть информацию о городе" +
                        "Код ответа сервера: " + response.code() + "Тело ответа: " +
                        response.body().string());
            }
            String jsonResponse = response.body().string();
            System.out.println("Ищу город " + selectedCity);

            if (objectMapper.readTree(jsonResponse).size() > 0) {
                String cityName = objectMapper.readTree(jsonResponse).get(0).at("/localizedName").asText();
                String countryName = objectMapper.readTree(jsonResponse).get(0).at("/country/localizedName").asText();
                System.out.println("Город: " + cityName + "в стране " + countryName);
            } else throw new IOException("Server returns 0 cities");
            return objectMapper.readTree(jsonResponse).get(0).at("/key").asText();
        }

    public String trimBrackets(JsonNode string){
        int lastIndexBody = string.toString().lastIndexOf("\"");
        String newString = string.toString().substring(1, lastIndexBody);
        return newString;
    }

}
