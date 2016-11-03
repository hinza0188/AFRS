package server;

import information.Airport;

/**
 * Get reservation command.
 */
public class GetReservation implements Request
{
    private final String passengerName;
    private Airport originAirport;
    private Airport destinationAirport;

    /**
     * Create a get reservation command with explicit parameters.
     * @param passengerName first key of reservation
     * @param originAirport second key of reservation
     * @param destinationAirport third key of reservation
     */
    public GetReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    /**
     * Create a get reservation command.
     * @param passengerName first key of reservation
     * @param originAirport second key of reservation
     */
    public GetReservation(String passengerName, Airport originAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
    }

    /**
     * Create a get reservation command given a passenger name.
     * @param passengerName first key of reservation
     */
    public GetReservation(String passengerName)
    {
        this.passengerName = passengerName;
    }

    /**
     * Execute the command.
     * @return success string if command got executed without error
     */
    @Override
    public String executeCommand()
    {
        return AFRS.getReservation(passengerName, originAirport, destinationAirport);
    }

    @Override
    public String undo() {
        return null;
    }

    @Override
    public String redo() {
        return null;
    }
}
