package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

public class AirportManager
{
    private static AirportManager singleton = null;
    private AirportData airportData;
    private boolean offline;

    protected AirportManager()
    {
        this.setOffline(false);
    }

    public void setOffline(boolean offline)
    {
        this.offline = offline;

        if (offline)
            this.airportData = new OfflineProxy();
        else
            this.airportData = new OnlineProxy();
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
