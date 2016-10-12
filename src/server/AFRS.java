package server; /**
 * Created by Yongki Jay An
 */

import information.Itinerary;
import information.ReservationManager;

public class AFRS
{
    protected void makeReservation(String passengerName, Itinerary itinerary){
        ReservationManager.getManager().makeReservation(passengerName, itinerary);
    }
    /*
    When the getItineraries mehtod is written it needs take the resulting itineraries as an arraylist
    and call ortItineraries(arrayList<Itinerary>)this retuns the sorted list
     */

}
