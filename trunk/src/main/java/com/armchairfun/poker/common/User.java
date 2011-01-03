package com.armchairfun.poker.common;


public class User implements XMLDescribable {

	int id;
	String username;
	String password;
	String email;
	int playCash;
    
    public User() {
    	
    }
	
	public User(String username, String password) {
		this.username = username;
		// give default play cash
		this.playCash = UserConstants.STARTING_PLAY_CASH;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPlayCash() {
		return playCash;
	}
	public void setPlayCash(int playCash) {
		this.playCash = playCash;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<user ");
		sb.append("id=\"");
		sb.append(id);
		sb.append("\" username=\"");
		sb.append(username);
		sb.append("\" playCash=\"");
		sb.append(playCash);
		sb.append("\" email=\"");
		sb.append(email);
		sb.append("\" />");
		return sb.toString();
	}
}
