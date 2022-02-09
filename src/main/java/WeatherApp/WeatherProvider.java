package WeatherApp;

import java.io.IOException;

public interface WeatherProvider {
    public void getWeather (Periods periods) throws IOException;
}
