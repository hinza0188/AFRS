/**
 * Created by hetelek on 10/6/16.
 */

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
}
