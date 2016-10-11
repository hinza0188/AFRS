/**
 * Created by hetelek on 10/6/16.
 */

package server;

import helpers.ItinerarySortingAlgorithm;
import information.Airport;

public class GetItinerary implements Request
{
    Airport originAirport;
    Airport destinationAirport;
    ItinerarySortingAlgorithm sortingAlgorithm;
    
    public GetItinerary(Airport originAirport, Airport destinationAirport, ItinerarySortingAlgorithm sortingMethod)
    {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.sortingAlgorithm=sortingMethod;
        //Call the AFRS with the origin, destination, and a sorting strategy
    }

    @Override
    public String executeCommand()
    {
        return null;
    }
}
