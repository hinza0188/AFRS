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
            singleton.reservations=new ArrayList<Reservation>();
        return singleton;
    }
    public Reservation getReservation(String passengerName, Airport originAirport, Airport destinationAirport){
        for(Reservation currentReservation:this.reservations){
            if (currentReservation.getPassenger().equals(passengerName)&&currentReservation.getItinerary().getOriginAirport()==originAirport&&currentReservation.getItinerary().getDestinationAirport()==destinationAirport){
                return currentReservation;
            }
        }
        //no reservation found throw error
        return null;
    }
    public ArrayList<Reservation> getReservationsForPassenger(String passengerName){
        ArrayList<Reservation>passengersReservations=new ArrayList<Reservation>();
        for(Reservation currentReservation:this.reservations){
            if (currentReservation.getPassenger().equals(passengerName)){
                passengersReservations.add(currentReservation);
            }
        }

        return passengersReservations;
    }
    public Reservation makeReservation(String passengerName, Itinerary itinerary)
    {
        //check if reservation exists
        if (getReservation(passengerName, itinerary.getOriginAirport(),itinerary.getDestinationAirport())==null){
            //create reservation object
            Reservation newReservation = new Reservation(passengerName,itinerary);
            //add reservation to list
            this.reservations.add(newReservation);
            return newReservation;
        }
        else{
            //reservation exists raise error
            return null;
        }
    }
    public void deleteReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }
}
