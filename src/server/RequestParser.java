/**
 * Created by hetelek on 10/6/16.
 */

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

public class RequestParser
{
    private static final String TERMINATOR = ";";
    private String currentData;

    public RequestParser()
    {
        this.clearData();
    }

    public void appendData(String data)
    {
        this.currentData += data;
    }

    public Request[] parseData() throws Exception
    {
        if (currentData.length() < 1 || currentData.charAt(currentData.length() - 1) != ';')
            return new Request[] { };

        List<Request> commands = new ArrayList<Request>();

        // loop through each command
        String[] possibleFullCommands = this.currentData.split(TERMINATOR);
        for (String possibleFullCommand : possibleFullCommands)
        {
            // get individual arguments for command
            String[] commandArgs = possibleFullCommand.split(",");
            if (commandArgs.length > 0)
            {
                String mainCommand = commandArgs[0];
                if (mainCommand.equals("info"))
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

                    commands.add(new GetItinerary(originAirport, destinationAirport, maxConnections, sortingMethod));
                }
                else if (mainCommand.equals("reserve"))
                {
                    // reserve,id,passenger;
                    String passenger = commandArgs[1];
                    int identifier = Integer.parseInt(commandArgs[2]);

                    Itinerary itinerary = ItineraryManager.getManager().getItineraryWithIdentifier(identifier);
                    if (itinerary == null)
                        throw new Exception("error,invalid id");

                    commands.add(new MakeReservation(passenger, itinerary));
                }
                else if (mainCommand.equals("retrieve"))
                {
                    // retrieve,passenger[,origin[,destination]];
                    String passenger = commandArgs[1];

                    if (commandArgs.length == 2)
                        commands.add(new GetReservation(passenger));
                    else if (commandArgs.length >= 3)
                    {
                        Airport originAirport = AirportManager.getManager().getAirport(commandArgs[2]);
                        if (originAirport == null)
                            throw new Exception("error,unknown origin");

                        if (commandArgs.length == 4)
                        {
                            Airport destinationAirport = AirportManager.getManager().getAirport(commandArgs[3]);
                            if (destinationAirport == null)
                                throw new Exception("error,unknown destination");

                            commands.add(new GetReservation(passenger, originAirport, destinationAirport));
                        }
                        else
                            commands.add(new GetReservation(passenger, originAirport));
                    }
                    else
                        commands.add(new GetReservation(passenger));
                }
                else if (mainCommand.equals("delete"))
                {
                    // delete,passenger,origin,destination;
                }
                else if (mainCommand.equals("weather"))
                {
                    // weather,airport;
                    String airportAbbreviation = commandArgs[1];
                    Airport airport = AirportManager.getManager().getAirport(airportAbbreviation);

                    if (airport == null)
                        throw new Exception("error,unknown airport");

                    commands.add(new GetWeather(airport));
                }
                else
                {
                    throw new Exception("invalid-command");
                }
            }
        }

        return commands.toArray(new Request[] { });
    }

    public void clearData()
    {
        this.currentData = "";
    }
}
