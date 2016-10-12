/**
 * Created by hetelek on 10/6/16.
 */

package helpers;

import information.Itinerary;

import java.util.Comparator;
import java.util.List;

public class SortByAirfare implements ItinerarySortingAlgorithm
{
    @Override
    public List<Itinerary> sortItineraries(List<Itinerary> Itineraries)
    {
        Itineraries.sort(Comparator.comparing(Itinerary::getAirfare));
        return Itineraries;
    }
}
