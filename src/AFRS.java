/**
 * Created by Yongki Jay An
 */

import server.RequestParser;
import java.util.Scanner;

public class AFRS
{
    static RequestParser parser = new RequestParser();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        // print usage
        System.out.println("Welcome to the AFRS system.");
        AFRS.printUsage();

        // main loop
        while (true)
        {
            // read input
            System.out.print("> ");
            String command = scanner.nextLine();

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
}
