package server;

import information.Itinerary;

/**
 * Make reservation command.
 */
public class MakeReservation implements Request
{
    private String passengerName;
    private Itinerary itinerary;

    /**
     * Create a make reservation command.
     * @param passengerName
     * @param itinerary
     */
    public MakeReservation(String passengerName, Itinerary itinerary)
    {
        this.passengerName = passengerName;
        this.itinerary = itinerary;
    }

    /**
     * Execute the command (make the reservation).
     * @return
     */
    @Override
    public String executeCommand()
    {
        return AFRS.makeReservation(passengerName, itinerary);
    }

    /**
     * Undo the make reservation command.
     * @return
     */
    public String undo()
    {
        StringBuilder sb = new StringBuilder();
        AFRS.deleteReservation(this.passengerName, this.itinerary.getOriginAirport(), this.itinerary.getDestinationAirport());
        sb.append("undo, reserve, ");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }

    /**
     * Redo the undid make reservation command.
     * @return
     */
    public String redo()
    {
        StringBuilder sb = new StringBuilder();
        AFRS.makeReservation(passengerName, itinerary);
        sb.append("redo, reserve, ");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }
}
