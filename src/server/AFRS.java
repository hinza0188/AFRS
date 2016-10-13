package server;

import helpers.ItinerarySortingAlgorithm;
import information.*;

import java.time.OffsetTime;
import java.util.List;

public class AFRS
{
    public static String makeReservation(String passengerName, Itinerary itinerary)
    {
        boolean success = ReservationManager.getManager().makeReservation(passengerName, itinerary);
        if (success)
            return "reserve,successful";
        else
            return "error,duplicate reservation";
    }

    public static String getItinerary(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        // get itineraries
        List<Itinerary> itineraries = ItineraryManager.getManager().getItineraries(originAirport,destinationAirport, maxConnections, sortingMethod);

        // create response string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("info ").append(itineraries.size()).append("[");

        // create string for each itinerary
        int x = 1;
        for (Itinerary i : itineraries)
        {
            stringBuilder.append("\r\n").append(x++).append(" ").append(i);
        }

        // finish
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String getReservation(String passengerName,Airport originAirport, Airport destinationAirport)
    {
        List<Reservation> reservations;

        // start response string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("retrieve ");

        // get reservations
        if(originAirport == null && destinationAirport == null)
            reservations = ReservationManager.getManager().getReservationsForPassenger(passengerName);
        else
            reservations = ReservationManager.getManager().getReservation(passengerName, originAirport, destinationAirport);

        // add size to string
        stringBuilder.append(reservations.size()).append("[");

        // put reservations in correct order
        for(Reservation r:reservations)
            stringBuilder.append("\r\n").append(r.getItinerary());

        // finish
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String deleteReservation(String passengerName,Airport originAirport, Airport destinationAirport)
    {
        boolean success = ReservationManager.getManager().deleteReservation(passengerName, originAirport, destinationAirport);
        if (success)
            return "delete,successful";
        else
            return "error,reservation not found";
    }

    public static String getWeather(Airport airport)
    {
        // get weather/delays
        String weather = airport.getWeather();
        OffsetTime delayTime = airport.getTimeDelay();
        String airportCode = airport.getAbbreviation();

        // create weather string
        int totalMinutes = (delayTime.getMinute() + delayTime.getHour() * 60);
        String stringBuilder = "weather," + airportCode + "," + weather + "," + totalMinutes;

        return stringBuilder;
    }
}
