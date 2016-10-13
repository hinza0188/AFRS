package server;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RequestManager
{
    private static RequestParser parser = new RequestParser();
    private static Scanner scanner = new Scanner(System.in);

    private static RequestManager singleton = null;
    List<server.Request> commandStack = new Stack<>();

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
        System.out.println("Welcome to the AFRS system.");
        RequestManager.printUsage();

        // main loop
        while (true)
        {
            System.out.print("> ");

            // append input to parser
            String command = scanner.nextLine();
            RequestManager.parser.appendData(command);

            try
            {
                // attempt to parse data
                Request[] requestCommands = RequestManager.parser.parseData();
                if (requestCommands.length > 0)
                {
                    // execute commands
                    for (Request requestCommand : requestCommands)
                    {
                        System.out.println(requestCommand.executeCommand());
                    }

                    // clear data
                    RequestManager.parser.clearData();
                }
                else
                {
                    System.out.println("partial-request");
                }

                // check if the user is quiting
                if (command.equals("quit"))
                    break;
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                RequestManager.parser.clearData();
            }
        }
    }

    private static void printUsage()
    {
        // print usage
        System.out.println("Usage:");
        System.out.println("\tcommand_name,required_arguments[,option_arguments]");
        System.out.println();

        // print actual commands
        System.out.println("\tinfo,origin,destination[,connections[,sort-order]];");
        System.out.println("\treserve,id,passenger;");
        System.out.println("\tretrieve,passenger[,origin[,destination]];");
        System.out.println("\tdelete,passenger,origin,destination;");
        System.out.println("\tweather,airport;");

        System.out.println();
        System.out.println("\tquit");
        System.out.println();
    }
}
