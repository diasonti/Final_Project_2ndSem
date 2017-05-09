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
			/* //TODO Remove comment upon gui completion
			socket = new Socket(IP, PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			int login = (int) send(new Packet("login", "Admin")).getContent()[0];
			connected = login == 0;
			*/
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
			City[] cities = new City[response.getContent().length + 1];
			cities[0] = null;
			for(int i = 0; i < response.getContent().length; i++) {
				cities[i + 1] = (City) response.getContent()[i];
			}
			return cities;
		} catch(Exception e) {
			e.printStackTrace();
			return new City[]{null};
		}
	}

}
