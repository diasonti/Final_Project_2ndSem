package Server;

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
	
	public ClientThread(ServerSocket server) {
		try {
			
			this.socket = server.accept();
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			
		}catch(Exception e){
			e.printStackTrace();
			socket = null;
		}
	}
	
	@Override
	public void run() {
		if(socket == null)
			return;
		
	}
}
