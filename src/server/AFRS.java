package server; /**
 * Created by Yongki Jay An
 */

import helpers.ItinerarySortingAlgorithm;
import information.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AFRS
{
    protected String makeReservation(String passengerName, Itinerary itinerary){
        return ReservationManager.getManager().makeReservation(passengerName, itinerary);
    }
    protected String getItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod){
        List<Itinerary> Itins = ItineraryManager.getManager().getItineraries(originAirport,destinationAirport, maxConnections);
        Itins=sortingMethod.sortItineraries(Itins);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("info "+Itins.size()+"[");

        for (Itinerary i:Itins){
            stringBuilder.append("\r\n"+ i.getIdentifier()+ " " + i.toString());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();

    }
    protected String getReservation(String passengerName,Airport originAirport, Airport destinationAirport){
        ArrayList<Reservation> reservations=new ArrayList<Reservation>();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("retrieve ");
        if(originAirport==null&&destinationAirport==null){
            reservations=ReservationManager.getManager().getReservationsForPassenger(passengerName);

        }
        else{
            reservations=ReservationManager.getManager().getReservation(passengerName,originAirport,destinationAirport);
        }
        stringBuilder.append(reservations.size()+"[");
        //put reservations in correct order

        for(Reservation r:reservations){
            stringBuilder.append("\r\n"+r.getItinerary().toString());

        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    protected String deleteReservation(String passengerName,Airport originAirport, Airport destinationAirport){
        return ReservationManager.getManager().deleteReservation(passengerName,originAirport,destinationAirport);
    }
    protected String getWeather(String airportCode){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("weather, ");
        stringBuilder.append(airportCode + ", ");
        stringBuilder.append(AirportManager.getManager().getAirport(airportCode).getWeather()+", ");
        stringBuilder.append(AirportManager.getManager().getAirport(airportCode).getTimeDelay()+ ", ");
        return stringBuilder.toString();
    }
}
