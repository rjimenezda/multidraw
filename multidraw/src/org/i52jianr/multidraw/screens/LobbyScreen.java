package org.i52jianr.multidraw.screens;

import java.util.Random;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.multiplayer.User;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;

/**
 * This class represents a simple Lobby Screen where creators wait until someone joins
 * @author Román Jiménez
 *
 */
public class LobbyScreen implements Screen {

	OrthographicCamera cam;
	Stage stage;
	Label state;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	private Multidraw game;
	private AssetManager manager;
	private boolean letsgo;
	private boolean creating;
	private String game_id;
	
	private boolean goBack;
	private String why;
	protected String word;
	
	/**
	 * Simple constructor with {@link Multidraw} instance and whether we create or not
	 * @param game The {@link Multidraw} instance to use
	 * @param creating whether we are creating or joining
	 */
	public LobbyScreen(Multidraw game, boolean creating) {
		this.game = game;
		this.creating = creating;
	}
	
	/**
	 * Constructor that takes a {@link Multidraw} instance, whether it creates and a Game Id 
	 * @param game the {@link Multidraw} instance to use
	 * @param creating whether we're creating the game or not
	 * @param game_id The Game we are joining 's ID 
	 */
	public LobbyScreen(Multidraw game, boolean creating, String game_id) {
		this.game = game;
		this.creating = creating;
		this.game_id = game_id;
		
		if (!creating) {
			this.game.nat.joinGame(this.game_id, new StartGameHandler() {
				
				@Override
				public void onGameStarted(String word) {
					LobbyScreen.this.word = word;
					letsgo = true;
				}
			}, new EndGameHandler() {
				
				@Override
				public void onGameEnd(String why) {
					goBack = true;
					LobbyScreen.this.why = why;
				}
			});
		}
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (!letsgo && !goBack) {
			cam.update();
			stage.getSpriteBatch().setProjectionMatrix(cam.combined);
			stage.act(delta);
			stage.draw();
		} else if (letsgo && !goBack){
			if (creating) {
				game.setGameScreen(word);	
			} else {
				game.setGuessScreen(word);
			}
		} else if (goBack && !letsgo) {
			game.setMenuScreen(why);
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	/**
	 * Sets the {@link AssetManager} to load resources from
	 * @param manager The {@link AssetManager} to load
	 */
	public void setAssetManager(AssetManager manager) {
		this.manager = manager;
	}

	@Override
	public void show() {
		SpriteBatch batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, true, batch);
				
		final Button menu_button = new Button(new Image(manager.get("format-list-unordered.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		menu_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				if (creating) {
					game.nat.endGame("Server closed the game");
				}
				game.setMenuScreen();
				
			}
		});
		menu_button.x = 10;
		menu_button.y = 10;
		
		state = new Label("Waiting...", manager.get("data/uiskin.json", Skin.class));
		state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
		state.y = ORIGINAL_HEIGHT - 100;
		
		stage.addActor(state);
		
		if (creating) {
			
			final Random rand = new Random(System.currentTimeMillis());
			
			Gdx.input.getPlaceholderTextInput(new TextInputListener() {
				
				@Override
				public void input(String text) {
					
					if (text.equals("")) {
						text = "Game # " + rand.nextInt(1000);
					}
					
					game.nat.createGame(text, new UserJoinsHandler() {
						@Override
						public void onUserJoined(User user) {
							state.setText("Somebody Joined!!");
							state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
							state.y = ORIGINAL_HEIGHT - 100;
						}
					}, new StartGameHandler() {
						// This is half OK, the owner should start the game
						@Override
						public void onGameStarted(String word) {
							LobbyScreen.this.word = word;
							letsgo = true;
						}
					});
					
					Gdx.input.setInputProcessor(stage);
				}
				
				@Override
				public void canceled() {
					game.setMenuScreen("Enter a game name");
				}
			}, "Game name", "Your game name...");
			
			
		}
		
		stage.addActor(menu_button);
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
		stage.dispose();
	}

}
