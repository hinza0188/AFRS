package server;

import java.util.Stack;

/**
 * Manager for handling and parsing commands.
 */
public class RequestManager
{
    private final RequestParser parser = new RequestParser();
    private final Stack<Request> undoStack = new Stack<>();
    private final Stack<Request> redoStack = new Stack<>();

    /**
     * Take a full or partial command.
     * @param command parsed input string
     * @return arrays of string for status change
     */
    String[] takeCommand(String command)
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
                    if(requestCommand instanceof MakeReservation || requestCommand instanceof DeleteReservation)
                        undoStack.push(requestCommand);
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

    /**
     * Undo the last command that was done.
     * @return success string if executed without error
     */
    public String undo()
    {
        if (undoStack.size() < 1)
            return "nothing to undo";

        // undo command
        Request request = undoStack.pop();
        redoStack.push(request);
        return request.undo();

    }

    /**
     * Redo the last undid command.
     * @return success string if executed without error
     */
    public String redo()
    {
        if (redoStack.size() < 1)
            return "nothing to redo";

        // redo command
        Request request = redoStack.pop();
        undoStack.push(request);
        return request.redo();
    }
}
