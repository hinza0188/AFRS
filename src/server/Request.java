package server;

public interface Request
{
    String executeCommand();
    String undo();
    String redo();
}
