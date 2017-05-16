package Server;

import common.Ticket;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Vladimir Danilov on 16/05/2017 : 18:53.
 */
public class debugShowDatabase {
	
	public static void main(String[] args) throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("memory.dat"));
		Database db = (Database)in.readObject();
		in.close();
		for(Ticket t : db.getTickets()){
			System.out.println(t);
		}
	}
	
}
