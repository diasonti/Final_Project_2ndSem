package Server;

import common.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:54.
 */
public class ClientThread extends Thread {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String access;
	
	private Database db;
	
	ClientThread(ServerSocket server) {
		try {
			
			this.socket = server.accept();
			//System.out.println("Socket");
			in = new ObjectInputStream(socket.getInputStream());
			//System.out.println("in");
			out = new ObjectOutputStream(socket.getOutputStream());
			//System.out.println("out");
			Packet login = get();
			//System.out.println("loginPacket");
			if(login.getHeader().equals("login")) {
				access = (String) login.getContent()[0];
				respond(0);
				//System.out.println("responded");
			} else {
				in.close();
				out.close();
				socket.close();
			}
			load();
			
		} catch(Exception e) {
			e.printStackTrace();
			in = null;
			out = null;
			socket = null;
		}
	}
	
	private Packet get() throws Exception {
		return (Packet) in.readObject();
	}
	
	private void send(Packet packet) {
		try {
			out.writeObject(packet);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void respond(int reply) {
		try {
			out.writeObject(new Packet("Response", reply));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		if(socket == null)
			return;
		while(true) {
			Packet query;
			try {
				query = get();
			} catch(Exception e) {
				e.printStackTrace();
				break;
			}
			if(query == null)
				continue;
			if(query.getHeader().equals("FilteredFlightsListRequest")) {
				//TODO Filter and send flights
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
			} else if(query.getHeader().equals("FlightsListRequest")) {
				//TODO Send all flights
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
			} else if(query.getHeader().equals("BookRequest")) {
				//TODO Book flight and send reply code
				//
				respond(0);
			} else if(query.getHeader().equals("CitiesListRequest")) {
				//TODO Send all cities' list
				load();
				send(new Packet(db.getCities().toArray()));
				/*
				send(new Packet(new City[]{new City("Almaty", "Kazakhstan", "UAAA"),
						new City("Astana", "Kazakhstan", "UACC"),
						new City("London Heathrow", "United Kingdom", "EGLL")}));
				*/
			}
		}
	}
	
	private void save(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("memory.dat"));
			out.writeObject(db);
			out.close();
		}catch(Exception e){
			System.out.println("Database writing error.");
			e.printStackTrace();
		}
	}
	private void load(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("memory.dat"));
			db = (Database)in.readObject();
			in.close();
		}catch(Exception e){
			db = new Database();
			System.out.println("Database reading error. New database created");
		}
	}
	
}
