/**
 * Created by hetelek on 10/6/16.
 */

package information;

import helpers.CSVReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AirportManager
{
    private static String AIRPORT_FILE_PATH = "data/airports.txt";
    private static AirportManager singleton = null;
    private ArrayList<Airport> airports;

    protected AirportManager()  { }
    public static AirportManager getManager()
    {
        if(AirportManager.singleton == null)
        {
            // create manager (and read airports)
            AirportManager.singleton = new AirportManager();
            AirportManager.singleton.readAirportsFromFile(AIRPORT_FILE_PATH);
        }

        return AirportManager.singleton;
    }

    private void readAirportsFromFile(String filePath)
    {
        // create new array list
        this.airports = new ArrayList<Airport>();

        try
        {
            // open file
            CSVReader airportFile = new CSVReader(filePath);
            airportFile.open();

            // read airports
            String[] data;
            while ((data = airportFile.readLine()) != null)
            {
                String abbreviation = data[0];
                String cityName = data[1];

                this.airports.add(new Airport(abbreviation, cityName));
            }

            // close file
            airportFile.close();
        }
        catch (FileNotFoundException ex) { }
    }
}
