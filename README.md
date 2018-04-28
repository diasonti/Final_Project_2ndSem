## Airline Tickets Booking System

> Final project for 2nd semester's Java SE class.

A GUI based client-server application for airplane tickets booking.

**Applications:**
1. Server application
2. Admin application
3. Cashier application

## Server Application.
  Server application can handle client requests and work with database *(serialized files)*.
  Server is able to work with multiple clients simultaneously. *(Multi-threading)*.

## Admin Application.
  Admin application manages tickets, flights, aircraft and ticket prices.
  Admin application does not have direct access to database.
  Every operation is done by sending requests to a server application *(sending serialized object to the server via network)*.

## Cashier Application.
  Cashier application can book tickets for a client, list available tickets.
