package helpers;

import information.Itinerary;

import java.util.List;

/**
 * Interface for sorting a list of itineraries.
 */
public interface ItinerarySortingAlgorithm
{
    /**
     * Sorts a list of itineraries.
     */
    void sortItineraries(List<Itinerary> Itineraries);
}
