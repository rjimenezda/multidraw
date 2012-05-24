package org.i52jianr.multidraw.client;

import java.util.List;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.NativeFunctions;
import org.i52jianr.multidraw.multiplayer.GameDescriptor;

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
	public List<GameDescriptor> getGames() {
		return null;
	}

	@Override
	public void testNativeness() {
		natTestNativeness();
	}
	
	private native void natTestNativeness() /*-{
		$wnd.console.log("Booting up Multidraw");
	}-*/;

}