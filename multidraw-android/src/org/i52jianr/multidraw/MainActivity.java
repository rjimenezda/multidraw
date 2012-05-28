package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.NativeJavaImpl;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
/**
 * App Launcher for Android
 * @author Román Jiménez
 *
 */
public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new Multidraw(new NativeJavaImpl()), cfg);
    }

}