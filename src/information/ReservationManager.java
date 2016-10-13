/**
 * Created by hetelek on 10/6/16.
 * modified by Roger
 * modified by Dylan
 */

package information;

import java.util.ArrayList;

public class ReservationManager
{
    private static ReservationManager singleton = null;
    private ArrayList<Reservation> reservations;

    protected ReservationManager()
    {
        reservations = new ArrayList<Reservation>();
    }

    public static ReservationManager getManager()
    {
        if(singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }
    
    public String deleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        for (Reservation reservation : this.reservations)
        {
            if (reservation.getPassengerName().equals(passengerName)
                    && reservation.getDestinationAirport() == destinationAirport
                    && reservation.getOriginAirport() == originAirport)
            {
                reservations.remove(reservation);
                return "delete,successful";
            }
        }

        return "error,reservation not found";
    }

    public ArrayList<Reservation> getReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        ArrayList<Reservation> reservations=new ArrayList<Reservation>();
        if(originAirport != null && destinationAirport != null)
        {
            for(Reservation currentReservation : this.reservations)
            {
                if (currentReservation.getPassenger().equals(passengerName)
                        && currentReservation.getItinerary().getOriginAirport() == originAirport
                        && currentReservation.getItinerary().getDestinationAirport() == destinationAirport)
                {
                    reservations.add(currentReservation);
                }
            }
        }
        else
        {
            for(Reservation currentReservation:this.reservations)
            {
                if (currentReservation.getPassenger().equals(passengerName)
                        && (currentReservation.getItinerary().getOriginAirport() == originAirport || currentReservation.getItinerary().getDestinationAirport() == destinationAirport))
                {
                    reservations.add(currentReservation);
                }
            }
        }


        return reservations;
    }

    public ArrayList<Reservation> getReservationsForPassenger(String passengerName)
    {
        ArrayList<Reservation> passengersReservations = new ArrayList<Reservation>();
        for (Reservation currentReservation : this.reservations)
        {
            if (currentReservation.getPassenger().equals(passengerName)){
                passengersReservations.add(currentReservation);
            }
        }
        return passengersReservations;
    }

    public String makeReservation(String passengerName, Itinerary itinerary)
    {
        // check if reservation exists
        if (getReservation(passengerName, itinerary.getOriginAirport(), itinerary.getDestinationAirport()).size() == 0)
        {
            // create reservation object
            Reservation newReservation = new Reservation(passengerName, itinerary);

            // add reservation to list
            reservations.add(newReservation);
            return "reserve,successful";
        }

        // reservation exists raise error
        return "error,duplicate reservation";
    }
}
