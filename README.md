Project name: Airline Tickets System

Project description: My aim is to create GUI based server – client application, where user can book tickets for aircraft from one destination to another.

Applications:

1. Server application
2. Admin application
3. Cashier application

1. Server Application.
  Server application will handle client requests, and work with my database (serialized files).
  Server handler will be able to receive or accept multiple clients. (Use threads).

2. Admin Application.
  Admin application can manage with tickets, flights, aircrafts and ticket prices.
  Admin application will not have a direct access to database.
  Every operation will be done by sending requests (sending serialized object to server by network) to a server application.

3. Cashier Application.
  Cashier application can book tickets for a client.
  Cashier application can list available tickets.
  Cashier application can cancel booked tickets.
