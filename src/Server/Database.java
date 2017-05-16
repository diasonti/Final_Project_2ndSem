package Server;

import common.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Danilov on 09/05/2017 : 14:43.
 */
class Database implements Serializable {
	
	private int flightIdGen = 1;
	private int aircraftIdGen = 1;
	private int ticketIdGen = 1;
	private List<Aircraft> aircrafts;
	private List<City> cities;
	private List<Flight> flights;
	private List<Ticket> tickets;
	
	Database() {
		aircrafts = new ArrayList<>();
		cities = new ArrayList<>();
		flights = new ArrayList<>();
		tickets = new ArrayList<>();
	}
	
	public List<Aircraft> getAircrafts() {
		return aircrafts;
	}
	
	public List<City> getCities() {
		return cities;
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	
	public Flight getFlightById(int id){
		for(Flight f : flights){
			if(f.getId() == id){
				return f;
			}
		}
		return null;
	}
	
	public Aircraft addAircraft(String name, String model, int businessSeats, int economySeats) {
		aircrafts.add(new Aircraft(name, model, businessSeats, economySeats));
		aircrafts.get(aircrafts.size() - 1).setId(aircraftIdGen++);
		return aircrafts.get(aircrafts.size() - 1);
	}
	
	public City addCity(String name, String country, String code) {
		cities.add(new City(name, country, code));
		return cities.get(cities.size() - 1);
	}
	
	public Flight addFlight(City from, City to, int distance, Aircraft aircraft, int economyPrice, int businessPrice, String date) {
		flights.add(new Flight(aircraft, from, to, distance, economyPrice, businessPrice, date));
		flights.get(flights.size() - 1).setId(flightIdGen++);
		return flights.get(flights.size() - 1);
	}
	
	public Ticket addTicket(String name, String surname, String passNo, boolean business, Flight flight) {
		tickets.add(new Ticket(name, surname, passNo, business, flight));
		if(business)
			flight.setBusinessPlacesFree(flight.getBusinessPlacesFree() - 1);
		else
			flight.setEconomyPlacesFree(flight.getEconomyPlacesFree() - 1);
		tickets.get(tickets.size() - 1).setId(ticketIdGen++);
		return tickets.get(tickets.size() - 1);
	}
}
