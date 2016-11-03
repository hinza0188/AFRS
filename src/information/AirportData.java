package information;

/**
 * Interface for retrieving airport objects.
 */
interface AirportData
{
    /**
     * Get an airport object corresponding to the given 3 letter code.
     * @param abbreviation The airport's 3 letter code.
     * @return : corresponding Airport Object
     */
    Airport getAirport(String abbreviation);
}
