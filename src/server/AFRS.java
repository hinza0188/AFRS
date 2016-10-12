package server; /**
 * Created by Yongki Jay An
 */

import helpers.ItinerarySortingAlgorithm;
import information.*;

import java.util.ArrayList;
import java.util.List;

public class AFRS
{
    public static String makeReservation(String passengerName, Itinerary itinerary){
        return ReservationManager.getManager().makeReservation(passengerName, itinerary);
    }

    public static String getItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod){
        List<Itinerary> itins = ItineraryManager.getManager().getItineraries(originAirport,destinationAirport, maxConnections);
        itins = sortingMethod.sortItineraries(itins);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("info "+itins.size()+"[");

        for (Itinerary i : itins)
            stringBuilder.append("\r\n"+ i.getIdentifier() + " " + i.toString());

        stringBuilder.append("]");
        return stringBuilder.toString();

    }
    public static String getReservation(String passengerName,Airport originAirport, Airport destinationAirport){
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
    public static String deleteReservation(String passengerName,Airport originAirport, Airport destinationAirport){
        return ReservationManager.getManager().deleteReservation(passengerName,originAirport,destinationAirport);
    }
    public static String getWeather(String airportCode){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("weather, ");
        stringBuilder.append(airportCode + ", ");
        stringBuilder.append(AirportManager.getManager().getAirport(airportCode).getWeather()+", ");
        stringBuilder.append(AirportManager.getManager().getAirport(airportCode).getTimeDelay()+ ", ");
        return stringBuilder.toString();
    }
}
