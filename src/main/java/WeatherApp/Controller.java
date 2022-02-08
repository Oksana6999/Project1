package WeatherApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
WeatherProvider weatherProvider = new AccuWeatherProvider();
Map <Integer, Functionality> variantResult = new HashMap<>();

public Controller () {
    variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
    variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAY);
}

public void onUserInput (String input) throws IOException {
    int command = Integer.parseInt(input);
    if (!variantResult.containsKey(command)) {
        throw new IOException("There is no command in command-key" + command);
    }

    switch (variantResult.get(command)) {
        case GET_CURRENT_WEATHER:
            getCurrentWeather();
            break;
        case GET_WEATHER_IN_NEXT_5_DAY:
            getWeatherIn5Day();
            break;
    }

}

public void getCurrentWeather() throws IOException {
    weatherProvider.getWeather(Periods.NOW);

}

public void getWeatherIn5Day () {
    throw  new RuntimeException ("Implements h/w");
}
}
