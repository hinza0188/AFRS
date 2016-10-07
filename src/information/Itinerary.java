/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.LocalTime;

public class Itinerary implements Flyable
{
    private Flight[] legs;

    @Override
    public LocalTime getDepartureTime()
    {
        return null;
    }

    @Override
    public LocalTime getArrivalTime()
    {
        return null;
    }

    @Override
    public LocalTime getTrueDepartureTime()
    {
        return null;
    }

    @Override
    public LocalTime getTrueArrivalTime()
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
