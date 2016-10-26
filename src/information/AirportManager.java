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

    protected AirportManager()
    {
        this.airportData = new OfflineAirportData();
    }

    public void setOffline(boolean offline)
    {
        if (offline)
            this.airportData = new OfflineAirportData();
        else
            this.airportData = new OnlineAirportData();
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
