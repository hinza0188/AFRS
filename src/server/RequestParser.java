/**
 * Created by hetelek on 10/6/16.
 */

package server;

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

    public Request[] parseData()
    {
        // loop through each command
        String[] possibleFullCommands = this.currentData.split(TERMINATOR);
        for (String possibleFullCommand : possibleFullCommands)
        {
            // get individual arguments for command
            String[] commandArgs = possibleFullCommand.split(" ");
            if (commandArgs.length > 0)
            {
                /* COMMAND LIST w/ ARGS
                    get_weather airport
                    get_itinerary origin_airport destination_airport
                    make_reservation first_name last_name itinerary_id
                    delete_reservation first_name last_name origin_airport destination_airport
                    get_reservation first_name last_name [origin_airport] [destination_airport]
                */

                String mainCommand = commandArgs[0];
                String requestParam_1 = commandArgs[1];
                String requestParam_2 = commandArgs[2];
                String requestParam_3 = commandArgs[3];
                if (mainCommand.equals("get_weather"))
                {
                    //@TODO: Fill this command execution
                    System.out.println("get_weather command activated\n");  // this is testing purposes code
                    System.out.println("received parameter: ");
                    System.out.println(requestParam_1);
                    /* Jay: I ran out of time to figure out how to call request object and connect with the
                     * concrete command here.
                     */
                }
                else if (mainCommand.equals("get_itinerary"))
                {
                    //@TODO: Fill this command execution
                    /*
                     * you need to check which sort is specified or use the default.
                     * this needs to create a strategy object before creating a command object with a strategy object
                     */

                }
                else if (mainCommand.equals("make_reservation"))
                {
                    //@TODO: Fill this command execution
                }
                else if (mainCommand.equals("delete_reservation"))
                {
                    //@TODO: Fill this command execution
                }
                else if (mainCommand.equals("get_reservation"))
                {
                    //@TODO: Fill this command execution
                }
            }
        }

        return new Request[] { };
    }

    public void clearData()
    {
        this.currentData = "";
    }
}
