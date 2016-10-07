/**
 * Created by hetelek on 10/6/16.
 */

package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FlightManager
{
    private static String FLIGHTS_FILE_PATH = "data/flights.txt";

    private static FlightManager singleton = null;
    public ArrayList<Flight> flights;

    protected FlightManager()  { }
    public static FlightManager getManager()
    {
        if(singleton == null)
        {
            singleton = new FlightManager();
            FlightManager.singleton.readFlightsFromFile(FLIGHTS_FILE_PATH);
        }

        return singleton;
    }

    private void readFlightsFromFile(String filePath)
    {
        // create new array list
        this.flights = new ArrayList<Flight>();

        try
        {
            // open file
            CSVReader flightFile = new CSVReader(filePath);
            flightFile.open();

            // read flights
            String[] data;
            while ((data = flightFile.readLine()) != null)
            {
                // get data
                String originAirportAbbreviation = data[0];
                String destinationAirportAbbreviation = data[1];
                String departureTime = data[2];
                String arrivalTime = data[3];
                int flightNumber = Integer.parseInt(data[4]);
                double airfare = Double.parseDouble(data[5]);

                // get airport objects
                Airport originAirport = AirportManager.getManager().getAirport(originAirportAbbreviation);
                Airport destinationAirport = AirportManager.getManager().getAirport(destinationAirportAbbreviation);

                // create flight
                Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureTime, arrivalTime,
                        airfare);
                this.flights.add(flight);
            }

            // close file
            flightFile.close();
        }
        catch (FileNotFoundException ex) { }
    }
}
