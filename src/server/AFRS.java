package server;

import helpers.ItinerarySortingAlgorithm;
import information.*;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

public class AFRS
{
    public static String makeReservation(String passengerName, Itinerary itinerary)
    {
        return ReservationManager.getManager().makeReservation(passengerName, itinerary);
    }

    public static String getItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        // get itineraries
        List<Itinerary> itineraries = ItineraryManager.getManager().getItineraries(originAirport,destinationAirport, maxConnections, sortingMethod);

        // create response string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("info " + itineraries.size() + "[");

        // create string for each itinerary
        int x = 1;
        for (Itinerary i : itineraries)
        {
            stringBuilder.append("\r\n" + x++ + " " + i.toString());
        }

        // finish
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String getReservation(String passengerName,Airport originAirport, Airport destinationAirport)
    {
        ArrayList<Reservation> reservations = new ArrayList<>();

        // start response string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("retrieve ");

        // get reservations
        if(originAirport == null && destinationAirport == null)
            reservations = ReservationManager.getManager().getReservationsForPassenger(passengerName);
        else
            reservations = ReservationManager.getManager().getReservation(passengerName, originAirport, destinationAirport);

        // add size to string
        stringBuilder.append(reservations.size() + "[");

        // put reservations in correct order
        for(Reservation r:reservations)
            stringBuilder.append("\r\n"+r.getItinerary().toString());

        // finish
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String deleteReservation(String passengerName,Airport originAirport, Airport destinationAirport)
    {
        return ReservationManager.getManager().deleteReservation(passengerName, originAirport, destinationAirport);
    }

    public static String getWeather(Airport airport)
    {
        // get weather/delays
        String weather = airport.getWeather();
        OffsetTime delayTime = airport.getTimeDelay();
        String airportCode = airport.getAbbreviation();

        // create weather string
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("weather, ");
        stringBuilder.append(airportCode + ", ");
        stringBuilder.append(weather + ", ");
        stringBuilder.append(delayTime + ", ");

        return stringBuilder.toString();
    }
}
