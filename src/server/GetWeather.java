package server;

import information.Airport;

/**
 * Get weather command.
 */
class GetWeather implements Request
{
    private final Airport airport;

    /**
     * Create get weather command given an airport object.
     * @param airport: target airport name
     */
    GetWeather(Airport airport)
    {
        this.airport = airport;
    }

    /**
     * Execute the command.
     * @return : the result of execution
     */
    @Override
    public String executeCommand()
    {
        return AFRS.getWeather(airport);
    }

    @Override
    public String undo() {return null;}

    @Override
    public String redo() {
        return null;
    }
}
