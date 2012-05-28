package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.NativeJavaImpl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/**
 * TEST CODE, IGNORE PLEASE
 * @author Román Jiménez
 *
 */
public class AnotherMain {
	
	/**
	 * Just the main method
	 * @param args Arguments to pass
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
