package Server;

import common.Aircraft;
import common.Packet;

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
	void grantAccess(){
		try {
			Packet query = get();
			
			if(filteredFlightsList(query)){}
			else if(flightsList(query)){}
			else if(book(query)){}
			else if(citiesList(query)){}
			else if(aircraftList(query)){}
			else if(ticketsList(query)){}
			else if(edit(query)){}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private boolean aircraftList(Packet query){
		if(query.getHeader().equals("AircraftListRequest")) {
			load();
			send(new Packet(db.getAircrafts().toArray()));
			return true;
		}else{
			return false;
		}
	}
	
	private boolean ticketsList(Packet query){
		if(query.getHeader().equals("TicketsListRequest")) {
			load();
			send(new Packet(db.getTickets().toArray()));
			return true;
		}else{
			return false;
		}
	}
	
	private boolean edit(Packet query){
		if(query.getHeader().equals("edit")) {
			load();
			if(query.getContent()[0].equals("newAircraft")){
				db.addAircraft((String)query.getContent()[1], (String)query.getContent()[2], (int)query.getContent()[3], (int)query.getContent()[4]);
			}
			//TODO Data edit
			
			save();
			respond(0);
			return true;
		}else{
			return false;
		}
	}
	
}
