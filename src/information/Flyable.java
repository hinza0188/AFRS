/**
 * Created by hetelek on 10/6/16.
 */

package information;

import java.time.ZonedDateTime;

public interface Flyable
{
    ZonedDateTime getDepartureTime();
    ZonedDateTime getArrivalTime();

    ZonedDateTime getTrueDepartureTime();
    ZonedDateTime getTrueArrivalTime();

    Airport getOriginAirport();
    Airport getDestinationAirport();

    float getAirfare();
}
