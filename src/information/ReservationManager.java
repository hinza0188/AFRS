package information;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages a list of reservations.
 */
public class ReservationManager
{
    private static ReservationManager singleton = null;
    private ArrayList<Reservation> reservations;

    private ReservationManager()
    {
        reservations = new ArrayList<>();
    }

    /**
     * Get the reservation manager singleton object.
     * @return global reservation manager
     */
    public static ReservationManager getManager()
    {
        if (singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }

    /**
     * Delete an existing reservation.
     * @param passengerName first key with reservation
     * @param originAirport second key with reservation
     * @param destinationAirport thrid key with reservation
     * @return true if reservation is successfully deleted
     */
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

    /**
     * Get an existing reservation.
     * @param passengerName first key for reservation
     * @param originAirport second key for reservation
     * @param destinationAirport third key for reservation
     * @return specific reservation object
     */
    public ArrayList<Reservation> getReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        ArrayList<Reservation> reservations = new ArrayList<>();
        if (originAirport != null && destinationAirport != null)
        {
            reservations.addAll(this.reservations.stream().filter(
                    currentReservation -> currentReservation.getPassenger().equals(passengerName) &&
                            currentReservation.getItinerary().getOriginAirport() == originAirport &&
                            currentReservation.getItinerary().getDestinationAirport() == destinationAirport
            ).collect(Collectors.toList()));
        } else
        {
            reservations.addAll(this.reservations.stream().filter(
                    currentReservation -> currentReservation.getPassenger().equals(passengerName) &&
                            (currentReservation.getItinerary().getOriginAirport() == originAirport ||
                                    currentReservation.getItinerary().getDestinationAirport() == destinationAirport)
            ).collect(Collectors.toList()));
        }

        return reservations;
    }

    /**
     * Get an existing reservation given a passenger.
     * @param passengerName first key for reservation
     * @return all reservation with passenger name key
     */
    public ArrayList<Reservation> getReservationsForPassenger(String passengerName)
    {
        return this.reservations.stream().filter(currentReservation ->
                currentReservation.getPassenger().equals(passengerName)
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Create a reservation.
     * @param passengerName first key for reservation
     * @param itinerary itinerary, that could be one or more, object
     * @return true if reservation object has been successfully created
     */
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
