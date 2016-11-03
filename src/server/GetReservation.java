package server;

import information.Airport;

/**
 * Get reservation command.
 */
public class GetReservation implements Request
{
    private String passengerName;
    private Airport originAirport;
    private Airport destinationAirport;

    /**
     * Create a get reservation command with explicit parameters.
     * @param passengerName
     * @param originAirport
     * @param destinationAirport
     */
    public GetReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    /**
     * Create a get reservation command.
     * @param passengerName
     * @param originAirport
     */
    public GetReservation(String passengerName, Airport originAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
    }

    /**
     * Create a get reservation command given a passenger name.
     * @param passengerName
     */
    public GetReservation(String passengerName)
    {
        this.passengerName = passengerName;
    }

    /**
     * Execute the command.
     * @return
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
