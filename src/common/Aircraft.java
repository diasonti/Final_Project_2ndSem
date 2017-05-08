package common;

import java.io.Serializable;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class Aircraft implements Serializable {
	
	private String name, model;
	private int business, economy;
	
	
	public Aircraft() {}
	public Aircraft(String name, String model, int business, int economy) {
		this.name = name;
		this.model = model;
		this.business = business;
		this.economy = economy;
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
	
	public int getBusiness() {
		return business;
	}
	
	public void setBusiness(int business) {
		this.business = business;
	}
	
	public int getEconomy() {
		return economy;
	}
	
	public void setEconomy(int economy) {
		this.economy = economy;
	}
	
	@Override
	public String toString() {
		return name + " " + model;
	}
}
