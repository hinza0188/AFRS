/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.OffsetDateTime;

public class Airport
{
    private String abbreviation;
    private String cityName;

    private String[] weather;
    private int weatherRequestCounter = 0;

    private OffsetDateTime timeDelay;

    protected Airport(String abbreviation, String cityName)
    {
        this.abbreviation = abbreviation;
        this.cityName = cityName;
    }

    protected void setWeather(String[] weather)
    {
        this.weather = weather;
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
        int currentWeatherIndex = this.weatherRequestCounter++ % this.weather.length;
        return this.weather[currentWeatherIndex];
    }

    public OffsetDateTime getTimeDelay()
    {
        return timeDelay;
    }

    @Override
    public String toString()
    {
        return cityName + " (" + abbreviation + ")";
    }
}
