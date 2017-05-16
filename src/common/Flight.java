package common;

import java.io.Serializable;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44 : 01:44.
 */
public class Flight extends Data implements Serializable {

	private int id;
	private Aircraft aircraft;
	private City departure, arrival;
	private int economyPrice;
	private int businessPrice;
	private int economyPlacesFree, businessPlacesFree;
	private String date;
	
	public Flight() {}
	
	public Flight(Aircraft aircraft, City departure, City arrival, int economyPrice, int businessPrice, String date) {
		this.aircraft = aircraft;
		this.departure = departure;
		this.arrival = arrival;
		this.economyPrice = economyPrice;
		this.businessPrice = businessPrice;
		this.date = date;
		this.economyPlacesFree = aircraft.getEconomySeats();
		this.businessPlacesFree = aircraft.getBusinessSeats();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Aircraft getAircraft() {
		return aircraft;
	}
	
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	public City getDeparture() {
		return departure;
	}
	
	public void setDeparture(City departure) {
		this.departure = departure;
	}
	
	public City getArrival() {
		return arrival;
	}
	
	public void setArrival(City arrival) {
		this.arrival = arrival;
	}
	
	public int getEconomyPrice() {
		return economyPrice;
	}
	
	public void setEconomyPrice(int economyPrice) {
		this.economyPrice = economyPrice;
	}
	
	public int getBusinessPrice() {
		return businessPrice;
	}
	
	public void setBusinessPrice(int businessPrice) {
		this.businessPrice = businessPrice;
	}
	
	public int getEconomyPlacesFree() {
		return economyPlacesFree;
	}
	
	public void setEconomyPlacesFree(int economyPlacesFree) {
		this.economyPlacesFree = economyPlacesFree;
	}
	
	public int getBusinessPlacesFree() {
		return businessPlacesFree;
	}
	
	public void setBusinessPlacesFree(int businessPlacesFree) {
		this.businessPlacesFree = businessPlacesFree;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return departure.getCode() + " - " + arrival.getCode() + " : " + date;
	}
	
}
