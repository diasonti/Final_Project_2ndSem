package Server;

import common.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:54.
 */
class ClientThread extends Thread {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String access;
	private Access util;
	
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
			System.out.println("Client connected: " + access);
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
		if(access.equals("cashier")) {
			util = new CashierAccess(in, out);
		}else if(access.equals("admin")) {
			util = new AdminAccess(in, out);
		}
		while(true){
			try {
				util.grantAccess();
			}catch(Exception e){
				System.out.println("Client disconnected");
				break;
			}
		}
	}
	
}
