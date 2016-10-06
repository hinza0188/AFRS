/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Airport;

public class DeleteReservation implements Request
{
    public DeleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {

    }

    public DeleteReservation(String passengerName, Airport originAirport)
    {

    }

    public DeleteReservation(String passengerName)
    {

    }

    @Override
    public String executeCommand()
    {
        return null;
    }
}
