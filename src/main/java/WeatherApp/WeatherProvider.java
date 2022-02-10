package WeatherApp;

import WeatherApp.Enum.Entity.WeatherData;
import WeatherApp.Enum.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface WeatherProvider {
    public void getWeather (Periods periods) throws IOException, SQLException;
    List<WeatherData> getAllFromDb() throws IOException, SQLException;

}
