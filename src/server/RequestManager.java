/**
 * Created by hetelek on 10/6/16.
 */

package server;

import information.Reservation;
import information.ReservationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RequestManager
{
    private static RequestManager singleton = null;
    List<server.Request> commandStack = new Stack<server.Request>();

    protected RequestManager()  { }
    public static RequestManager getManager()
    {
        if(singleton == null)
            singleton = new RequestManager();

        return singleton;
    }
    
}
