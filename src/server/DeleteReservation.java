package server;

import information.Airport;
import information.Itinerary;

/**
 * Delete reservation command.
 */
public class DeleteReservation implements Request
{
    private final String passengerName;
    private final Airport originAirport;
    private final Airport destinationAirport;
    private final Itinerary itinerary;

    /**
     * Create the command to delete.
     * @param passengerName first key of reservation
     * @param originAirport second key of reservation
     * @param destinationAirport third key of reservation
     */
    DeleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.itinerary = AFRS.getItineraryObjectFromReservation(this.passengerName, this.originAirport, this.destinationAirport);
    }

    /**
     * Execute the command.
     * @return success string if reservation is deleted
     */
    @Override
    public String executeCommand()
    {
        return AFRS.deleteReservation(this.passengerName, this.originAirport, this.destinationAirport);
    }

    /**
     * Undo the command.
     * @return success of undo command execution
     */
    @Override
    public String undo()
    {
        StringBuilder sb = new StringBuilder();
        AFRS.makeReservation(passengerName,itinerary);
        sb.append("undo, delete, ");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }

    /**
     * Redo the undid command.
     * @return success of redo command execution
     */
    @Override
    public String redo()
    {
        StringBuilder sb = new StringBuilder();
        AFRS.deleteReservation(this.passengerName, this.originAirport, this.destinationAirport);
        sb.append("redo, delete, ");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }
}
