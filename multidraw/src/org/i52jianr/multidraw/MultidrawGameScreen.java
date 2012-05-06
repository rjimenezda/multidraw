package org.i52jianr.multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MultidrawGameScreen implements Screen {

    private OrthographicCamera cam;
	private Multidraw game;
	private DrawingArea drawingArea;
	private SpriteBatch batch;
	private int position;
	
	private final int OFFSET_X = 31;
	private final int OFFSET_Y = 16;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	private float offset_x_f = 31.0f;
	private float offset_y_f = 220.0f;
	
	public MultidrawGameScreen(Multidraw multidrawGame) {
		game = multidrawGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// drawingArea.clear();

		handleInput();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(drawingArea.getBindedTexture(), OFFSET_X, OFFSET_Y);
		batch.end();
		
	}

	private void handleInput() {		
		if (Gdx.input.isTouched()) {
			int touchx = Gdx.input.getX();
			int touchy = Gdx.input.getY();
			
			touchx = Math.round(touchx * scaleX);
			touchy = Math.round(touchy * scaleY);
			
			Rectangle rect = new Rectangle(31, OFFSET_Y, 256, 256);
			
			if (rect.contains(touchx, touchy)) {
				// Gdx.app.log("INFO", String.format("INSIDE (%d, %d)", touchx, touchy));
				drawingArea.normDraw(touchx - OFFSET_X, touchy - OFFSET_Y);
			}
			
		}
		
//		if (Gdx.input.isKeyPressed(Input.Keys.B)) {
//			drawingArea.setEraseMode();
//		} 
//	
//		if (Gdx.input.isKeyPressed(Input.Keys.G)) {
//			drawingArea.setColor(Color.GREEN);
//		}
	}

	@Override
	public void resize(int width, int height) {
		
        scaleX = ((float)ORIGINAL_WIDTH) / width;
        scaleY = ((float)ORIGINAL_HEIGHT) / height;
        
        offset_x_f = OFFSET_X * scaleX;
        offset_y_f = OFFSET_Y * scaleY;
	}

	@Override
	public void show() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true, 320, 480);
		drawingArea = new DrawingArea(128);
		drawingArea.setColor(Color.BLUE);
		batch = new SpriteBatch();
		position = 0;
		
		
		// This snippet allows virtual keyboard processing
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char character) {
				
				if (character == 'g') {
					drawingArea.setColor(Color.GREEN);
				}
				
				return true;
			}
		});
		
		Gdx.input.setOnscreenKeyboardVisible(true);
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		drawingArea.dispose();
		batch.dispose();
	}

}
