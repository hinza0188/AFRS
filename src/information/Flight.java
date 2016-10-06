/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.ZonedDateTime;

public class Flight implements Flyable
{
    private int flightNumber;
    private Airport originAirport;
    private Airport destinationAirport;

    @Override
    public ZonedDateTime getDepartureTime()
    {
        return null;
    }

    @Override
    public ZonedDateTime getArrivalTime()
    {
        return null;
    }

    @Override
    public ZonedDateTime getTrueDepartureTime()
    {
        return null;
    }

    @Override
    public ZonedDateTime getTrueArrivalTime()
    {
        return null;
    }

    @Override
    public Airport getOriginAirport()
    {
        return null;
    }

    @Override
    public Airport getDestinationAirport()
    {
        return null;
    }

    @Override
    public float getAirfare()
    {
        return 0;
    }
}
