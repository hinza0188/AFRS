package server;

/**
 * Interface for request/command.
 */
public interface Request
{
    String executeCommand();
    String undo();
    String redo();
}
