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
     * @param flightNumber identification number of the flight
     * @param originAirport departing airport of the flight
     * @param destinationAirport target airport of the flight
     * @param departureTime departing time of the flight
     * @param arrivalTime arriving time of the flight
     * @param airfare price of the flight
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
     * @return readable local time for departure
     */
    @Override
    public LocalTime getDepartureTime()
    {
        return this.departureTime;
    }

    /**
     * Get the time of arrival.
     * @return readable local time for arrival
     */
    @Override
    public LocalTime getArrivalTime()
    {
        return this.arrivalTime;
    }

    /**
     * Get the time of departure including the delay time.
     * @return readable local time after delay time calculated for departure
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
     * @return readable local time after delay time calculated for arrival
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
     * @return target airport object
     */
    @Override
    public Airport getOriginAirport()
    {
        return this.originAirport;
    }

    /**
     * Get the destination airport.
     * @return target airport object
     */
    @Override
    public Airport getDestinationAirport()
    {
        return this.destinationAirport;
    }

    /**
     * Get the price for this flight.
     * @return precise value of ticket
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
