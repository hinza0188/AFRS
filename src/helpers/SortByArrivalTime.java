package helpers;

import information.Itinerary;

import java.util.Comparator;
import java.util.List;

public class SortByArrivalTime implements ItinerarySortingAlgorithm
{
    /**
     * Sorts itineraries by arrival time.
     * @param itineraries
     */
    @Override
    public void sortItineraries(List<Itinerary> itineraries)
    {
        itineraries.sort(Comparator.comparing(Itinerary::getArrivalTime));
    }
}
