package org.i52jianr.multidraw.client;

import org.i52jianr.multidraw.MultidrawGameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(320, 480);
		cfg.antialiasing = false;
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new MultidrawGameScreen();
	}
}