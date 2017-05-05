package common;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class City {
	
	private String name, country, code;
	
	public City(String name, String country, String code) {
		this.name = name;
		this.country = country;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
