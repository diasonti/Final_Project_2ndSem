package CashierClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import common.*;

/**
 * Created by Vladimir on 05.05.2017.
 */
class App {
    
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    static boolean connected = false;
    
    static Frame frame;
    
    public static void main(String[] args){
        final String IP = "127.0.0.1";
        final int PORT = 2009;
        try {
            socket = new Socket(IP, PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            int login = (int)send(new Packet("login","cashier")).getContent()[0];
            connected = login == 0;
			frame = new Frame();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // Send packet and wait for response
    static Packet send(Packet packet){
        if(packet == null)
            return null;
        try{
            out.writeObject(packet);
            packet = (Packet)in.readObject();
			return packet;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    static Flight[] findFlights(City from, City to, boolean business, int passengers){
    	try {
			Object[] content = {from, to, business, passengers}; // Flights finding packet
			Packet query = new Packet("FilteredFlightsListRequest", content);
			Packet response = send(query);
			Flight[] flights = new Flight[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				flights[i] = (Flight) response.getContent()[i];
			}
			return flights;
		}catch(Exception e){
    		e.printStackTrace();
    		return new Flight[0];
		}
    }

    static int buyTickets(Flight flight, boolean business, int amount){
		try {
			Object[] content = {flight.getId(), business, amount}; // Flights booking packet
			Packet query = new Packet("BookRequest", content);
			Packet response = send(query);
			return (int)response.getContent()[0];
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
    }
    
    static Flight[] getAllFlights(){
		try {
			Packet query = new Packet("FlightsListRequest");// All flights getting packet
			Packet response = send(query);
			Flight[] flights = new Flight[response.getContent().length];
			for(int i = 0; i < response.getContent().length; i++) {
				flights[i] = (Flight) response.getContent()[i];
			}
			return flights;
		}catch(Exception e){
			e.printStackTrace();
			return new Flight[0];
		}
    }
	
	static City[] getAllCities(){
		try {
			Packet query = new Packet("CitiesListRequest");// All cities getting packet
			Packet response = send(query);
			City[] cities = new City[response.getContent().length + 1];
			cities[0] = null;
			for(int i = 0; i < response.getContent().length; i++) {
				cities[i + 1] = (City) response.getContent()[i];
			}
			return cities;
		}catch(Exception e){
			e.printStackTrace();
			return new City[]{null};
		}
	}
    
}

/*
return new Flight[]{new Flight(new Aircraft("Airbus", "A231", 50, 150),
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
                        5000, 1000, 3000, "2.12.1998")};
 */
