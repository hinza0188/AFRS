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


}
