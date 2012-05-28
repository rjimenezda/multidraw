package org.i52jianr.multidraw.screens;

import org.i52jianr.multidraw.DrawingArea;
import org.i52jianr.multidraw.Multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Base class for actual Game Screens, defines a bunch of default stuff
 * @author Román Jiménez
 *
 */
public abstract class MultidrawBaseGameScreen implements Screen {

    protected OrthographicCamera cam;
	protected DrawingArea drawingArea;
	protected SpriteBatch batch;

	protected final int ORIGINAL_WIDTH = 320;
	protected final int ORIGINAL_HEIGHT = 480;
	
	/* UI Stuff */
	protected Stage stage;
	protected AssetManager manager;

	protected int canvasSize;
	
	protected Button activeBrushButton;
	
	protected Multidraw game;
	protected String word;
	
	/**
	 * Basic Constructor, takes {@link Multidraw} instance and word to guess
	 * @param game
	 * @param word
	 */
	public MultidrawBaseGameScreen(Multidraw game, String word) {
		canvasSize = 256; // Default
		
		this.game = game;
		this.word = word;
	}
	
	/**
	 * Sets the {@link AssetManager} to load resources from
	 * @param manager The {@link AssetManager} to load from
	 */
	public void setAssetManager(AssetManager manager) {
		this.manager = manager; 
	}

	@Override
	public void show() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);																																					
		drawingArea = new DrawingArea(canvasSize);
		batch = new SpriteBatch();
		stage = new Stage(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, true, batch);
		
		Gdx.input.setInputProcessor(stage);
		
		setupUI(manager.get("data/uiskin.json", Skin.class));
	
		Gdx.input.setInputProcessor(stage);
		// Weird alpha if not called wtf
		drawingArea.clearArea();
	}
	
	@Override
	public void pause() {
		Gdx.app.log("INFO", "Pausing...");
	}

	@Override
	public void dispose() {
		stage.dispose();
		drawingArea.dispose();
	}

	/**
	 * Setup the Stage UI
	 * @param skin {@link Skin} instance (called from show method)
	 */
	abstract void setupUI(final Skin skin);

	/**
	 * Set the {@link DrawingArea} {@link Color} based on current input
	 */
	abstract void setSelectedColor();
	
	/**
	 * Set the {@link Color} for the {@link DrawingArea} 
	 * @param color {@link Color} to set the {@link DrawingArea}
	 */
	abstract void setSelectedColor(Color color);

}