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
	
	public MultidrawBaseGameScreen(Multidraw game, String word) {
		canvasSize = 256; // Default
		
		this.game = game;
		this.word = word;
	}
	
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
		drawingArea.dispose();
		batch.dispose();
	}

	abstract void setupUI(final Skin skin);

	// Overloading is cool
	abstract void setSelectedColor();
	
	abstract void setSelectedColor(Color color);

}