package org.i52jianr.multidraw.screens;

import java.util.ArrayList;

import org.i52jianr.multidraw.BrushButtonDescriptor;
import org.i52jianr.multidraw.Brushes;
import org.i52jianr.multidraw.DrawingArea;
import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.multiplayer.callbacks.DrawHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	private Label status;
	private long gameStart;
	
	private ArrayList<BrushButtonDescriptor> brushButtonsDesc;
		
	public MultidrawGuessScreen(Multidraw game, String word) {
		super(game, word);
		
		brushButtonsDesc = new ArrayList<BrushButtonDescriptor>();
		
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.pixel, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.round, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.round7, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.round11, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.crossSmooth, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.smooth5, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.smooth7, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.smooth11, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.square3, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.square5, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.square11, 0, 0));
		brushButtonsDesc.add(new BrushButtonDescriptor(Brushes.square15, 0, 0));
		
		this.game.nat.onDraw(new DrawHandler() {
			
			@Override
			public void onDraw(int x, int y, int r, int g, int b, int brush) {
				if (x == -1 && y == -1) {
					drawingArea.removeLast();
				} else {
					if (brush == -1) {
						drawingArea.clearArea();
					} else {
						drawingArea.setBrush(brushButtonsDesc.get(brush).getBrush());
						setSelectedColor(new Color(r/255.0f, g/255.0f, b/255.0f, 1.0f));
//					
						drawingArea.drawAt(x, y);
					}
				}
				
			}
		});
		
		this.game.nat.gameStarted(new EndGameHandler() {
			
			@Override
			public void onGameEnd(String why) {
				MultidrawGuessScreen.this.game.setMenuScreen(why);
			}
		});
		
		gameStart = System.currentTimeMillis();
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
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		
		status = new Label("WRONG", skin);
		stage.addActor(status);
		
		status.x = ORIGINAL_WIDTH / 2 - status.getTextBounds().width / 2;
		status.y = OFFSET_Y + 70;
		status.visible = false;
		
		final Button wonTheGame = new Button(new Label("Go Back", skin), skin.getStyle(ButtonStyle.class));
		
		wonTheGame.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				long time = System.currentTimeMillis() - gameStart;
				time /= 1000;
				game.nat.endGame("Player guessed the word!!\n Just " + time + " seconds, Wow!");
				game.setMenuScreen();
			}
		});
		
		wonTheGame.x = ORIGINAL_WIDTH / 2 - wonTheGame.width / 2;
		wonTheGame.y = OFFSET_Y + 20;
		wonTheGame.visible = false;
		
		stage.addActor(wonTheGame);
		
		Button guessbutton = new Button(new Label("GUESS", skin), skin.getStyle(ButtonStyle.class));
		
		guessbutton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				Gdx.input.getPlaceholderTextInput(new TextInputListener() {
					
					@Override
					public void input(String text) {
						status.visible = true;
						if (word != null) {
							// Yep, we're making a local check
							if(text.toLowerCase().equals(word.toLowerCase())) {
								setStatus("YOU WIN", Color.GREEN);
								wonTheGame.visible = true;
							} else {
								setStatus("WRONG", Color.RED);
							}
						}
					}
					
					@Override
					public void canceled() {
						// Nothing to do here...
					}
				}, "Guess the word", "Your guess...");
			}
		});
		
		stage.addActor(guessbutton);
		guessbutton.x = ORIGINAL_WIDTH / 2 - guessbutton.width / 2;
		guessbutton.y = OFFSET_Y + 100;
		
		Button menu_button = new Button(new Image(manager.get("format-list-unordered.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		menu_button.x = 10;
		menu_button.y = 10;
		
		menu_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.nat.endGame("Player quit...");
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
	
	private void setStatus(String text, Color color) {
		status.setColor(color);
		status.setText(text);
		status.x = ORIGINAL_WIDTH / 2 - status.getTextBounds().width / 2;
	}

}