package information;

import java.time.LocalTime;
import java.util.Arrays;

public class Itinerary implements Flyable
{
    private final Flight[] legs;

    /**
     * Create an itinerary from multiple flights.
     * @param legs The flights in chronological order that this itinerary's composed of.
     */
    public Itinerary(Flight[] legs)
    {
        this.legs = legs;
    }

    /**
     * Get the itinerary's departure time.
     * @return
     */
    @Override
    public LocalTime getDepartureTime()
    {
        // gets the first flight object in the itinerary and returns the departure time
        return this.legs[0].getDepartureTime();
    }

    /**
     * Get the itinerary's arrival time.
     * @return
     */
    @Override
    public LocalTime getArrivalTime()
    {
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getArrivalTime();
    }

    /**
     * Get the itinerary's departure time including delays.
     * @return
     */
    @Override
    public LocalTime getTrueDepartureTime()
    {
        // gets the first flight object in the itinerary and returns the departure time
        return this.legs[0].getTrueDepartureTime();
    }

    /**
     * Get the itinerary's arrival time including delays.
     * @return
     */
    @Override
    public LocalTime getTrueArrivalTime()
    {
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getTrueArrivalTime();
    }

    /**
     * Get the origin airport of the itinerary.
     * @return
     */
    @Override
    public Airport getOriginAirport()
    {
        // gets the first flight object in the itinerary and returns the origination airport
        return this.legs[0].getOriginAirport();
    }

    /**
     * Get the destination airport of the itinerary.
     * @return
     */
    @Override
    public Airport getDestinationAirport()
    {
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getDestinationAirport();
    }

    /**
     * Get the total cost of all the flights in the itinerary.
     * @return
     */
    @Override
    public double getAirfare()
    {
        // loops through the itinerary containing flights and increment each iteration for the price calculation
        double tot = 0;
        for (Flight e : this.legs)
        {
            tot += e.getAirfare();
        }
        return tot;
    }

    @Override
    public String toString()
    {
        return "$" + this.getAirfare() + " " + legs.length + Arrays.toString(legs);
    }
}
