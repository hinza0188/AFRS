/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.util.ArrayList;

public class AirportManager
{
    private static AirportManager singleton = null;
    private ArrayList<Airport> airports;

    protected AirportManager()  { }
    public static AirportManager getManager()
    {
        if(singleton == null)
            singleton = new AirportManager();

        return singleton;
    }
}
