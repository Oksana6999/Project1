package WeatherApp.repository;

import WeatherApp.Enum.Entity.WeatherData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DataBaseRepository {
    boolean saveWeatherData(WeatherData weatherData) throws SQLException;
    List<WeatherData> getAllSaveData() throws IOException, SQLException;
    void closeConnection();
}
