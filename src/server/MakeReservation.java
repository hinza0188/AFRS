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
        return AFRS.makeReservation(passengerName, itinerary);
    }

    public String undo()
    {
        StringBuilder sb=new StringBuilder();
        AFRS.deleteReservation(this.passengerName, this.itinerary.getOriginAirport(), this.itinerary.getDestinationAirport());
        sb.append("undo, reserve,");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }
    public String redo(){
        StringBuilder sb=new StringBuilder();
        AFRS.makeReservation(passengerName, itinerary);
        sb.append("redo, reserve,");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }
}
