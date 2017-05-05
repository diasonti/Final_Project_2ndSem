package common;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class Aircraft {
	
	private String name, model;
	private int business, econom;
	
	
	public Aircraft() {}
	public Aircraft(String name, String model, int business, int econom) {
		this.name = name;
		this.model = model;
		this.business = business;
		this.econom = econom;
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
	
	public int getEconom() {
		return econom;
	}
	
	public void setEconom(int econom) {
		this.econom = econom;
	}
}
