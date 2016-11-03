package information;

import java.time.OffsetTime;

/**
 * Encapsulates and manages airport information.
 */
public class Airport
{
    private String abbreviation;
    private String cityName;

    private String[] weather;
    private int weatherRequestCounter = 0;

    private OffsetTime timeDelay;

    /**
     * Create an object representing an airport.
     * @param abbreviation The airport abbreviation.
     * @param cityName The name of the city.
     */
    protected Airport(String abbreviation, String cityName)
    {
        this.abbreviation = abbreviation;
        this.cityName = cityName;
    }

    /**
     * Get the airport's 3 letter code.
     * @return
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }

    /**
     * Get the airport's city name.
     * @return
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * Get the weather of the airport at this moment.
     * @return
     */
    public String getWeather()
    {
        // make sure we have some weather
        if (this.weather.length < 1)
            return null;

        // check if this is online data
        if (this.weather.length == 1)
            return this.weather[0];

        // return current weather
        int currentWeatherIndex = this.weatherRequestCounter % this.weather.length;
        String weather = this.weather[currentWeatherIndex];
        String temperature = this.weather[currentWeatherIndex + 1];

        this.weatherRequestCounter += 2;

        return weather + "," + temperature;
    }

    /**
     * Set the weather.
     * @param weather An array representing the weather.
     */
    protected void setWeather(String[] weather)
    {
        this.weather = weather;
    }

    /**
     * Get the delay time.
     * @return
     */
    public OffsetTime getTimeDelay()
    {
        return timeDelay;
    }

    /**
     * Set the delay time.
     * @param timeDelay
     */
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
