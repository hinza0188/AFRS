package server;

import helpers.ItinerarySortingAlgorithm;
import information.Airport;

public class GetItinerary implements Request
{
    private Airport originAirport;
    private Airport destinationAirport;
    private int maxConnections;
    private ItinerarySortingAlgorithm sortingAlgorithm;

    public GetItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.maxConnections = maxConnections;
        this.sortingAlgorithm = sortingMethod;
    }

    @Override
    public String executeCommand()
    {
        return AFRS.getItinerary(originAirport, destinationAirport, maxConnections, sortingAlgorithm);
    }
}
