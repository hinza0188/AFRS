package server;

import information.Itinerary;

public class MakeReservation implements Request
{
    private String passengerName;
    private Itinerary itinerary;
    private boolean undone = false;
    
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
