package org.i52jianr.multidraw.screens;

import java.util.List;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.multiplayer.GameDescriptor;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.FlickScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.Scaling;

/**
 * This class represents a Screen that shows a list of games from the server
 * @author Román Jiménez
 *
 */
public class GameListScreen implements Screen {

	OrthographicCamera cam;
	Stage stage;
	Label state;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	private Multidraw game;
	private AssetManager manager;
	
	/**
	 * Contructor that takes an instance of {@link Multidraw}
	 * @param game {@link Multidraw} instance to use
	 */
	public GameListScreen(Multidraw game) {
		this.game = game;
	}
	
	/**
	 * Sets the {@link AssetManager} to get resources from 
	 * @param manager An instance of {@link AssetManager} to get resources
	 */
	public void setAssetManager(AssetManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		stage.getSpriteBatch().setProjectionMatrix(cam.combined);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		SpriteBatch batch = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, true, batch);
		
		// I ALWAYS forget this one and drives me crazy
		Gdx.input.setInputProcessor(stage);
		
		final Skin skin = manager.get("data/uiskin.json", Skin.class);
		state = new Label("Loading games...", skin);
		
		final Table table = new Table(skin);
		final FlickScrollPane pane = new FlickScrollPane();
		
		stage.addActor(state);
		state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
		state.y = ORIGINAL_HEIGHT - state.getTextBounds().height - 10;
				
		final Button menu_button = new Button(new Image(manager.get("format-list-unordered.png", Texture.class), Scaling.fill), manager.get("data/uiskin.json", Skin.class));
		menu_button.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.setMenuScreen();
			}
		});
		menu_button.x = 10;
		menu_button.y = 10;
				
		game.nat.getGames(new GetGamesHandler() {
			
			@Override
			public void onGamesReceived(List<GameDescriptor> games) {
				// For each object received (game) create a row in the table
				for (final GameDescriptor gameDesc : games) {
					Label gameName = new Label(gameDesc.getName() + " by " + gameDesc.getOwnerName(), skin);
					Label joinLabel = new Label("JOIN", skin);
					Button button = new Button(joinLabel, skin.getStyle(ButtonStyle.class));
					button.setClickListener(new ClickListener() {
						
						@Override
						public void click(Actor actor, float x, float y) {
							game.setLobbyScreen(false, gameDesc.getGameId());
						}
					});
				
					table.add(gameName);
					table.add(button);
					table.row().pad(5);
				}
				// Change the text to "Game List" and recalculate position
				state.setText("Game list:");
				state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
				
				// It's VERY important to do this here, Asynchronous code!!b
				pane.setWidget(table);
				pane.x = 36;
				pane.width = ORIGINAL_WIDTH - 26;
				pane.height = ORIGINAL_HEIGHT - 30;
				stage.addActor(pane);
			}
		});
			
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
