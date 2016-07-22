package demo;

import java.io.Serializable;


public class LoginCredentials implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8604070582661979517L;
	private String userName;
	private String userPassword;
	private Location userLocation;

	public LoginCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Location getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(Location userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "LoginCredentials [userName=" + userName + ", userPassword=" + userPassword + ", userLocation=" + userLocation + "]";
	}





}
