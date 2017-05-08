package common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class Ticket implements Serializable {
	
	private String name, surname, passportNo;
	private Date departure;
	private Flight flight;
	
	public Ticket() {}
	
	public Ticket(String name, String surname, String passportNo, Date departure, Flight flight) {
		this.name = name;
		this.surname = surname;
		this.passportNo = passportNo;
		this.departure = departure;
		this.flight = flight;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPassportNo() {
		return passportNo;
	}
	
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	
	public Date getDeparture() {
		return departure;
	}
	
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
