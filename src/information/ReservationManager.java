package information;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReservationManager
{
    private static ReservationManager singleton = null;
    private ArrayList<Reservation> reservations;

    protected ReservationManager()
    {
        reservations = new ArrayList<>();
    }

    public static ReservationManager getManager()
    {
        if(singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }
    
    public boolean deleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        for (Reservation reservation : this.reservations)
        {
            if (reservation.getPassengerName().equals(passengerName)
                    && reservation.getDestinationAirport() == destinationAirport
                    && reservation.getOriginAirport() == originAirport)
            {
                reservations.remove(reservation);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Reservation> getReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        ArrayList<Reservation> reservations= new ArrayList<>();
        if(originAirport != null && destinationAirport != null)
        {
            reservations.addAll(this.reservations.stream().filter(
                    currentReservation -> currentReservation.getPassenger().equals(passengerName) &&
                    currentReservation.getItinerary().getOriginAirport() == originAirport &&
                    currentReservation.getItinerary().getDestinationAirport() == destinationAirport
            ).collect(Collectors.toList()));
        }
        else
        {
            reservations.addAll(this.reservations.stream().filter(
                    currentReservation -> currentReservation.getPassenger().equals(passengerName) &&
                    (currentReservation.getItinerary().getOriginAirport() == originAirport ||
                            currentReservation.getItinerary().getDestinationAirport() == destinationAirport)
            ).collect(Collectors.toList()));
        }

        return reservations;
    }

    public ArrayList<Reservation> getReservationsForPassenger(String passengerName)
    {
        ArrayList<Reservation> passengersReservations = this.reservations.stream().filter(currentReservation ->
                currentReservation.getPassenger().equals(passengerName)
        ).collect(Collectors.toCollection(ArrayList::new));

        return passengersReservations;
    }

    public boolean makeReservation(String passengerName, Itinerary itinerary)
    {
        // check if reservation exists
        if (getReservation(passengerName, itinerary.getOriginAirport(), itinerary.getDestinationAirport()).size() == 0)
        {
            // create reservation object
            Reservation newReservation = new Reservation(passengerName, itinerary);

            // add reservation to list
            reservations.add(newReservation);
            return true;
        }

        // reservation exists raise error
        return false;
    }
}
