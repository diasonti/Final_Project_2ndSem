package Server;

import common.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Vladimir Danilov on 16/05/2017 : 18:37.
 */
public class debugCreateDatabase {
	public static void main(String[] args) throws Exception{
		
		Database db = new Database();
		City astana = db.addCity("Astana", "Kazakhstan", "UACC");
		City almaty = db.addCity("Almaty", "Kazakhstan", "UAAA");
		Aircraft plane = db.addAircraft("Airbus", "A321", 50, 250);
		Flight f = db.addFlight(astana, almaty, 950, plane, 20000, 150000, "16052017");
		Flight f1 = db.addFlight(astana, almaty, 950, plane, 20000, 150000, "17052017");
		Flight f2 = db.addFlight(astana, almaty, 950, plane, 20000, 150000, "18052017");
		db.addTicket("Vlad", "Danilov", "32222223", true, f);
		db.addTicket("Vladmr", "Danilov", "32222223", true, f1);
		ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("memory.dat"));
		file.writeObject(db);
		file.close();
	}
}
