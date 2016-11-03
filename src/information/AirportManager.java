package information;

/**
 * Manages and creates a list of online and offline airports.
 */
public class AirportManager
{
    private static AirportManager singleton = null;
    private AirportData airportData;
    private boolean offline;

    /**
     * Intialize an airport manager.
     */
    protected AirportManager()
    {
        this.setOffline(true);
    }

    /**
     *
     * @param offline
     */
    public void setOffline(boolean offline)
    {
        this.offline = offline;

        // update proxy from state
        if (offline)
            this.airportData = new OfflineProxy();
        else
            this.airportData = new OnlineProxy();
    }

    /**
     * Returns true if data is coming from a local file.
     * @return
     */
    public boolean isOffline()
    {
        return this.offline;
    }

    /**
     * Get the global airport manager.
     * @return
     */
    public static AirportManager getManager()
    {
        if (AirportManager.singleton == null)
        {
            // create manager (and read airports/weather)
            AirportManager.singleton = new AirportManager();
        }

        return AirportManager.singleton;
    }

    /**
     * Get an airport object.
     * @param abbreviation The airport's 3 letter abbreviation.
     * @return
     */
    public Airport getAirport(String abbreviation)
    {
        return this.airportData.getAirport(abbreviation);
    }
}
