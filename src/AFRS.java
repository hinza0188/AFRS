/**
 * Created by Yongki Jay An
 */

public class AFRS
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the AFRS system.");
        AFRS.printUsage();
    }

    private static void printUsage()
    {
        System.out.println("Usage:");
        System.out.println("\tcommand_name required_arguments [option_arguments]");
        System.out.println();

        System.out.println("\tget_weather airport");
        System.out.println("\tget_itinerary origin_airport destination_airport");
        System.out.println("\tmake_reservation first_name last_name itinerary_id");
        System.out.println("\tdelete_reservation first_name last_name origin_airport destination_airport");
        System.out.println("\tget_reservation first_name last_name [origin_airport] [destination_airport]");
    }
}
