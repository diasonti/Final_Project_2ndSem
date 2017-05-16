package Server;

import common.Packet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Vladimir Danilov on 15/05/2017 : 10:26.
 */
abstract class Access {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected Database db;
	
	public Access(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
		this.in = inputStream;
		this.out = outputStream;
	}
	
	abstract void grantAccess() throws Exception;
	
	protected void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("memory.dat"));
			out.writeObject(db);
			out.close();
		} catch(Exception e) {
			System.out.println("Database writing error.");
			e.printStackTrace();
		}
	}
	
	protected void load() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("memory.dat"));
			db = (Database) in.readObject();
			in.close();
		} catch(Exception e) {
			db = new Database();
			save();
			System.out.println("Database reading error. New database created");
		}
	}
	
	protected Packet get() throws Exception {
		return (Packet) in.readObject();
	}
	
	protected void send(Packet packet) {
		try {
			out.writeObject(packet);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void respond(int reply) {
		try {
			out.writeObject(new Packet("Response", reply));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
