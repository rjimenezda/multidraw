package org.i52jianr.multidraw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.utils.Scaling;

public class MultidrawGameScreen implements ApplicationListener {

    private OrthographicCamera cam;
	private DrawingArea drawingArea;
	private SpriteBatch batch;
	
	private final int OFFSET_X = 31;
	private final int OFFSET_Y = 16;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	
	private int red_x = 25;
	private int red_y = 80;
	
	private int green_x = 25;
	private int green_y = 50;
	
	private int blue_x = 25;
	private int blue_y = 20;
	
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	
	/* UI Stuff */
	private Stage stage;
	private Slider red_slider;
	private Slider blue_slider;
	private Slider green_slider;
	private Pixmap colorPreview;
	private Texture colorPreviewTexture;
	private Button softRound;
	private Button hardRound;
	private Button slope5;
	private Button square5;
	private AssetManager manager;
	
	public MultidrawGameScreen() {
	}
	
	@Override
	public void create() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true, 320, 480);																																					
		drawingArea = new DrawingArea(256);
		drawingArea.setColor(Color.BLUE);
		batch = new SpriteBatch();
		stage = new Stage(320, 480, true, batch) {
			public boolean keyTyped(char character) {
					/* DEBUG */
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
			};
		
		Gdx.input.setInputProcessor(stage);
		manager = new AssetManager();
		manager.load("data/uiskin.json", Skin.class);
		manager.load("draw-brush.png", Texture.class);
		manager.load("draw-eraser-2.png", Texture.class);
		manager.load("edit-clear-2.png", Texture.class);
		
		// While things yet to be loaded...
		while(!manager.update());
		
		setupUI(manager.get("data/uiskin.json", Skin.class));
	
		Gdx.input.setInputProcessor(stage);
		
		Gdx.input.setOnscreenKeyboardVisible(true);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// We could use event handlers in case of performance drop 
		setSelectedColor();
		handleInput();	
		
		Texture texture = new Texture(drawingArea.getPixmap());
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		texture.bind();
		
		Texture colortext = new Texture(colorPreview);
		colortext.bind();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(texture, OFFSET_X, OFFSET_Y);
		batch.draw(colortext, 200, 390);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		texture.dispose();
		colortext.dispose();
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

	private void setupUI(Skin skin) {
		Label red_label = new Label(skin);
		red_label.setText("R");
		Label green_label = new Label(skin);
		green_label.setText("G");
		Label blue_label = new Label(skin);
		blue_label.setText("B");
		
		softRound = brushButtonFactory(Brushes.roundSmooth, skin, red_x, red_y + 40);
		hardRound = brushButtonFactory(Brushes.round, skin, red_x + 40, red_y + 40);
		slope5 = brushButtonFactory(Brushes.slope5, skin, red_x + 80, red_y + 40);
		square5 = brushButtonFactory(Brushes.square5, skin, red_x + 120, red_y + 40);

		stage.addActor(softRound);
		stage.addActor(hardRound);
		stage.addActor(slope5);
		stage.addActor(square5);

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
		
		// Random Initial values
		red_slider.setValue(192);
		green_slider.setValue(168);
		blue_slider.setValue(172);
		
		// Maybe it's not necessary to use RGBA?
		colorPreview = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		colorPreviewTexture = new Texture(colorPreview);
		colorPreviewTexture.bind();

		Label drawThis = new Label(manager.get("data/uiskin.json", Skin.class));
		drawThis.setText("Dibuja...La Dignidad");
		drawThis.x = (ORIGINAL_WIDTH / 2) - (drawThis.getTextBounds().width / 2);
		drawThis.y = ORIGINAL_HEIGHT - OFFSET_Y + 5;
		
		stage.addActor(drawThis);
		
		// Brush, eraser, clear buttons
		Button brush_button = new Button(new Image(manager.get("draw-brush.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		Button erase_button = new Button(new Image(manager.get("draw-eraser-2.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		Button clear_button = new Button(new Image(manager.get("edit-clear-2.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		
		brush_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				drawingArea.setDrawMode();
			}
		});
		
		erase_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				drawingArea.setEraseMode();
			}
		});
		
		clear_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				drawingArea.clearArea();
			}
		});
		
		// Setup Position
		clear_button.x = ORIGINAL_WIDTH - clear_button.width - 10;
		clear_button.y = blue_slider.y;
		
		erase_button.x = ORIGINAL_WIDTH - erase_button.width - 10;
		erase_button.y = green_slider.y;
		
		brush_button.x = ORIGINAL_WIDTH - brush_button.width - 10;
		brush_button.y = red_slider.y;
		
		stage.addActor(brush_button);
		stage.addActor(erase_button);
		stage.addActor(clear_button);

	}

	@Override
	public void pause() {
		Gdx.app.log("INFO", "Pausing...");
	}

	@Override
	public void resume() {
		Gdx.app.log("INFO", "Resuming...");
		
		stage.removeActor(softRound);
		stage.removeActor(hardRound);
		stage.removeActor(slope5);
		stage.removeActor(square5);
		
		softRound = brushButtonFactory(Brushes.roundSmooth, manager.get("data/uiskin.json", Skin.class), red_x, red_y + 40);
		hardRound = brushButtonFactory(Brushes.round, manager.get("data/uiskin.json", Skin.class), red_x + 40, red_y + 40);
		slope5 = brushButtonFactory(Brushes.slope5, manager.get("data/uiskin.json", Skin.class), red_x + 80, red_y + 40);
		square5 = brushButtonFactory(Brushes.square5, manager.get("data/uiskin.json", Skin.class), red_x + 120, red_y + 40);
		
		stage.addActor(softRound);
		stage.addActor(hardRound);
		stage.addActor(slope5);
		stage.addActor(square5);
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
		drawingArea.setColor(color);
	}
	
	private Button brushButtonFactory(final Brush brush, Skin skin, int x, int y) {
		Texture text = new Texture(brush.getPixmap());
		text.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		text.bind();
		Image image = new Image(text, Scaling.fill);
		Button button = new Button(image, skin.getStyle(ButtonStyle.class));
		button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				drawingArea.setBrush(brush);
			}
		});
		
		button.x = x;
		button.y = y;
		
		return button;
	}

}