package server;

import information.Itinerary;

/**
 * Make reservation command.
 */
class MakeReservation implements Request
{
    private final String passengerName;
    private final Itinerary itinerary;

    /**
     * Create a make reservation command.
     * @param passengerName first key of reservation
     * @param itinerary chain of flight data in itinerary object
     */
    MakeReservation(String passengerName, Itinerary itinerary)
    {
        this.passengerName = passengerName;
        this.itinerary = itinerary;
    }

    /**
     * Execute the command (make the reservation).
     * @return success string if executed without error
     */
    @Override
    public String executeCommand()
    {
        return AFRS.makeReservation(passengerName, itinerary);
    }

    /**
     * Undo the make reservation command.
     * @return success string if executed without error
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
     * @return success string if executed without error
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
