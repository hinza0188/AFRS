/**
 * Created by hetelek on 10/6/16.
 */

package information;

public class Reservation
{
    private String passenger;
    private Itinerary itinerary;

    public Itinerary getItinerary() {
        return itinerary;
    }

    public String getPassenger() {
        return passenger;
    }

    protected Reservation(String pass, Itinerary itin){
        this.passenger=pass;
        this.itinerary=itin;
    }
}
