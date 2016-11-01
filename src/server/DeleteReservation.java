package server;

import information.Airport;
import information.Itinerary;

public class DeleteReservation implements Request
{
    private String passengerName;
    private Airport originAirport;
    private Airport destinationAirport;
    private Itinerary itinerary;



    public DeleteReservation(String passengerName, Airport originAirport, Airport destinationAirport)
    {
        this.passengerName = passengerName;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.itinerary=AFRS.getItineraryObjectFromReservation(this.passengerName, this.originAirport, this.destinationAirport);
    }

    @Override
    public String executeCommand()
    {
        return AFRS.deleteReservation(this.passengerName, this.originAirport, this.destinationAirport);
    }

    @Override
    public String undo() {
        StringBuilder sb=new StringBuilder();
        AFRS.makeReservation(passengerName,itinerary);
        sb.append("undo, delete,");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }

    @Override
    public String redo() {
        StringBuilder sb=new StringBuilder();
        AFRS.deleteReservation(this.passengerName, this.originAirport, this.destinationAirport);
        sb.append("redo, delete,");
        sb.append(this.passengerName);
        sb.append(this.itinerary.toString());
        return sb.toString();
    }
}
