package information;

import java.time.OffsetTime;

/**
 * Encapsulates and manages airport information.
 */
public class Airport
{
    private final String abbreviation;
    private final String cityName;

    private String[] weather;
    private int weatherRequestCounter = 0;

    private OffsetTime timeDelay;

    /**
     * Create an object representing an airport.
     * @param abbreviation The airport abbreviation.
     * @param cityName The name of the city.
     */
    Airport(String abbreviation, String cityName)
    {
        this.abbreviation = abbreviation;
        this.cityName = cityName;
    }

    /**
     * Get the airport's 3 letter code.
     * @return The 3 lettered code in String
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }

    /**
     * Get the airport's city name.
     * @return the city name of airport in String
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * Get the weather of the airport at this moment.
     * @return the status of weather in String
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
    void setWeather(String[] weather)
    {
        this.weather = weather;
    }

    /**
     * Get the delay time.
     * @return the delay time in OffsetTime
     */
    public OffsetTime getTimeDelay()
    {
        return timeDelay;
    }

    /**
     * Set the delay time.
     * @param timeDelay the delay time
     */
    void setTimeDelay(OffsetTime timeDelay)
    {
        this.timeDelay = timeDelay;
    }

    @Override
    public String toString()
    {
        return cityName + " (" + abbreviation + ")";
    }
}
