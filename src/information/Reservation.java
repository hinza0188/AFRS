/**
 * Created by hetelek on 10/6/16.
 */

package information;

public class Reservation
{
    private String passenger;
    private Itinerary itinerary;
<<<<<<< HEAD
    
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
=======

    public Itinerary getItinerary() {
        return itinerary;
    }

    public String getPassenger() {
        return passenger;
    }

    protected Reservation(String pass, Itinerary itin){
        this.passenger=pass;
        this.itinerary=itin;
>>>>>>> refs/remotes/origin/master
    }
}
