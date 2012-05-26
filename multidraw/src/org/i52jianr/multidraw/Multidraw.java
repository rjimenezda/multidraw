package org.i52jianr.multidraw;

import java.util.Random;

import org.i52jianr.multidraw.screens.MultidrawGameScreen;
import org.i52jianr.multidraw.screens.MultidrawMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;

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
		
		final Preferences prefs = Gdx.app.getPreferences("GAME_PREFS");
		username = prefs.getString("USERNAME", null);
		
		if (username == null) {
			Gdx.input.getPlaceholderTextInput(new TextInputListener() {
		            @Override
		            public void input(String text) {
		            	nat.setUsername(text);
		            	prefs.putString("USERNAME", text);
		            	prefs.flush();
		            }
		            
		            @Override
		            public void canceled() {
		            	nat.setUsername("User #" + random.nextInt(1000));
		            }
		        }, "Enter your name", "Your name");
		} else {
			nat.setUsername(username);
		}
		
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
