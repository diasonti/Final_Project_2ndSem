package Server;

import common.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:54.
 */
public class ClientThread extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String access;
	
	ClientThread(ServerSocket server) {
		try {
			
			this.socket = server.accept();
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			Packet login = get();
			if(login.getHeader().equals("login")) {
				access = (String) login.getContent()[0];
				respond(0);
			}
			else {
				in.close();
				out.close();
				socket.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			socket = null;
		}
	}
	
	private Packet get() throws ClassNotFoundException, java.io.IOException{
		return (Packet)in.readObject();
	}
	private void respond(int reply) throws java.io.IOException{
		out.writeObject(new Packet("Response", reply));
	}
	
	@Override
	public void run() {
		if(socket == null)
			return;
		
	}
}
