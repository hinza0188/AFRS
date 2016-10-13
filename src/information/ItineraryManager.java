package information;

import helpers.ItinerarySortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : Jay
 * This class is made of singleton pattern that
 * takes care of sorting algorithms and manages
 * each itinerary objects
 */
public class ItineraryManager
{
    private static ItineraryManager singleton = null;
    private List<Itinerary> itineraries = new ArrayList<>();

    protected ItineraryManager()
    {
    }

    public static ItineraryManager getManager()
    {
        if (singleton == null)
            singleton = new ItineraryManager();

        return singleton;
    }

    public Itinerary getItineraryWithIdentifier(int identifier)
    {
        // find itinerary
        if (identifier >= 1 && identifier <= this.itineraries.size())
            return this.itineraries.get(identifier - 1);

        return null;
    }

    public List<Itinerary> getItineraries(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        this.itineraries.clear();

        // find itineraries
        List<Flight> flights = FlightManager.getManager().flightsLeavingAirport(originAirport);
        for (Flight flight : flights)
        {
            List<Flight> flightsList = Arrays.asList(flight);
            List<Itinerary> newItineraries = this.getItineraries(flightsList, destinationAirport, maxConnections, 0);
            this.itineraries.addAll(newItineraries);
        }

        // sort
        sortingMethod.sortItineraries(this.itineraries);

        return this.itineraries;
    }

    private List<Itinerary> getItineraries(List<Flight> currentFlights, Airport destinationAirport, int maxConnections, int depth)
    {
        // check if we're over the max connections
        if (depth > maxConnections)
            return new ArrayList<>();

        List<Itinerary> newItineraries = new ArrayList<>();
        Flight lastFlight = currentFlights.get(currentFlights.size() - 1);

        // check if we are leaving
        if (lastFlight.getDestinationAirport() == destinationAirport)
        {
            // create new itinerary
            Itinerary itinerary = new Itinerary(currentFlights.toArray(new Flight[]{}));
            newItineraries.add(itinerary);
        }

        // loop through each flight
        List<Flight> flightsLeavingDestination = FlightManager.getManager().flightsLeavingAirport(lastFlight.getDestinationAirport());
        for (Flight flightLeaving : flightsLeavingDestination)
        {
            // create new list
            List<Flight> newFlightsList = new ArrayList<>(currentFlights);
            newFlightsList.add(flightLeaving);

            // do same thing with greater depth
            newItineraries.addAll(getItineraries(newFlightsList, destinationAirport, maxConnections, depth + 1));
        }

        return newItineraries;
    }
}
