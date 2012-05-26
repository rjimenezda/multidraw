package org.i52jianr.multidraw.screens;

import org.i52jianr.multidraw.Multidraw;
import org.i52jianr.multidraw.multiplayer.User;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LobbyScreen implements Screen {

	OrthographicCamera cam;
	Stage stage;
	Label state;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	private Multidraw game;
	
	public LobbyScreen(Multidraw game) {
		this.game = game;
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
		
		AssetManager manager = new AssetManager();
		manager.load("data/uiskin.json", Skin.class);
		
		while(!manager.update());
		
		state = new Label("Waiting...", manager.get("data/uiskin.json", Skin.class));
		state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
		state.y = ORIGINAL_HEIGHT - 100;
		
		stage.addActor(state);
		
		game.nat.createGame(new UserJoinsHandler() {
			
			@Override
			public void onUserJoined(User user) {
				state.setText("Somebody Joined!!");
				state.x = ORIGINAL_WIDTH / 2 - state.getTextBounds().width / 2;
				state.y = ORIGINAL_HEIGHT - 100;
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
