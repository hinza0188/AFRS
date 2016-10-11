/**
 * Created by hetelek on 10/6/16.
 */

package server;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RequestManager
{
    static RequestParser parser = new RequestParser();
    static Scanner scanner = new Scanner(System.in);

    private static RequestManager singleton = null;
    List<server.Request> commandStack = new Stack<server.Request>();

    protected RequestManager()  { }
    public static RequestManager getManager()
    {
        if(singleton == null)
            singleton = new RequestManager();

        return singleton;
    }

    public static void main(String[] args)
    {
        // print usage
        System.out.println("Welcome to the server.AFRS system.");
        RequestManager.printUsage();

        // main loop
        while (true)
        {
            System.out.print("> ");

            // append input to parser
            String command = scanner.nextLine();
            RequestManager.parser.appendData(command);

            // attempt to parse data
            Request[] requestCommands = RequestManager.parser.parseData();
            if (requestCommands.length > 0)
            {
                // execute commands
                for (Request requestCommand : requestCommands)
                {
                    requestCommand.executeCommand();
                }

                // clear data
                RequestManager.parser.clearData();
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
}
