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
     * Returns the passengers name.
     * @return
     */
    public String getPassengerName()
    {
        return this.passenger;
    }

    /**
     * The reservation's origin airport.
     * @return
     */
    public Airport getOriginAirport()
    {
        return this.itinerary.getOriginAirport();
    }

    /**
     * The reservation's destination airport.
     * @return
     */
    public Airport getDestinationAirport()
    {
        return this.itinerary.getDestinationAirport();
    }

    /**
     * Get the itinerary.
     * @return
     */
    public Itinerary getItinerary()
    {
        return itinerary;
    }

    public String getPassenger()
    {
        return passenger;
    }

}
