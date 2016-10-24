package information;

import java.time.OffsetTime;

public class Airport
{
    private String abbreviation;
    private String cityName;

    private String[] weather;
    private int weatherRequestCounter = 0;

    private OffsetTime timeDelay;

    protected Airport(String abbreviation, String cityName)
    {
        this.abbreviation = abbreviation;
        this.cityName = cityName;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public String getCityName()
    {
        return cityName;
    }

    public String getWeather()
    {
        // make sure we have some weather
        if (this.weather.length < 1)
            return null;

        // return current weather
        int currentWeatherIndex = this.weatherRequestCounter % this.weather.length;
        String weather = this.weather[currentWeatherIndex];
        String temperature = this.weather[currentWeatherIndex + 1];

        this.weatherRequestCounter += 2;

        return weather + "," + temperature;
    }

    protected void setWeather(String[] weather)
    {
        this.weather = weather;
    }

    public OffsetTime getTimeDelay()
    {
        return timeDelay;
    }

    protected void setTimeDelay(OffsetTime timeDelay)
    {
        this.timeDelay = timeDelay;
    }

    @Override
    public String toString()
    {
        return cityName + " (" + abbreviation + ")";
    }
}
