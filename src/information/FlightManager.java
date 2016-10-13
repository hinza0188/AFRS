package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

public class FlightManager
{
    private static String FLIGHTS_FILE_PATH = "data/flights.txt";

    private static FlightManager singleton = null;
    private ArrayList<Flight> flights;

    protected FlightManager()
    {
    }

    public static FlightManager getManager()
    {
        if (singleton == null)
        {
            // create manager (and read flights)
            singleton = new FlightManager();
            FlightManager.singleton.readFlightsFromFile(FLIGHTS_FILE_PATH);
        }

        return singleton;
    }

    private static LocalTime parseTime(String time)
    {
        // get string info
        String timeOnly = time.substring(0, time.length() - 1);
        String[] timeParts = timeOnly.split(":");

        // parse info
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        boolean morning = time.charAt(time.length() - 1) == 'a';

        // create local time
        if (morning)
            return LocalTime.of(hour - 1, minute);
        else
            return LocalTime.of(hour + 11, minute);
    }

    public ArrayList<Flight> flightsLeavingAirport(Airport originAirport)
    {
        // find flights leaving
        ArrayList<Flight> flightsLeaving = new ArrayList<>();
        this.flights.forEach(flight ->
        {
            if (flight.getOriginAirport() == originAirport)
                flightsLeaving.add(flight);
        });

        return flightsLeaving;
    }

    private void readFlightsFromFile(String filePath)
    {
        // create new array list
        this.flights = new ArrayList<>();

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
                String dTime = data[2];
                String aTime = data[3];

                // parse data
                LocalTime departureTime = FlightManager.parseTime(dTime);
                LocalTime arrivalTime = FlightManager.parseTime(aTime);

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
        } catch (FileNotFoundException ex)
        {
        }
    }
}
