package org.i52jianr.multidraw.multiplayer;

public class GameDescriptor {
	
	private String gameId;
	private String name;
	private String ownerName;
	private int size;
	private int nPlayers;
	private int maxPlayers;
	private String word;
	
	
	public GameDescriptor(String gameId, String name, String ownerName) {
		this.gameId = gameId;
		this.name = name;
		this.ownerName = ownerName;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
}
