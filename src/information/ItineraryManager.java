package information;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : Jay
 * This class is made of singleton pattern that
 * takes care of sorting algorithms and manages
 * each itinerary objects
 */
public class ItineraryManager {
    private static ItineraryManager singleton = null;
    private List<Itinerary> itineraries = new ArrayList<Itinerary>();
    private int nextItineraryId = 0;

    protected ItineraryManager() { }
    public static ItineraryManager getManager() {
        if(singleton == null) {
            singleton = new ItineraryManager();
        }
        return singleton;
    }

    public Itinerary getItineraryWithIdentifier(int identifier)
    {
        return this.itineraries.get(identifier);
    }

    public List<Itinerary> getItineraries(Airport originAirport, Airport destinationAirport, int maxConnections)
    {
        List<Flight> flights = FlightManager.getManager().flightsLeavingAirport(originAirport);
        for (Flight flight : flights)
        {
            List<Flight> flightsList = Arrays.asList(new Flight[] { flight });
            List<Itinerary> newItineraries = this.getItineraries(flightsList, destinationAirport, maxConnections, 0);
            this.itineraries.addAll(newItineraries);
        }

        return this.itineraries;
    }

    private List<Itinerary> getItineraries(List<Flight> currentFlights, Airport destinationAirport, int maxConnections, int depth)
    {
        // check if we're over the max connections
        if (depth > maxConnections)
            return new ArrayList<Itinerary>();

        List<Itinerary> newItineraries = new ArrayList<Itinerary>();
        Flight lastFlight = currentFlights.get(currentFlights.size() - 1);

        // check if we are leaving
        if (lastFlight.getDestinationAirport() == destinationAirport)
        {
            // create new itinerary
            Itinerary itinerary = new Itinerary(currentFlights.toArray(new Flight[] { }), nextItineraryId++);
            newItineraries.add(itinerary);
        }

        // loop through each flight
        List<Flight> flightsLeavingDestination = FlightManager.getManager().flightsLeavingAirport(lastFlight.getDestinationAirport());
        for (Flight flightLeaving : flightsLeavingDestination)
        {
            // create new list
            List<Flight> newFlightsList = new ArrayList<Flight>(currentFlights);
            newFlightsList.add(flightLeaving);

            // do same thing with greater depth
            newItineraries.addAll(getItineraries(newFlightsList, destinationAirport, maxConnections, depth + 1));
        }

        return newItineraries;
    }
}
