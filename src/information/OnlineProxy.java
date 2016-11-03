package information;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.net.URL;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import java.util.HashMap;
import java.util.Map;

/**
 * Reads and manages a list of online airports.
 */
class OnlineProxy implements AirportData
{
    private static final String URL_FORMAT = "http://services.faa.gov/airport/status/%s?format=application/xml";
    private Map<String, Airport> airportCache = new HashMap<>();

    /**
     * Get the airport from the web service and return an airport object.
     * @param abbreviation The airport's 3 letter code.
     * @return Corresponding airport object
     */
    public Airport getAirport(String abbreviation)
    {
        try
        {
            // check for cached airport
            if (airportCache.containsKey(abbreviation))
                return this.airportCache.get(abbreviation);

            // create document
            String airportUrl = String.format(URL_FORMAT, abbreviation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(airportUrl).openStream());

            // get data
            String weather = doc.getElementsByTagName("Weather").item(1).getTextContent();
            String cityName = doc.getElementsByTagName("City").item(0).getTextContent();
            String avgDelay = doc.getElementsByTagName("AvgDelay").item(0).getTextContent();

            // parse delay
            int delayInMinutes = 0;
            if (avgDelay.length() > 0)
                delayInMinutes = Integer.parseInt(avgDelay.split(" ")[0]);

            // create airport, and cache
            Airport airport = new Airport(abbreviation, cityName);
            airport.setWeather(new String[] { weather });
            airport.setTimeDelay(OffsetTime.of(delayInMinutes / 60, delayInMinutes % 60, 0, 0, ZoneOffset.UTC));

            this.airportCache.put(abbreviation, airport);

            return airport;
        }
        catch (Exception ignored) { }

        return null;
    }
}
