package helpers;

import information.Itinerary;

import java.util.Comparator;
import java.util.List;

public class SortByDepartureTime implements ItinerarySortingAlgorithm
{
    @Override
    public void sortItineraries(List<Itinerary> itineraries)
    {
       /*
        for (int i=0;i<Itineraries.size()-1; i++){
            int index=i;
            for (int j=i+1;j<Itineraries.size();j++){
                if (Itineraries.get(j).getDepartureTime().isBefore(Itineraries.get(index).getDepartureTime())){
                    index=j;
                }
            }
            Itinerary smallerItinerary=Itineraries.get(index);
            Itineraries.set(index,Itineraries.get(i));
            Itineraries.set(i,smallerItinerary);
        }
        */
        itineraries.sort(Comparator.comparing(Itinerary::getDepartureTime));
    }
}
