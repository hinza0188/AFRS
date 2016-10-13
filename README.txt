Airline Flight Reservation System [ AFRS ]
This is for Group B: Glorious Basterds

To run the program
  [Windows Platform]
    double click start.bat file that is included in the directory

  [Linux / Mac]
    open Terminal and go to the directory where AFRS and start.sh is located
    Then, run following code:
        chmod +x start.sh
        ./start.sh

Usage
  upon start up, the program prints following commands

  command_name,required_arguments[,option_arguments]

  info,origin,destination[,connections[,sort-order]];
  reserve,id,passenger;
  retrieve,passenger[,origin[,destination]];
  delete,passenger,origin,destination;
  weather,airport;

  quit

  To terminate program:

  > quit     (without semi-colon)

  To get flight information that are available:

  [desired departure airport/desired arrival airport] : takes 3 capitalized letters for representation of airport
  [desired number of legs] : takes 0 - 3 integer, number of lay over

  > info,[desired departure airport],[desired arrival airport],<optional>[desired number of legs];
    Then, the result should return following:
  > info number_of_result[

  id price number_of_lay_over[flight_number, origin_airport, departure_time, destination_airport, arrival_time]

  ]

  To make reservation:

  [id] : From the information that has been queried
  [name ] : The name of passenger in string format

  > reserve,[id],[name];

  To delete reservation:

  [name] : The name of passenger in string format
  [origin_airport] : Three capital letter of departure airport
  [destination_airport] : Three capital letter of arrival airport

  > delete,[name],[origin_airport],[destination_airport];

  To get weather information:

  [airport] : Three capital letter of the target airport

  > weather,[airport];
    Then, the result should return following:
  > weather,[airport],[status],[temperature_in_fahrenheit],[current_delay_time]