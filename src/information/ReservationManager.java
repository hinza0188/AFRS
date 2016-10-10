/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.util.ArrayList;

public class ReservationManager
{
    private static ReservationManager singleton = null;
    private ArrayList<Reservation> reservations;

    protected ReservationManager()  { }
    public static ReservationManager getManager()
    {
        if(singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }
    
    public String makeReservation(String passengerName, Itinerary itinerary){
        Reservation newReservation = new Reservation(passengerName, itinerary);
        reservations.add(newReservation);
        return "Reservation made for "+passengerName+" from "+itinerary.getOriginAirport().getCityName()+" to "+itinerary.getDestinationAirport().getCityName()+"";
    }
    
    public String deleteReservation(String passengerName, Airport originAirport, Airport destinationAirport){
        for (Reservation res : reservations){
            if ((res.getPassengerName() == passengerName) && (res.getDestinationAirport() == destinationAirport) && (res.getOriginAirport()==originAirport)){
                reservations.remove(res);
            }
            else {
                return "No matching reservation";
            }
        }
        return "Reservation deleted for "+passengerName+" from "+originAirport.getCityName()+" to "+destinationAirport.getCityName()+"";
    }
}
