/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Airport;

public class GetReservation implements Request
{
    private String passengerName;
    private Airport originAirport;
    private Airport destinationAirport;

    public GetReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    public GetReservation(String passengerName, Airport originAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
    }

    public GetReservation(String passengerName)
    {
        this.passengerName = passengerName;
    }

    @Override
    public String executeCommand()
    {
        return AFRS.getReservation(passengerName, originAirport, destinationAirport);
    }
}
