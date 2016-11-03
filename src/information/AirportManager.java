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
     * Initialize an airport manager.
     */
    private AirportManager()
    {
        this.setOffline(true);
    }

    /**
     * use boolean representation for setting system in online <-> offline
     * @param offline: set to true if status is declared offline
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
     * Identify if airport is pointing at online or offline
     * @return : true if data is coming from a local file.
     */
    public boolean isOffline()
    {
        return this.offline;
    }

    /**
     * Get the global airport manager.
     * @return : the global airport manager
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
     * @return : Get the corresponding Airport Object
     */
    public Airport getAirport(String abbreviation)
    {
        return this.airportData.getAirport(abbreviation);
    }
}
