/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Airport;

public class DeleteReservation implements Request
{
    private String passengerName;
    private Airport originAirport;
    private Airport destinationAirport;

    public DeleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    @Override
    public String executeCommand()
    {
        return AFRS.deleteReservation(this.passengerName, this.originAirport, this.destinationAirport);
    }
}
