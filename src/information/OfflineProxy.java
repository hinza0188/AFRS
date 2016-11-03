package information;

import helpers.CSVIterator;
import helpers.CSVReader;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Reads and manages a list of offline airports.
 */
class OfflineProxy implements AirportData
{
    private ArrayList<Airport> airports;
    final private static String AIRPORT_FILE_PATH = "data/airports.txt";
    final private static String AIRPORT_WEATHER_FILE_PATH = "data/weather.txt";
    final private static String AIRPORT_DELAYS_FILE_PATH = "data/delays.txt";

    /**
     * Parse the offline data file.
     */
    OfflineProxy()
    {
        this.readAirportsFromFile(AIRPORT_FILE_PATH);
        this.readWeatherFromFile(AIRPORT_WEATHER_FILE_PATH);
        this.readDelaysFromFile(AIRPORT_DELAYS_FILE_PATH);
    }

    /**
     * Get the airport from the 3 letter airport code.
     * @param abbreviation The airport's 3 letter code.
     * @return Corresponding Airport object
     */
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

    /**
     * Parse the airport file.
     * @param filePath: String format of directory
     */
    private void readAirportsFromFile(String filePath)
    {
        // create new array list
        this.airports = new ArrayList<>();

        // open file
        CSVReader airportFile = new CSVReader(filePath);
        CSVIterator csvIterator = airportFile.getIterator();

        csvIterator.first();

        // read airports
        while (csvIterator.currentItem() != null)
        {
            String[] data = csvIterator.currentItem();

            String abbreviation = data[0];
            String cityName = data[1];

            airports.add(new Airport(abbreviation, cityName));

            csvIterator.next();
        }

        // close file
        airportFile.close();
    }

    /**
     * Parse the weather from the local file.
     * @param filePath: String format of directory
     */
    private void readWeatherFromFile(String filePath)
    {
        // open file
        CSVReader weatherFile = new CSVReader(filePath);
        CSVIterator csvIterator = weatherFile.getIterator();

        csvIterator.first();

        // read weather
        while (csvIterator.currentItem() != null)
        {
            String[] data = csvIterator.currentItem();

            // get airport
            String abbreviation = data[0];
            Airport airport = this.getAirport(abbreviation);

            // get weather (remove first element), and set
            String[] weatherData = Arrays.copyOfRange(data, 1, data.length);
            airport.setWeather(weatherData);

            csvIterator.next();
        }

        // close file
        weatherFile.close();
    }

    /**
     * Read the delays from the file.
     * @param filePath: String format of directory
     */
    private void readDelaysFromFile(String filePath)
    {
        // open file
        CSVReader delaysFile = new CSVReader(filePath);
        CSVIterator csvIterator = delaysFile.getIterator();

        csvIterator.first();

        // read weather
        while (csvIterator.currentItem() != null)
        {
            String[] currentItem = csvIterator.currentItem();

            // get airport
            String abbreviation = currentItem[0];
            Airport airport = this.getAirport(abbreviation);

            // get delay, and set
            int delayInMinutes = Integer.parseInt(currentItem[1]);
            airport.setTimeDelay(OffsetTime.of(delayInMinutes / 60, delayInMinutes % 60, 0, 0, ZoneOffset.UTC));

            csvIterator.next();
        }

        // close file
        delaysFile.close();
    }
}
