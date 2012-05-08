package org.i52jianr.multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;

public class MultidrawGameScreen implements Screen {

    private OrthographicCamera cam;
	private Multidraw game;
	private DrawingArea drawingArea;
	private SpriteBatch batch;
	
	private final int OFFSET_X = 31;
	private final int OFFSET_Y = 16;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	
	private int red_x = 25;
	private int red_y = 60;
	
	private int green_x = 25;
	private int green_y = 35;
	
	private int blue_x = 25;
	private int blue_y = 10;
	
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	
	/* UI Stuff */
	private Stage stage;
	private Slider red_slider;
	private Slider blue_slider;
	private Slider green_slider;
	private Pixmap colorPreview;
	private Texture colorPreviewTexture;
	
	public MultidrawGameScreen(Multidraw multidrawGame) {
		game = multidrawGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		// We could use event handlers in case of performance drop 
		setSelectedColor();
		handleInput();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(drawingArea.getBindedTexture(), OFFSET_X, OFFSET_Y);
		batch.draw(colorPreviewTexture, 200, 400);
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
				drawingArea.normDraw(touchx - OFFSET_X, touchy - OFFSET_Y);
			}
		} else {
			 drawingArea.removeLast();
		}
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
		stage = new Stage(320, 480, true, batch);
		
		Gdx.input.setInputProcessor(stage);
		AssetManager manager = new AssetManager();
		// manager.load("uiskin.png", Texture.class);
		manager.load("uiskin.json", Skin.class);
		
		// While things yet to be loaded...
		while(!manager.update());
		
		Skin skin = manager.get("uiskin.json", Skin.class);
		setupUI(skin);
		
		// This snippet allows virtual keyboard processing
//		Gdx.input.setInputProcessor(new InputAdapter() {
//			@Override
//			public boolean keyTyped(char character) {
//				
//				if (character == 'g') {
//					drawingArea.setColor(Color.GREEN);
//				} else if (character == 'b') {
//					drawingArea.setColor(Color.BLUE);
//				} else if (character == 'q') {
//					drawingArea.setEraseMode();
//				} else if (character == 'r') {
//					drawingArea.setColor(Color.RED);
//				} else if (character == '1') {
//					drawingArea.setBrush(Brushes.pixel);
//				} else if (character == '2') {
//					drawingArea.setBrush(Brushes.cross);
//				} else if (character == '3') {
//					drawingArea.setBrush(Brushes.round);
//				} else if (character == '4') {
//					drawingArea.setBrush(Brushes.slope3);
//				} else if (character == '5') {
//					drawingArea.setBrush(Brushes.slope5);
//				} else if (character == '6') {
//					drawingArea.setBrush(Brushes.square3);
//				} else if (character == '7') {
//					drawingArea.setBrush(Brushes.square5);
//				} else if (character == '8') {
//					drawingArea.setBrush(Brushes.roundSmooth);
//				} else if (character == '9') {
//					drawingArea.setBrush(Brushes.crossSmooth);
//				} else if (character == '!') {
//					drawingArea.clearArea();
//				}
//				
//				return true;
//			}
//		});
		
		Gdx.input.setInputProcessor(stage);
		
		Gdx.input.setOnscreenKeyboardVisible(true);
		
	}

	private void setupUI(Skin skin) {
		Label red_label = new Label(skin);
		red_label.setText("R");
		Label green_label = new Label(skin);
		green_label.setText("G");
		Label blue_label = new Label(skin);
		blue_label.setText("B");
		
		red_slider = new Slider(0, 255, 1, skin.getStyle(SliderStyle.class), "slider");
		green_slider = new Slider(0, 255, 1, skin.getStyle(SliderStyle.class), "slider");
		blue_slider = new Slider(0, 255, 1, skin.getStyle(SliderStyle.class), "slider");
		stage.addActor(red_slider);
		stage.addActor(green_slider);
		stage.addActor(blue_slider);
		
		stage.addActor(red_label);
		stage.addActor(green_label);
		stage.addActor(blue_label);
		
		red_slider.y = red_y;
		red_slider.x = red_x;
		red_label.x = red_x - 15;
		red_label.y = red_y + 6;
		
		green_slider.y = green_y;
		green_slider.x = green_x;
		green_label.x = green_x - 15;
		green_label.y = green_y + 6;
		
		blue_slider.y = blue_y;
		blue_slider.x = blue_x;
		blue_label.x = blue_x - 15;
		blue_label.y = blue_y + 6;
		
		
		red_slider.setValue(192);
		green_slider.setValue(168);
		blue_slider.setValue(0);
		
		// Maybe it's not necessary to use RGBA?
		colorPreview = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		colorPreviewTexture = new Texture(colorPreview);
		colorPreviewTexture.bind();
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
		// drawingArea.dispose();
		batch.dispose();
	}
	
	// Overloading is cool
	private void setSelectedColor() {
		setSelectedColor(new Color(red_slider.getValue() / 255.0f, green_slider.getValue()  / 255.0f, blue_slider.getValue() / 255.0f, 1.0f));
	}
	
	private void setSelectedColor(Color color) {
		colorPreview.setColor(color);
		colorPreview.fill();
		colorPreviewTexture.draw(colorPreview, 0, 0);
		colorPreviewTexture.bind();
		drawingArea.setColor(color);
	}

}
