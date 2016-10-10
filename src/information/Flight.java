/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.LocalTime;
import java.time.OffsetTime;

public class Flight implements Flyable
{
    private int flightNumber;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double airfare;

    protected Flight(int flightNumber, Airport originAirport, Airport destinationAirport, LocalTime departureTime,
                     LocalTime arrivalTime, double airfare)
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
        return this.arrivalTime;
    }

    @Override
    public LocalTime getTrueDepartureTime()
    {
        // This seems dumb by adding hours, minutes, and seconds,
        // but java is too dumb to run '+' operation
        LocalTime current = getArrivalTime();
        OffsetTime delta = getOriginAirport().getTimeDelay();
        current.plusHours(delta.getHour());
        current.plusMinutes(delta.getMinute());
        current.plusSeconds(delta.getSecond());
        return current;
    }

    @Override
    public LocalTime getTrueArrivalTime()
    {
        // This seems dumb by adding hours, minutes, and seconds,
        // but java is too dumb to run '+' operation
        LocalTime current = getArrivalTime();
        OffsetTime delta = getOriginAirport().getTimeDelay();
        current.plusHours(delta.getHour());
        current.plusMinutes(delta.getMinute());
        current.plusSeconds(delta.getSecond());
        return current;
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
