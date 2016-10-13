package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class AirportManager
{
    private static String AIRPORT_FILE_PATH = "data/airports.txt";
    private static String AIRPORT_WEATHER_FILE_PATH = "data/weather.txt";

    private static AirportManager singleton = null;
    private ArrayList<Airport> airports;

    protected AirportManager()  { }
    public static AirportManager getManager()
    {
        if(AirportManager.singleton == null)
        {
            // create manager (and read airports/weather)
            AirportManager.singleton = new AirportManager();
            AirportManager.singleton.readAirportsFromFile(AIRPORT_FILE_PATH);
            AirportManager.singleton.readWeatherFromFile(AIRPORT_WEATHER_FILE_PATH);
        }

        return AirportManager.singleton;
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

                this.airports.add(new Airport(abbreviation, cityName));
            }

            // close file
            airportFile.close();
        }
        catch (FileNotFoundException ex) { }
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
        }
        catch (FileNotFoundException ex) { }
    }
}
