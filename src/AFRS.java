/**
 * Created by Yongki Jay An
 */

import server.RequestParser;
import server.Request;
import information.*;

import java.util.Scanner;


public class AFRS
{
    static RequestParser parser = new RequestParser();
    static Scanner scanner = new Scanner(System.in);
    
    private AirportManager airportManager;
    private FlightManager flightManager;
    //private ItineraryManager itineraryManager;
    private ReservationManager reservationManager;

    public static void main(String[] args)
    {
        // print usage
        System.out.println("Welcome to the AFRS system.");
        AFRS.printUsage();

        // main loop
        while (true)
        {
            System.out.print("> ");

            // append input to parser
            String command = scanner.nextLine();
            AFRS.parser.appendData(command);

            // attempt to parse data
            Request[] requestCommands = AFRS.parser.parseData();
            if (requestCommands.length > 0)
            {
                // execute commands
                for (Request requestCommand : requestCommands)
                {
                    requestCommand.executeCommand();
                }

                // clear data
                AFRS.parser.clearData();
            }

            // check if the user is quiting
            if (command.equals("quit"))
                break;
        }
    }

    private static void printUsage()
    {
        // print usage
        System.out.println("Usage:");
        System.out.println("\tcommand_name required_arguments [option_arguments]");
        System.out.println();

        // print actual commands
        System.out.println("\tget_weather airport");
        System.out.println("\tget_itinerary origin_airport destination_airport");
        System.out.println("\tmake_reservation first_name last_name itinerary_id");
        System.out.println("\tdelete_reservation first_name last_name origin_airport destination_airport");
        System.out.println("\tget_reservation first_name last_name [origin_airport] [destination_airport]");

        System.out.println();
        System.out.println("\tquit");
        System.out.println();
    }
    
    protected void makeReservation(String passengerName, Itinerary itinerary){
        reservationManager.makeReservation(passengerName, itinerary);
    }
}
