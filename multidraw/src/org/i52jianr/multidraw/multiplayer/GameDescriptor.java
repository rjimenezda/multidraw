package org.i52jianr.multidraw.multiplayer;

/**
 * This class stores some game information that could be useful
 * @author Román Jiménez
 *
 */
public class GameDescriptor {
	
	private String gameId;
	private String name;
	private String ownerName;
	private int size;
	private int nPlayers;
	private int maxPlayers;
	private String word;
	
	/**
	 * Basic constructor, some fields missing
	 * @param gameId Id of the Game
	 * @param name Arbitrary game name
	 * @param ownerName Owner's username
	 */
	public GameDescriptor(String gameId, String name, String ownerName) {
		this.gameId = gameId;
		this.name = name;
		this.ownerName = ownerName;
	}
	
	/**
	 * Getter for the gameId field
	 * @return the Game's ID
	 */
	public String getGameId() {
		return gameId;
	}
	
	/**
	 * Getter for the name field
	 * @return the Game's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the owner's username
	 * @return the Owner's username
	 */
	public String getOwnerName() {
		return ownerName;
	}
	
}
