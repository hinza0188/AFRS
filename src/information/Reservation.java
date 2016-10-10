/**
 * Created by hetelek on 10/6/16.
 */

package information;

public class Reservation
{
    private String passenger;
    private Itinerary itinerary;
    
    public Reservation(String passenger, Itinerary itinerary){
        this.itinerary = itinerary;
        this.passenger = passenger;
    }
    
    public String getPassengerName(){
        return this.passenger;
    }
    
    public Airport getOriginAirport(){
        return this.itinerary.getOriginAirport();
    }
    
    public Airport getDestinationAirport(){
        return this.itinerary.getDestinationAirport();
    }
}
