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
        /*
        for (int i=0;i<Itineraries.size()-1; i++){
            int index=i;
            for (int j=i+1;j<Itineraries.size();j++){
                if (Itineraries.get(j).getAirfare()<Itineraries.get(index).getAirfare()){
                    index=j;
                }
            }
            Itinerary smallerItinerary=Itineraries.get(index);
            Itineraries.set(index,Itineraries.get(i));
            Itineraries.set(i,smallerItinerary);
        }
        */
        Itineraries.sort(Comparator.comparing(Itinerary::getAirfare));
        return Itineraries;
    }
}
