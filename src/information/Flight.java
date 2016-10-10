/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.LocalTime;

public class Flight implements Flyable
{
    private int flightNumber;
    private Airport originAirport;
    private Airport destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private double airfare;

    protected Flight(int flightNumber, Airport originAirport, Airport destinationAirport, String departureTime,
                     String arrivalTime, double airfare)
    {
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airfare = airfare;
    }

    @Override
    public LocalTime getDepartureTime()
    {
        return this.departureTime;
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
        return this.originAirport;
    }

    @Override
    public Airport getDestinationAirport()
    {
        return this.destinationAirport;
    }

    @Override
    public double getAirfare()
    {
        return this.airfare;
    }

    @Override
    public String toString()
    {
        return this.originAirport.getAbbreviation() + " -> " + this.destinationAirport.getAbbreviation();
    }
}
