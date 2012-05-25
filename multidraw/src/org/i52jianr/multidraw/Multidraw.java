package org.i52jianr.multidraw;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class Multidraw extends Game {
	
	private MultidrawMenuScreen menuScreen;
	private MultidrawGameScreen	gameScreen;
	public NativeFunctions nat;
	private String username;
	
	public Multidraw(NativeFunctions nat) {
		this.nat = nat;
	}
	
	@Override
	public void create() {
		menuScreen = new MultidrawMenuScreen(this);
		final Random random = new Random(System.currentTimeMillis());
		
		// We should store the username and only show this if it's null
		Gdx.input.getPlaceholderTextInput(new TextInputListener() {
	            @Override
	            public void input(String text) {
	            	nat.setUsername(text);
	            }
	            
	            @Override
	            public void canceled() {
	            	nat.setUsername("User #" + random.nextInt(1000));
	            }
	        }, "Enter your name", "Your name");
		
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
	
	@Override
	public void dispose() {
		nat.byebye();
	}

}
