package org.billing_system.electricityBillingApp.model;
public class User {
	private int userid;
	private String username;
	private String address;
	
	public User(String username, String address) {
		super();
		this.username = username;
		this.address = address;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
