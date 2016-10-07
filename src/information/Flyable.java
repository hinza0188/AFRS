/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.LocalTime;

public interface Flyable
{
    LocalTime getDepartureTime();
    LocalTime getArrivalTime();

    LocalTime getTrueDepartureTime();
    LocalTime getTrueArrivalTime();

    Airport getOriginAirport();
    Airport getDestinationAirport();

    double getAirfare();
}
