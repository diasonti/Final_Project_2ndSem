/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44 : 01:44.
 */
public class Flight {

	private Aircraft aircraft;
	private City departure, arrival;
	private int distance, economPrice, businessPrice;
	
	public Flight() {}
	
	public Flight(Aircraft aircraft, City departure, City arrival, int distance, int economPrice, int businessPrice) {
		this.aircraft = aircraft;
		this.departure = departure;
		this.arrival = arrival;
		this.distance = distance;
		this.economPrice = economPrice;
		this.businessPrice = businessPrice;
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
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getEconomPrice() {
		return economPrice;
	}
	
	public void setEconomPrice(int economPrice) {
		this.economPrice = economPrice;
	}
	
	public int getBusinessPrice() {
		return businessPrice;
	}
	
	public void setBusinessPrice(int businessPrice) {
		this.businessPrice = businessPrice;
	}
}
