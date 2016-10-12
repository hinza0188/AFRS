/**
 * Created by hetelek on 10/6/16.
 * Modifier : Jay
 * This class designs itinerary object under flyable interface
 */


package information;

import java.time.LocalTime;
import java.util.Arrays;

public class Itinerary implements Flyable{
    private Flight[] legs;
    private int identifier;

    public Itinerary(Flight[] legs, int identifier)
    {
        this.legs = legs;
        this.identifier = identifier;
    }

    public int getIdentifier()
    {
        return identifier;
    }

    @Override
    public LocalTime getDepartureTime(){
        // gets the first flight object in the itinerary and returns the departure time
        return this.legs[0].getDepartureTime();
    }

    @Override
    public LocalTime getArrivalTime(){
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getArrivalTime();
    }

    @Override
    public LocalTime getTrueDepartureTime(){
        // gets the first flight object in the itinerary and returns the departure time
        return this.legs[0].getTrueDepartureTime();
    }

    @Override
    public LocalTime getTrueArrivalTime(){
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getTrueArrivalTime();
    }

    @Override
    public Airport getOriginAirport(){
        // gets the first flight object in the itinerary and returns the origination airport
        return this.legs[0].getOriginAirport();
    }

    @Override
    public Airport getDestinationAirport(){
        // gets the last flight object in the itinerary and returns the arrival time
        int last = (this.legs.length) - 1;
        return this.legs[last].getDestinationAirport();
    }

    @Override
    public double getAirfare(){
        // loops through the itinerary containing flights and increment each iteration for the price calculation
        double tot = 0;
        for (Flight e: this.legs) {
            tot += e.getAirfare();
        }
        return tot;
    }

    @Override
    public String toString()
    {
        return "$" + this.getAirfare() + " "+ legs.length + Arrays.toString(legs);
    }
}
