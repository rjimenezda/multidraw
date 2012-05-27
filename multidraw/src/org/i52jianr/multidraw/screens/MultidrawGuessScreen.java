package org.i52jianr.multidraw.screens;

import org.i52jianr.multidraw.DrawingArea;
import org.i52jianr.multidraw.Multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;

public class MultidrawGuessScreen extends MultidrawBaseGameScreen {

    private OrthographicCamera cam;
	private DrawingArea drawingArea;
	private SpriteBatch batch;
	
	private final int OFFSET_X = 31;
	private final int OFFSET_Y = 16;
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
		
	public MultidrawGuessScreen(Multidraw game, String word) {
		super(game, word);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// We could use event handlers in case of performance drop 
		setSelectedColor();
		
		Texture texture = new Texture(drawingArea.getPixmap());
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		texture.bind();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(texture, OFFSET_X, OFFSET_Y);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		texture.dispose();
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
	public void hide() {
	}

	@Override
	public void resize(int width, int height) {
        scaleX = ((float)ORIGINAL_WIDTH) / width;
        scaleY = ((float)ORIGINAL_HEIGHT) / height;
	}
	
	@Override
	public void pause() {
		Gdx.app.log("INFO", "Pausing...");
	}

	@Override
	public void resume() {
		Gdx.app.log("INFO", "Resuming...");
	}

	@Override
	public void dispose() {
		drawingArea.dispose();
		batch.dispose();
	}

	protected void setupUI(final Skin skin) {
		
		/* TODO: Add guess button + textinput */
		
		Button menu_button = new Button(new Image(manager.get("format-list-unordered.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		
		menu_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.setMenuScreen();
			}
		});
		
		stage.addActor(menu_button);
	}

	protected void setSelectedColor() {
		// Get code from last message received
	}
	
	protected void setSelectedColor(Color color) {
		// Get color from last message received
		drawingArea.setColor(color);
	}

}