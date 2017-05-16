package Server;

import common.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Vladimir Danilov on 15/05/2017 : 10:27.
 */
class AdminAccess extends CashierAccess {
	
	public AdminAccess(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
		super(inputStream, outputStream);
	}
	
	@Override
	void grantAccess() throws Exception {
		Packet query = get();
		
		if(filteredFlightsList(query)) {
		} else if(flightsList(query)) {
		} else if(book(query)) {
		} else if(citiesList(query)) {
		} else if(aircraftList(query)) {
		} else if(ticketsList(query)) {
		} else if(edit(query)) {
		}
	}
	
	private boolean aircraftList(Packet query) {
		if(query.getHeader().equals("AircraftListRequest")) {
			load();
			send(new Packet(db.getAircraft().toArray()));
			return true;
		} else {
			return false;
		}
	}
	
	private boolean ticketsList(Packet query) {
		if(query.getHeader().equals("TicketsListRequest")) {
			load();
			send(new Packet(db.getTickets().toArray()));
			return true;
		} else {
			return false;
		}
	}
	
	private boolean edit(Packet query) {
		if(query.getHeader().equals("edit")) {
			load();
			if(query.getContent()[0].equals("newAircraft")) {
				db.addAircraft((String) query.getContent()[1], (String) query.getContent()[2], (int) query.getContent()[3], (int) query.getContent()[4]);
			} else if(query.getContent()[0].equals("aircraft")) {
				Aircraft edited = (Aircraft) query.getContent()[0];
				for(Aircraft a : db.getAircraft()) {
					if(a.getId() == edited.getId()) {
						a.setName(edited.getName());
						a.setModel(edited.getModel());
						a.setBusinessSeats(edited.getBusinessSeats());
						a.setEconomySeats(edited.getEconomySeats());
						break;
					}
				}
			} else if(query.getContent()[0].equals("newCity")) {
				db.addCity((String) query.getContent()[1], (String) query.getContent()[2], (String) query.getContent()[3]);
			} else if(query.getContent()[0].equals("city")) {
				City compare = (City) query.getContent()[0];
				for(City c : db.getCities()) {
					if(c.equals(compare)) {
						City updated = (City) query.getContent()[1];
						c.setName(updated.getName());
						c.setCountry(updated.getCountry());
						c.setCode(updated.getCode());
						break;
					}
				}
			} else if(query.getContent()[0].equals("newFlight")) {
				
				Flight newOne = (Flight) query.getContent()[1];
				City from = null, to = null;
				Aircraft by = null;
				
				for(City c : db.getCities()){
					if(c.equals(newOne.getDeparture())){
						from = c;
						break;
					}
				}
				for(City c : db.getCities()){
					if(c.equals(newOne.getAircraft())){
						to = c;
						break;
					}
				}
				for(Aircraft c : db.getAircraft()){
					if(c.equals(newOne.getAircraft())){
						by = c;
						break;
					}
				}
				if(from != null && to != null && by != null)
					db.addFlight(from, to, 0, by, newOne.getEconomyPrice(), newOne.getBusinessPrice(),newOne.getDate());
			}else if(query.getContent()[0].equals("flight")) {
				Flight updated = (Flight)query.getContent()[1];
				Flight toEdit = null;
				for(Flight f : db.getFlights()){
					if(f.getId() == updated.getId()){
						toEdit = f;
						break;
					}
				}
				City from = (City)db.find(updated.getDeparture());
				City to = (City)db.find(updated.getArrival());
				Aircraft by = (Aircraft)db.find(updated.getAircraft());
				toEdit.setDeparture(from);
				toEdit.setArrival(to);
				toEdit.setAircraft(by);
				toEdit.setDate(updated.getDate());
				toEdit.setEconomyPrice(updated.getEconomyPrice());
				toEdit.setBusinessPrice(updated.getBusinessPrice());
			}
			//TODO Data edit
			
			save();
			respond(0);
			return true;
		} else {
			return false;
		}
	}
	
}
