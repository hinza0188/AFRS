package server;

import helpers.ItinerarySortingAlgorithm;
import information.Airport;

/**
 * Get itinerary command.
 */
public class GetItinerary implements Request
{
    private Airport originAirport;
    private Airport destinationAirport;
    private int maxConnections;
    private ItinerarySortingAlgorithm sortingAlgorithm;

    /**
     * Create an itinerary command.
     * @param originAirport
     * @param destinationAirport
     * @param maxConnections
     * @param sortingMethod
     */
    public GetItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.maxConnections = maxConnections;
        this.sortingAlgorithm = sortingMethod;
    }

    /**
     * Execute the command.
     * @return
     */
    @Override
    public String executeCommand()
    {
        return AFRS.getItinerary(originAirport, destinationAirport, maxConnections, sortingAlgorithm);
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
