package information;

public class AirportManager
{
    private static AirportManager singleton = null;
    private AirportData airportData;
    private boolean offline;

    protected AirportManager()
    {
        this.setOffline(true);
    }

    public void setOffline(boolean offline)
    {
        this.offline = offline;

        // update proxy from state
        if (offline)
            this.airportData = new OfflineProxy();
        else
            this.airportData = new OnlineProxy();
    }

    public boolean isOffline()
    {
        return this.offline;
    }

    public static AirportManager getManager()
    {
        if (AirportManager.singleton == null)
        {
            // create manager (and read airports/weather)
            AirportManager.singleton = new AirportManager();
        }

        return AirportManager.singleton;
    }

    public Airport getAirport(String abbreviation)
    {
        return this.airportData.getAirport(abbreviation);
    }
}
