package information;

import java.time.LocalTime;
import java.time.OffsetTime;

/**
 * Represents a flight: origin, destination, departure/arrival time, and airfare.
 */
public class Flight implements Flyable
{
    private int flightNumber;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double airfare;

    /**
     * Create a flight object.
     * @param flightNumber
     * @param originAirport
     * @param destinationAirport
     * @param departureTime
     * @param arrivalTime
     * @param airfare
     */
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

    /**
     * Get the time of departure.
     * @return
     */
    @Override
    public LocalTime getDepartureTime()
    {
        return this.departureTime;
    }

    /**
     * Get the time of arrival.
     * @return
     */
    @Override
    public LocalTime getArrivalTime()
    {
        return this.arrivalTime;
    }

    /**
     * Get the time of departure including the delay time.
     * @return
     */
    @Override
    public LocalTime getTrueDepartureTime()
    {
        LocalTime current = this.getDepartureTime();
        OffsetTime delta = this.getOriginAirport().getTimeDelay();

        current.plusHours(delta.getHour());
        current.plusMinutes(delta.getMinute());
        current.plusSeconds(delta.getSecond());

        return current;
    }

    /**
     * Get the time of arrival including the delay time.
     * @return
     */
    @Override
    public LocalTime getTrueArrivalTime()
    {
        LocalTime current = this.getArrivalTime();
        OffsetTime delta = this.getOriginAirport().getTimeDelay();

        current.plusHours(delta.getHour());
        current.plusMinutes(delta.getMinute());
        current.plusSeconds(delta.getSecond());

        return current;
    }

    /**
     * Get the origin airport.
     * @return
     */
    @Override
    public Airport getOriginAirport()
    {
        return this.originAirport;
    }

    /**
     * Get the destination airport.
     * @return
     */
    @Override
    public Airport getDestinationAirport()
    {
        return this.destinationAirport;
    }

    /**
     * Get the price for this flight.
     * @return
     */
    @Override
    public double getAirfare()
    {
        return this.airfare;
    }

    @Override
    public String toString()
    {
        return "[ " + this.flightNumber + ", " + this.originAirport.getAbbreviation() + ", " + this.departureTime + ", " + this.destinationAirport.getAbbreviation() + ", " + this.arrivalTime + " ]";
    }
}
