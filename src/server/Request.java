package server;

/**
 * Interface for request/command.
 */
interface Request
{
    String executeCommand();
    String undo();
    String redo();
}
