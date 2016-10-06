/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.util.ArrayList;

public class ReservationManager
{
    private static ReservationManager singleton = null;
    private ArrayList<Reservation> reservations;

    protected ReservationManager()  { }
    public static ReservationManager getManager()
    {
        if(singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }
}
