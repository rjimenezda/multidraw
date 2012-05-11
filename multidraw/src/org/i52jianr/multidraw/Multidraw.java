package org.i52jianr.multidraw;

import com.badlogic.gdx.Game;

public class Multidraw extends Game {
	
	private MultidrawMenuScreen menuScreen;
	
	@Override
	public void create() {
		menuScreen = new MultidrawMenuScreen(this);
		// setScreen(gameScreen);
	}
	
	public void setGameScreen() {
		getScreen().dispose();
		// setScreen(gameScreen);
	}
	
	public void setMenuScreen() {
		// getScreen().dispose();
		setScreen(menuScreen);
	}

}
