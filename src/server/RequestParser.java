package server;

import helpers.ItinerarySortingAlgorithm;
import helpers.SortByAirfare;
import helpers.SortByArrivalTime;
import helpers.SortByDepartureTime;
import information.Airport;
import information.AirportManager;
import information.Itinerary;
import information.ItineraryManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse requests.
 */
class RequestParser
{
    private static final String TERMINATOR = ";";
    private String currentData;

    /**
     * Create a request parser.
     */
    RequestParser()
    {
        this.clearData();
    }

    /**
     * Parse command for getting itineraries.
     * @param commandArgs : user choice of sorting algorithm
     * @return : GetItinerary class
     * @throws Exception : ignore when issue occur
     */
    private static GetItinerary parseInfoCommand(String[] commandArgs) throws Exception
    {
        // info,origin,destination[,connections[,sort-order]];
        String origin = commandArgs[1];
        String destination = commandArgs[2];

        // create parameters
        Airport originAirport = AirportManager.getManager().getAirport(origin);
        Airport destinationAirport = AirportManager.getManager().getAirport(destination);
        int maxConnections = 2;
        ItinerarySortingAlgorithm sortingMethod = new SortByDepartureTime();

        // parse optionals
        if (commandArgs.length >= 4 && commandArgs[3].length() > 0)
            maxConnections = Integer.parseInt(commandArgs[3]);
        if (commandArgs.length == 5)
        {
            switch (commandArgs[4])
            {
                case "departure":
                    sortingMethod = new SortByDepartureTime();
                    break;
                case "arrival":
                    sortingMethod = new SortByArrivalTime();
                    break;
                case "airfare":
                    sortingMethod = new SortByAirfare();
                    break;
                default:
                    throw new Exception("error,invalid sort order");
            }
        }

        // check for invalid request
        if (originAirport == null)
            throw new Exception("error,unknown origin");
        else if (destinationAirport == null)
            throw new Exception("error,unknown destination");
        else if (maxConnections < 0 || maxConnections > 2)
            throw new Exception("error,invalid connection limit");

        return new GetItinerary(originAirport, destinationAirport, maxConnections, sortingMethod);
    }

    /**
     * Parse command to make a reservation.
     * @param commandArgs : user choice of sorting algorithm
     * @return
     * @throws Exception
     */
    private static MakeReservation parseMakeReservationCommand(String[] commandArgs) throws Exception
    {
        // reserve,id,passenger;
        int identifier = Integer.parseInt(commandArgs[1]);
        String passenger = commandArgs[2];

        // create itinerary
        Itinerary itinerary = ItineraryManager.getManager().getItineraryWithIdentifier(identifier);
        if (itinerary == null)
            throw new Exception("error,invalid id");

        return new MakeReservation(passenger, itinerary);
    }

    /**
     * Parse command to get reservations.
     * @param commandArgs : user choice of sorting algorithm
     * @return
     * @throws Exception
     */
    private static GetReservation parseGetReservationCommand(String[] commandArgs) throws Exception
    {
        // retrieve,passenger[,origin[,destination]];
        String passenger = commandArgs[1];

        // parse optionals
        if (commandArgs.length == 2)
            return new GetReservation(passenger);
        else if (commandArgs.length >= 3)
        {
            // check if origin exists
            Airport originAirport = AirportManager.getManager().getAirport(commandArgs[2]);
            if (originAirport == null)
                throw new Exception("error,unknown origin");

            if (commandArgs.length == 4)
            {
                // check if destination exists
                Airport destinationAirport = AirportManager.getManager().getAirport(commandArgs[3]);
                if (destinationAirport == null)
                    throw new Exception("error,unknown destination");

                return new GetReservation(passenger, originAirport, destinationAirport);
            }

            return new GetReservation(passenger, originAirport);
        }

        return new GetReservation(passenger);
    }

    /**
     * Parse command to delete reservation.
     * @param commandArgs : user choice of sorting algorithm
     * @return
     * @throws Exception
     */
    private static DeleteReservation parseDeleteReservationCommand(String[] commandArgs) throws Exception
    {
        // delete,passenger,origin,destination;
        String passenger = commandArgs[1];

        // check if origin exists
        Airport originAirport = AirportManager.getManager().getAirport(commandArgs[2]);
        if (originAirport == null)
            throw new Exception("error,unknown origin");

        // check if destination exists
        Airport destinationAirport = AirportManager.getManager().getAirport(commandArgs[3]);
        if (destinationAirport == null)
            throw new Exception("error,unknown destination");

        return new DeleteReservation(passenger, originAirport, destinationAirport);
    }

    /**
     * Parse command to get the weather.
     * @param commandArgs : user choice of sorting algorithm
     * @return
     * @throws Exception
     */
    private static GetWeather parseGetWeatherCommand(String[] commandArgs) throws Exception
    {
        // weather,airport;
        String airportAbbreviation = commandArgs[1];

        // check if airport exists
        Airport airport = AirportManager.getManager().getAirport(airportAbbreviation);
        if (airport == null)
            throw new Exception("error,unknown airport");

        return new GetWeather(airport);
    }

    /**
     * Append a partial command to the current data.
     * @param data
     */
    void appendData(String data)
    {
        this.currentData += data;
    }

    /**
     * Parse all the data in currentData.
     * @return parsed data
     * @throws Exception : ignore if error occurs
     */
    Request[] parseData() throws Exception
    {
        // make sure we have some data and end with a ';'
        if (currentData.length() < 1 || currentData.charAt(currentData.length() - 1) != ';')
            return new Request[]{};

        List<Request> commands = new ArrayList<>();

        // loop through each command
        String[] possibleFullCommands = this.currentData.split(TERMINATOR);
        for (String possibleFullCommand : possibleFullCommands)
        {
            // get command objects
            String[] commandArgs = possibleFullCommand.split(",");
            if (commandArgs.length > 0)
            {
                String mainCommand = commandArgs[0];
                switch (mainCommand)
                {
                    case "info":
                        commands.add(RequestParser.parseInfoCommand(commandArgs));
                        break;
                    case "reserve":
                        commands.add(RequestParser.parseMakeReservationCommand(commandArgs));
                        break;
                    case "retrieve":
                        commands.add(RequestParser.parseGetReservationCommand(commandArgs));
                        break;
                    case "delete":
                        commands.add(RequestParser.parseDeleteReservationCommand(commandArgs));
                        break;
                    case "weather":
                        commands.add(RequestParser.parseGetWeatherCommand(commandArgs));
                        break;


                    default:
                        throw new Exception("invalid-command");
                }
            }
        }

        return commands.toArray(new Request[]{});
    }

    /**
     * Clear the current data (including partial commands).
     */
    void clearData()
    {
        this.currentData = "";
    }
}
