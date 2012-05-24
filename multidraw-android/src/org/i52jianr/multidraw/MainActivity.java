package org.i52jianr.multidraw;

import java.util.List;

import org.i52jianr.multidraw.multiplayer.GameDescriptor;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication implements NativeFunctions {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new Multidraw(this), cfg);
    }

	public List<GameDescriptor> getGames() {
		return null;
	}

	public void testNativeness() {
		Log.d("testNativeness", "Starting Multidraw");
	}
}