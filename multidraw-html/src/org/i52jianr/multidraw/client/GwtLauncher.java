package org.i52jianr.multidraw.client;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.NativeFunctions;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;

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
	public void createGame() {
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

}