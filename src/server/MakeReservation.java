/**
 * Created by hetelek on 10/6/16.
 * Now star coding on this project directory and let me show you how to commit and push
 * I gotta get going soonish- I'm on-campus and I'm going home soon
 * sounds good.
 * step0. write code
 * step1. team -> git -> add all
 * step2. team -> git -> commit all
 * step3. push (probably don't have to do that all the time)
 * step4. let me know if you are all done. then I check the code and make sure everything merges fine
 * conclusion: go coding go!
 */

package server;

import information.Itinerary;

public class MakeReservation implements Request
{
    String passengerName;
    Itinerary itinerary;
    boolean undone = false;
    
    public MakeReservation(String passengerName, Itinerary itinerary)
    {
        this.passengerName = passengerName;
        this.itinerary = itinerary;
    }

    @Override
    public String executeCommand()
    {
        RequestManager.getManager().commandStack.add(this);
        return AFRS.makeReservation(passengerName, itinerary);
    }
    
    public String undo()
    {
        return null;   
    }
}
