/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.util.ArrayList;

public class FlightManager
{
    private static FlightManager singleton = null;
    private ArrayList<Flight> flights;

    protected FlightManager()  { }
    public static FlightManager getManager()
    {
        if(singleton == null)
            singleton = new FlightManager();

        return singleton;
    }
}
