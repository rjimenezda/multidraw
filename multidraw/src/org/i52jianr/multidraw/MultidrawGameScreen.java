package org.i52jianr.multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

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
	private Stage stage;
	private Slider red_slider;
	private Skin skin;
	
	public MultidrawGameScreen(Multidraw multidrawGame) {
		game = multidrawGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		handleInput();
		//drawingArea.drawAt(5, 5);
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(drawingArea.getBindedTexture(), OFFSET_X, OFFSET_Y);
		batch.end();
		
		stage.act(delta);
		stage.draw();
		
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
			
		} else {
			 drawingArea.removeLast();
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
	}

	@Override
	public void show() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true, 320, 480);
		drawingArea = new DrawingArea(128);
		drawingArea.setColor(Color.BLUE);
		batch = new SpriteBatch();
		position = 0;
		stage = new Stage(320, 480, true, batch);
		Gdx.input.setInputProcessor(stage);
//        skin = new Skin(Gdx.files.internal("uiskin.json"), Gdx.files.internal("uiskin.png"));
//		
//		red_slider = new Slider(0, 10, 1, skin.getStyle(SliderStyle.class), "slider");
//		stage.addActor(red_slider);
//		red_slider.y = 30;
		
		// This snippet allows virtual keyboard processing
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char character) {
				
				if (character == 'g') {
					drawingArea.setColor(Color.GREEN);
				} else if (character == 'b') {
					drawingArea.setColor(Color.BLUE);
				} else if (character == 'q') {
					drawingArea.setEraseMode();
				} else if (character == 'r') {
					drawingArea.setColor(Color.RED);
				} else if (character == '1') {
					drawingArea.setBrush(Brushes.pixel);
				} else if (character == '2') {
					drawingArea.setBrush(Brushes.cross);
				} else if (character == '3') {
					drawingArea.setBrush(Brushes.round);
				} else if (character == '4') {
					drawingArea.setBrush(Brushes.slope3);
				} else if (character == '5') {
					drawingArea.setBrush(Brushes.slope5);
				} else if (character == '6') {
					drawingArea.setBrush(Brushes.square3);
				} else if (character == '7') {
					drawingArea.setBrush(Brushes.square5);
				} else if (character == '8') {
					drawingArea.setBrush(Brushes.roundSmooth);
				} else if (character == '9') {
					drawingArea.setBrush(Brushes.crossSmooth);
				} else if (character == '!') {
					drawingArea.clearArea();
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
