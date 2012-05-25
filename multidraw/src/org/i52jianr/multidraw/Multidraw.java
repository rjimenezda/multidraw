package org.i52jianr.multidraw;

import com.badlogic.gdx.Game;

public class Multidraw extends Game {
	
	private MultidrawMenuScreen menuScreen;
	private MultidrawGameScreen	gameScreen;
	public NativeFunctions nat;
	
	public Multidraw(NativeFunctions nat) {
		this.nat = nat;
	}
	
	@Override
	public void create() {
		menuScreen = new MultidrawMenuScreen(this);
		setScreen(menuScreen);
		nat.testNativeness();
		
		// This is just thinking out loud...
		
		// Load minimal resources for a loading screen
		
		// Create that screen with just a texture or something
		
		// Set that screen
		
		// Start loading the rest of the files
		
		// Wait until everything's loaded
		
		// Pass the asset manager around
		
		// ???????
		
		// Profit!
	}
	
	public void setGameScreen(MultidrawGameScreen screen) {
		gameScreen = screen;
		setScreen(gameScreen);
		// getScreen().dispose();
	}
	
	public void setMenuScreen() {
		getScreen().dispose(); // Risky
		setScreen(menuScreen);
	}

}
