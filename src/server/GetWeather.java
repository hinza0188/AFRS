package server;

import information.Airport;

public class GetWeather implements Request
{
    private Airport airport;

    public GetWeather(Airport airport)
    {
        this.airport = airport;
    }

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
