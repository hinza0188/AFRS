package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

public class OfflineAirportData implements AirportData
{
    private ArrayList<Airport> airports;
    private static String AIRPORT_FILE_PATH = "data/airports.txt";
    private static String AIRPORT_WEATHER_FILE_PATH = "data/weather.txt";
    private static String AIRPORT_DELAYS_FILE_PATH = "data/delays.txt";

    public OfflineAirportData()
    {
        this.readAirportsFromFile(AIRPORT_FILE_PATH);
        this.readWeatherFromFile(AIRPORT_WEATHER_FILE_PATH);
        this.readDelaysFromFile(AIRPORT_DELAYS_FILE_PATH);
    }

    public Airport getAirport(String abbreviation)
    {
        // find the given airport
        for (Airport airport : this.airports)
        {
            if (airport.getAbbreviation().equals(abbreviation))
                return airport;
        }

        return null;
    }

    private void readAirportsFromFile(String filePath)
    {
        // create new array list
        this.airports = new ArrayList<>();

        try
        {
            // open file
            CSVReader airportFile = new CSVReader(filePath);
            airportFile.open();

            // read airports
            String[] data;
            while ((data = airportFile.readLine()) != null)
            {
                String abbreviation = data[0];
                String cityName = data[1];

                airports.add(new Airport(abbreviation, cityName));
            }

            // close file
            airportFile.close();
        } catch (FileNotFoundException ex)
        {
        }
    }

    private void readWeatherFromFile(String filePath)
    {
        try
        {
            // open file
            CSVReader weatherFile = new CSVReader(filePath);
            weatherFile.open();

            // read weather
            String[] data;
            while ((data = weatherFile.readLine()) != null)
            {
                // get airport
                String abbreviation = data[0];
                Airport airport = this.getAirport(abbreviation);

                // get weather (remove first element), and set
                String[] weatherData = Arrays.copyOfRange(data, 1, data.length);
                airport.setWeather(weatherData);
            }

            // close file
            weatherFile.close();
        } catch (FileNotFoundException ex)
        {
        }
    }

    private void readDelaysFromFile(String filePath)
    {
        try
        {
            // open file
            CSVReader delaysFile = new CSVReader(filePath);
            delaysFile.open();

            // read weather
            String[] data;
            while ((data = delaysFile.readLine()) != null)
            {
                // get airport
                String abbreviation = data[0];
                Airport airport = this.getAirport(abbreviation);

                // get delay, and set
                int delayInMinutes = Integer.parseInt(data[1]);
                airport.setTimeDelay(OffsetTime.of(delayInMinutes / 60, delayInMinutes % 60, 0, 0, ZoneOffset.UTC));
            }

            // close file
            delaysFile.close();
        } catch (FileNotFoundException ex)
        {
        }
    }
}
