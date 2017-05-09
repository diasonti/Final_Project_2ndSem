package Server;

import common.*;

import java.net.*;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:51.
 */
class Server {

	public static void main(String[] args){
		final int PORT = 2009;
		try {
			
			ServerSocket server = new ServerSocket(PORT);
			
			while(true){
				new ClientThread(server).start();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
