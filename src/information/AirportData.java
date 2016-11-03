package information;

/**
 * Interface for retreiving airport objects.
 */
public interface AirportData
{
    /**
     * Get an airport object corresponding to the given 3 letter code.
     * @param abbreviation The airport's 3 letter code.
     * @return
     */
    Airport getAirport(String abbreviation);
}
