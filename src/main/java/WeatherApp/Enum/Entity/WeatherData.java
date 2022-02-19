package WeatherApp.Enum.Entity;

public class WeatherData {
    private String city;
    private String localDate;
    private String dayText;
    private String nightText;
    private double minTemperature;
    private double maxTemperature;



    public WeatherData (){
    }

    public WeatherData (String city, String localDate, String dayText, String nightText,
                        double maxTemperature, double minTemperature) {
        this.city = city;
        this.localDate = localDate;
        this.dayText = dayText;
        this.nightText = nightText;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getCity() {
        return city;
    }

    public String getNightText() {
        return nightText;
    }

    public String getDayText() {
        return dayText;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String getLocalDate() {
        return localDate;
    }


    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", localDate='" + localDate + '\'' +
                ", dayText='" + dayText + '\'' +
                ", nightText='" + nightText + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                '}';
    }
}
