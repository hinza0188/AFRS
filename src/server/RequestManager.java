package server;

import java.util.List;
import java.util.Stack;

public class RequestManager
{
    private RequestParser parser = new RequestParser();
    private List<Request> commandStack = new Stack<>();

    public String[] takeCommand(String command)
    {
        // append input to parser
        this.parser.appendData(command);

        try
        {
            // attempt to parse data
            Request[] requestCommands = this.parser.parseData();
            if (requestCommands.length > 0)
            {
                // execute commands and get responses
                String[] commandResponses = new String[requestCommands.length];
                for (int i = 0; i < requestCommands.length; ++i)
                {
                    Request requestCommand = requestCommands[i];
                    commandResponses[i] = requestCommand.executeCommand();

                    // add to command stack
                    this.commandStack.add(requestCommand);
                }

                // clear data
                this.parser.clearData();

                return commandResponses;
            }
            else
                return new String[] { "partial-request" };
        }
        catch (Exception ex)
        {
            // clear parser on exception and return message
            this.parser.clearData();
            return new String[] { ex.getMessage() };
        }
    }
}
