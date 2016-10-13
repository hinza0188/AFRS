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
    public void sortItineraries(List<Itinerary> itineraries)
    {
        itineraries.sort(Comparator.comparing(Itinerary::getAirfare));
    }
}
