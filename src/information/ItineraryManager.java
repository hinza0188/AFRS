package information;

import java.util.ArrayList;

/**
 * Author : Jay
 * This class is made of singleton pattern that
 * takes care of sorting algorithms and manages
 * each itinerary objects
 */
public class ItineraryManager {
    private static ItineraryManager singleton = null;
    private ArrayList<Itinerary> itineraries;

    protected ItineraryManager() { }
    public static ItineraryManager getManager() {
        if(singleton == null) {
            singleton = new ItineraryManager();
        }
        return singleton;
    }
}
