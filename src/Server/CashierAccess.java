package Server;

import common.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Danilov on 15/05/2017 : 10:25.
 */
class CashierAccess extends Access {
	
	public CashierAccess(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
		super(inputStream, outputStream);
	}
	
	@Override
	void grantAccess() throws Exception {
		Packet query = get();
		if(filteredFlightsList(query)) {
		} else if(flightsList(query)) {
		} else if(book(query)) {
		} else if(citiesList(query)) {
		}
	}
	
	protected boolean filteredFlightsList(Packet query) {
		if(query.getHeader().equals("FilteredFlightsListRequest")) {
			//{from(City), to(City), business(boolean), passengers(int)}
			
			City from = (City) query.getContent()[0];
			City to = (City) query.getContent()[1];
			boolean business = (boolean) query.getContent()[2];
			int count = (int) query.getContent()[3];
			
			load();
			List<Flight> flights = db.getFlights();
			ArrayList<Flight> match = new ArrayList<>();
			for(Flight flight : flights) {
				if(flight.getDeparture().equals(from) && flight.getArrival().equals(to)) {
					if(business) {
						if(flight.getBusinessPlacesFree() >= count)
							match.add(flight);
					} else {
						if(flight.getEconomyPlacesFree() >= count)
							match.add(flight);
					}
				}
			}
			send(new Packet(match.toArray()));
			return true;
				/*
				send(new Packet(new Flight[]{new Flight(new Aircraft("Airbus", "A231", 50, 150),
						new City("Astana", "Kazakhstan", "UACC"),
						new City("London Heathrow", "United Kingdom", "EGLL"),
						5000, 1000, 3000, "2.12.1998"),
						new Flight(new Aircraft("Airbus", "A231", 50, 150),
								new City("Astana", "Kazakhstan", "UACC"),
								new City("London Heathrow", "United Kingdom", "EGLL"),
								5000, 1000, 3000, "2.12.1998"),
						new Flight(new Aircraft("Airbus", "A231", 50, 150),
								new City("Astana", "Kazakhstan", "UACC"),
								new City("London Heathrow", "United Kingdom", "EGLL"),
								5000, 1000, 3000, "2.12.1998")}));
				*/
		} else {
			return false;
		}
	}
	
	protected boolean flightsList(Packet query) {
		if(query.getHeader().equals("FlightsListRequest")) {
			load();
			send(new Packet(db.getFlights().toArray()));
			return true;
				/*
				send(new Packet("", new Flight[]{new Flight(new Aircraft("Airbus", "A231", 50, 150),
						new City("Astana", "Kazakhstan", "UACC"),
						new City("London Heathrow", "United Kingdom", "EGLL"),
						5000, 1000, 3000, "2.12.1998"),
						new Flight(new Aircraft("Airbus", "A231", 50, 150),
								new City("Astana", "Kazakhstan", "UACC"),
								new City("London Heathrow", "United Kingdom", "EGLL"),
								5000, 1000, 3000, "2.12.1998"),
						new Flight(new Aircraft("Airbus", "A231", 50, 150),
								new City("Astana", "Kazakhstan", "UACC"),
								new City("London Heathrow", "United Kingdom", "EGLL"),
								5000, 1000, 3000, "2.12.1998")}));
				*/
		} else {
			return false;
		}
	}
	
	protected boolean book(Packet query) {
		if(query.getHeader().equals("BookRequest")) {
			//(name[String], surname[String], pass[String], selectedFlight(int id), businessClass(boolean))
			//(flight, business, name, surname, pass, name, surname, pass, ...)
			load();
			int passCount = query.getContent().length - 2;
			passCount /= 3;
			String[] name, surname, pass;
			name = new String[passCount];
			surname = new String[passCount];
			pass = new String[passCount];
			for(int i = 2, j = 0; i < query.getContent().length; i = i + 3, j++){
				name[j] = (String)query.getContent()[i];
				surname[j] = (String)query.getContent()[i + 1];
				pass[j] = (String)query.getContent()[i + 2];
			}
			Flight flight = db.getFlightById((int) query.getContent()[0]);
			boolean business = (boolean) query.getContent()[1];
			if(flight == null) {
				respond(404);
				return true;
			}
			if(business) {
				if(flight.getBusinessPlacesFree() < name.length) {
					respond(-1);
					return true;
				}
			} else {
				if(flight.getEconomyPlacesFree() < name.length) {
					respond(-1);
					return true;
				}
			}
			for(int i = 0; i < name.length; i++) {
				db.addTicket(name[i], surname[i], pass[i], business, flight);
			}
			save();
			respond(0);
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean citiesList(Packet query) {
		if(query.getHeader().equals("CitiesListRequest")) {
			load();
			send(new Packet(db.getCities().toArray()));
			return true;
				/*
				send(new Packet(new City[]{new City("Almaty", "Kazakhstan", "UAAA"),
						new City("Astana", "Kazakhstan", "UACC"),
						new City("London Heathrow", "United Kingdom", "EGLL")}));
				*/
		} else {
			return false;
		}
	}
	
}

