package common;

import java.io.Serializable;

/**
 * Created by Vladimir Danilov on 06/05/2017 : 01:40.
 */
public class Packet implements Serializable {
	
	private String header;
	private Object[] content;
	public Packet(String action) {
		this.header = action;
	}
	public Packet(String action, Object[] content) {
		this.header = action;
		this.content = content;
	}
	public Packet(String action, int content) {
		this.header = action;
		this.content = new Integer[]{content};
	}
	public Packet(String action, String content) {
		this.header = action;
		this.content = new String[]{content};
	}
	
	public String getHeader() {
		return header;
	}
	
	public Object[] getContent() {
		return content;
	}
}
