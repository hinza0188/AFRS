/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Airport;

public class GetItinerary implements Request
{
    Airport originAirport;
    Airport destinationAirport;
    
    public GetItinerary(Airport originAirport, Airport destinationAirport)
    {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    @Override
    public String executeCommand()
    {
        return null;
    }
}
