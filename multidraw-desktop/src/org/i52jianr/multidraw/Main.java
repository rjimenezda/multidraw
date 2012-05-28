package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.NativeJavaImpl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * App launcher for desktop version, uses {@link LwjglApplication}
 * @author Román Jiménez de Andrés
 *
 */
public class Main {
	
	/**
	 * Main method
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "multidraw";
		cfg.useGL20 = false;
		cfg.width = 320;
		cfg.height = 480;
				
		new LwjglApplication(new Multidraw(new NativeJavaImpl()), cfg);
	}

}
