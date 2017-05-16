package common;

import java.io.Serializable;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class Aircraft extends Data implements Serializable {
	
	private int id;
	private String name, model;
	private int businessSeats, economySeats;
	
	
	public Aircraft() {}
	public Aircraft(String name, String model, int businessSeats, int economySeats) {
		this.name = name;
		this.model = model;
		this.businessSeats = businessSeats;
		this.economySeats = economySeats;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getBusinessSeats() {
		return businessSeats;
	}
	
	public void setBusinessSeats(int businessSeats) {
		this.businessSeats = businessSeats;
	}
	
	public int getEconomySeats() {
		return economySeats;
	}
	
	public void setEconomySeats(int economySeats) {
		this.economySeats = economySeats;
	}
	
	@Override
	public String toString() {
		return name + " " + model;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Aircraft)) return false;
		Aircraft o = (Aircraft) obj;
		if(o.getId() != id) return false;
		if(!o.getName().equals(name)) return false;
		if(!o.getModel().equals(model)) return false;
		if(o.getEconomySeats() != economySeats) return false;
		if(o.getBusinessSeats() != businessSeats) return false;
		return true;
	}
}
