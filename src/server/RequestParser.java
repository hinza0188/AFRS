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
                String mainCommand = commandArgs[0];
                if (mainCommand.equals(""))
                {

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
