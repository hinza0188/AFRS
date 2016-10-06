/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Airport;

public class GetReservation implements Request
{
    public GetReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {

    }

    public GetReservation(String passengerName, Airport originAirport)
    {

    }

    public GetReservation(String passengerName)
    {

    }

    @Override
    public String executeCommand()
    {
        return null;
    }
}
