package org.i52jianr.multidraw.multiplayer;

/**
 * Class that Stores basic user information
 * @author Román Jiménez
 *
 */
public class User {
	private String username;
	private String userId;
	
	/**
	 * Classic field constructor
	 * @param username The User's username
	 * @param userId The User's User ID
	 */
	public User(String username, String userId) {
		this.username = username;
		this.userId = userId;
	}
	
	/**
	 * Getter for the User Id field
	 * @return the User's id 
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Getter for the User's username
	 * @return the User's username
	 */
	public String getUsername() {
		return username;
	}
}
