package org.i52jianr.multidraw;

import java.util.Random;

import org.i52jianr.multidraw.screens.GameListScreen;
import org.i52jianr.multidraw.screens.LobbyScreen;
import org.i52jianr.multidraw.screens.MultidrawGameScreen;
import org.i52jianr.multidraw.screens.MultidrawMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Multidraw extends Game {
	
	private MultidrawMenuScreen menuScreen;
	private MultidrawGameScreen	gameScreen;
	public NativeFunctions nat;
	private String username;
	private AssetManager manager;
	
	public Multidraw(NativeFunctions nat) {
		this.nat = nat;
	}
	
	@Override
	public void create() {
		manager = new AssetManager();
		// Menu Screen Assets
		manager.load("data/uiskin.json", Skin.class);
		manager.load("shittylogo.png", Texture.class);
		manager.load("draw-brush.png", Texture.class);
		manager.load("draw-eraser-2.png", Texture.class);
		manager.load("edit-clear-2.png", Texture.class);
		manager.load("format-list-unordered.png", Texture.class);
		
		while(!manager.update());
		
		menuScreen = new MultidrawMenuScreen(this);
		menuScreen.setAssetManager(manager);
		
		// Ask for the username
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
	
	public void setGameScreen() {
		MultidrawGameScreen game = new MultidrawGameScreen(this);
		game.setAssetManager(manager);
		setScreen(game);
	}
	
	public void setGameScreen(MultidrawGameScreen screen) {
		getScreen().dispose();
		gameScreen = screen;
		setScreen(gameScreen);
		// getScreen().dispose();
	}
	
	public void setMenuScreen() {
		MultidrawMenuScreen screen = new MultidrawMenuScreen(this);
		screen.setAssetManager(manager);
		getScreen().dispose(); // Risky
		setScreen(screen);
	}
	
	public void setLobbyScreen(boolean creating) {
		LobbyScreen screen = new LobbyScreen(this, creating);
		screen.setAssetManager(manager);
		getScreen().dispose();
		setScreen(screen);
	}
	
	public void setLobbyScreen(boolean creating, String game_id) {
		LobbyScreen screen = new LobbyScreen(this, creating, game_id);
		screen.setAssetManager(manager);
		getScreen().dispose();
		setScreen(screen);
	}
	
	public void setGameListScreen() {
		GameListScreen screen = new GameListScreen(this);
		screen.setAssetManager(manager);
		getScreen().dispose();
		setScreen(screen);
	}
	
	@Override
	public void dispose() {
		nat.byebye();
	}

}
