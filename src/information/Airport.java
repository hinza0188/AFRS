/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.OffsetDateTime;

public class Airport
{
    private String abbreviation;
    private String cityName;
    private String weather;

    private OffsetDateTime timeDelay;

    public Airport(String abbreviation, String cityName)
    {
        this.abbreviation = abbreviation;
        this.cityName = cityName;
    }

    public String toString()
    {
        return cityName + " (" + abbreviation + ")";
    }
}
