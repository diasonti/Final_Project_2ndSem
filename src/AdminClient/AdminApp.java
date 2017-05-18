package AdminClient;

import common.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Vladimir Danilov on 10/05/2017 : 01:52.
 */
class AdminApp {
	
	private static Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	static boolean connected = false;
	
	static Frame frame;
	
	public static void main(String[] args){
		final String IP = "127.0.0.1";
		final int PORT = 2009;
		try {
			 //TODO Remove comment upon gui completion
			socket = new Socket(IP, PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			int login = (int) send(new Packet("login", "admin")).getContent()[0];
			connected = login == 0;
			
			frame = new Frame();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Send packet and wait for response
	static Packet send(Packet packet) {
		if(packet == null)
			return null;
		try {
			out.writeObject(packet);
			packet = (Packet) in.readObject();
			return packet;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static Flight[] getAllFlights() {
		try {
			Packet query = new Packet("FlightsListRequest");// All flights getting packet
			Packet response = send(query);
			Flight[] flights = new Flight[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				flights[i] = (Flight) response.getContent()[i];
			}
			return flights;
		} catch(Exception e) {
			e.printStackTrace();
			return new Flight[0];
		}
	}
	
	static City[] getAllCities() {
		try {
			Packet query = new Packet("CitiesListRequest");// All cities getting packet
			Packet response = send(query);
			City[] cities = new City[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				cities[i] = (City) response.getContent()[i];
			}
			return cities;
		} catch(Exception e) {
			e.printStackTrace();
			return new City[]{null};
		}
	}
	
	static Aircraft[] getAllAircraft(){
		try {
			Packet query = new Packet("AircraftListRequest");// All aircraft getting packet
			Packet response = send(query);
			Aircraft[] aircraft = new Aircraft[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				aircraft[i] = (Aircraft) response.getContent()[i];
			}
			return aircraft;
		} catch(Exception e) {
			e.printStackTrace();
			return new Aircraft[]{null};
		}
	}
	
	static Ticket[] getAllTickets(){
		try {
			Packet query = new Packet("TicketsListRequest");// All tickets getting packet
			Packet response = send(query);
			Ticket[] tickets = new Ticket[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				tickets[i] = (Ticket) response.getContent()[i];
			}
			return tickets;
		} catch(Exception e) {
			e.printStackTrace();
			return new Ticket[]{null};
		}
	}
	
	static void newAircraft(String name, String model, int bs, int es){
		try {
			Object[] content = new Object[5];
			content[0] = "newAircraft";
			content[1] = name;
			content[2] = model;
			content[3] = bs;
			content[4] = es;
			Packet query = new Packet("edit", content);
			send(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void newCity(String name, String country, String code){
		try {
			Object[] content = new Object[3];
			content[0] = "newCity";
			content[1] = name;
			content[2] = country;
			content[3] = code;
			Packet query = new Packet("edit", content);
			send(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void newFlight(Aircraft aircraft, City departure, City arrival, int economyPrice, int businessPrice, String date){
		Flight newOne = new Flight(aircraft, departure, arrival, economyPrice, businessPrice, date);
		try{
			send(new Packet("edit", new Object[]{"newFlight", newOne}));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void editData(String dataType, Data[] edited){
		try {
			Object[] content = new Object[edited.length + 1];
			content[0] = dataType;
			for(int i = 1; i < content.length; i++){
				content[i] = edited[i - 1];
			}
			Packet query = new Packet("edit", content);
			send(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
