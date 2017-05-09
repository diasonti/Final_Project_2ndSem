package common;

import java.io.Serializable;

/**
 * Created by Vladimir Danilov on 05/05/2017 : 01:44.
 */
public class City implements Serializable {
	
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
	
	@Override
	public String toString() {
		return name + ", " + country + " (" + code + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof City)
			return code.equals(((City)obj).getCode());
		return false;
	}
}
