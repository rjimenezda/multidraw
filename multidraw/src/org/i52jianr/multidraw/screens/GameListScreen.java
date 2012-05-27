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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class GameListScreen implements Screen {

	OrthographicCamera cam;
	Stage stage;
	Label state;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	private Multidraw game;
	private AssetManager manager;
	
	public GameListScreen(Multidraw game) {
		this.game = game;
	}
	
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
		
		table.x = ORIGINAL_WIDTH / 2;
		table.y = ORIGINAL_HEIGHT - 150;
		
		table.add(state);
		table.pad(5);
		
		stage.addActor(table);
		
		game.nat.getGames(new GetGamesHandler() {
			
			@Override
			public void onGamesReceived(List<GameDescriptor> games) {
				for (final GameDescriptor gameDesc : games) {
					// Create a button or something
					Label gameName = new Label(gameDesc.getName() + " by " + gameDesc.getOwnerName(), skin);
					Label joinLabel = new Label("JOIN", skin);
					Button button = new Button(joinLabel, skin.getStyle(ButtonStyle.class));
					button.setClickListener(new ClickListener() {
						
						@Override
						public void click(Actor actor, float x, float y) {
							game.setLobbyScreen(false, gameDesc.getGameId());
						}
					});
				
					table.add(gameName).pad(5);
					table.add(button).pad(5);
					table.row();
				}
				state.setText("Game list:");
			}
		});
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
