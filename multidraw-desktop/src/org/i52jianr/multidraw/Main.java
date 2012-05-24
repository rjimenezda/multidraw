package org.i52jianr.multidraw;

import java.util.List;

import org.i52jianr.multidraw.multiplayer.GameDescriptor;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main implements NativeFunctions {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "multidraw";
		cfg.useGL20 = false;
		cfg.width = 320;
		cfg.height = 480;
		
		Main main = new Main();
		
		new LwjglApplication(new Multidraw(main), cfg);
	}

	@Override
	public List<GameDescriptor> getGames() {
		return null;
	}

	@Override
	public void testNativeness() {
		System.out.println("Booting up Multidraw...");
	}
}
