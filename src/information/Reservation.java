package information;

public class Reservation
{
    private String passenger;
    private Itinerary itinerary;

    /**
     * Create a reservation.
     * @param passenger The passenger's name.
     * @param itinerary The itinerary to reserve.
     */
    protected Reservation(String passenger, Itinerary itinerary)
    {
        this.itinerary = itinerary;
        this.passenger = passenger;
    }

    /**
     * Get the passengers name.
     * @return the passenger's name
     */
    String getPassengerName()
    {
        return this.passenger;
    }

    /**
     * The reservation's origin airport.
     * @return final origin airport object
     */
    Airport getOriginAirport()
    {
        return this.itinerary.getOriginAirport();
    }

    /**
     * The reservation's destination airport.
     * @return final destination airport object
     */
    Airport getDestinationAirport()
    {
        return this.itinerary.getDestinationAirport();
    }

    /**
     * Get the itinerary.
     * @return specific itinerary for the reservation
     */
    public Itinerary getItinerary()
    {
        return itinerary;
    }

    String getPassenger()
    {
        return passenger;
    }

}
