package org.i52jianr.multidraw.client;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.multiplayer.NativeFunctions;
import org.i52jianr.multidraw.multiplayer.callbacks.DrawHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication implements NativeFunctions {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(320, 480);
		cfg.antialiasing = false;
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new Multidraw(this);
	}

	@Override
	public void testNativeness() {
		natTestNativeness();
	}
	
	private native void natTestNativeness() /*-{
		$wnd.console.log("Booting up Multidraw");
	}-*/;

	@Override
	public void getGames(GetGamesHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void byebye() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinGame(String gameId, StartGameHandler startHandler,
			EndGameHandler endHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame(String why) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameStarted(EndGameHandler endHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(int x, int y, int r, int g, int b, int brush) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(DrawHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createGame(String gameName, UserJoinsHandler handler,
			StartGameHandler startHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanCallbacks() {
		// TODO Auto-generated method stub
		
	}

}