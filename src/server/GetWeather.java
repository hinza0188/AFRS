package server;

import information.Airport;

/**
 * Get weather command.
 */
public class GetWeather implements Request
{
    private Airport airport;

    /**
     * Create get weather command given an airport object.
     * @param airport
     */
    public GetWeather(Airport airport)
    {
        this.airport = airport;
    }

    /**
     * Exeecute the command.
     * @return
     */
    @Override
    public String executeCommand()
    {
        return AFRS.getWeather(airport);
    }

    @Override
    public String undo() {
        return null;
    }

    @Override
    public String redo() {
        return null;
    }
}
