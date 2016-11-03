package information;

import helpers.ItinerarySortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    private ItineraryManager()
    {
    }

    /**
     * Get the global itinerary manager.
     * @return global itinerary manager
     */
    public static ItineraryManager getManager()
    {
        if (singleton == null)
            singleton = new ItineraryManager();

        return singleton;
    }

    /**
     * Get the itinerary with the given identifier.
     * @param identifier The itinerary's identifier.
     * @return speicific itinerary object
     */
    public Itinerary getItineraryWithIdentifier(int identifier)
    {
        // find itinerary
        if (identifier >= 1 && identifier <= this.itineraries.size())
            return this.itineraries.get(identifier - 1);

        return null;
    }

    /**
     * Get all itineraries with the given information.
     * @param originAirport target departing airport object
     * @param destinationAirport target arriving airport object
     * @param maxConnections depends on user input, from 0 to up to 2
     * @param sortingMethod sorting algorithm that could be specified by user
     * @return chain of itineraries
     */
    public List<Itinerary> getItineraries(Airport originAirport, Airport destinationAirport, int maxConnections, ItinerarySortingAlgorithm sortingMethod)
    {
        this.itineraries.clear();

        // find itineraries
        List<Flight> flights = FlightManager.getManager().flightsLeavingAirport(originAirport);
        for (Flight flight : flights)
        {
            List<Flight> flightsList = Collections.singletonList(flight);
            List<Itinerary> newItineraries = this.getItineraries(flightsList, destinationAirport, maxConnections, 0);
            this.itineraries.addAll(newItineraries);
        }

        // sort
        sortingMethod.sortItineraries(this.itineraries);

        return this.itineraries;
    }

    /**
     * Get all itineraries with the given information.
     * @param currentFlights flight object
     * @param destinationAirport target arriving airport object
     * @param maxConnections depends on user input, from 0 to up to 2
     * @param depth left over connection
     * @return find the itinerary with depth based search
     */
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
