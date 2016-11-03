package helpers;

import information.Itinerary;

import java.util.Comparator;
import java.util.List;

public class SortByDepartureTime implements ItinerarySortingAlgorithm
{
    /**
     * Sorts itineraries by departure time.
     * @param itineraries all generated itinerary objects
     */
    @Override
    public void sortItineraries(List<Itinerary> itineraries)
    {
        itineraries.sort(Comparator.comparing(Itinerary::getDepartureTime));
    }
}
