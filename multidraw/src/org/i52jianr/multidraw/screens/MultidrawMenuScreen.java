package org.i52jianr.multidraw.screens;

import org.i52jianr.multidraw.Multidraw;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class MultidrawMenuScreen implements Screen {
	
	private Multidraw game;
	private Stage stage;
	private OrthographicCamera cam;
	private final int ORIGINAL_WIDTH = 320;
	private final int ORIGINAL_HEIGHT = 480;
	private AssetManager manager;
	private String alert;
	private Button dialog;
	private Label alertText;

	public MultidrawMenuScreen(Multidraw multidrawGame, String alert) {
		this.game = multidrawGame;
		this.alert = alert;
	}

	public void setAssetManager(AssetManager manager) {
		this.manager = manager;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (alert != null) {
			dialog.visible = true;
		}
		
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
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);																																					
		SpriteBatch batch = new SpriteBatch();
		stage = new Stage(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, true, batch);
				
		Skin skin = manager.get("data/uiskin.json", Skin.class);
		
		Gdx.input.setInputProcessor(stage);
		
		alertText = new Label(alert, skin);
		dialog = new Button(alertText, skin.getStyle(ButtonStyle.class));
		dialog.row();
		
		Button dialogbutton = new Button(new Label("OK", skin), skin.getStyle(ButtonStyle.class));
		dialog.add(dialogbutton);
		dialogbutton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				alert = null;
				dialog.visible = false;
			}
		});
		
		dialog.height += 40;
		dialog.x = ORIGINAL_WIDTH / 2 - dialog.width / 2;
		dialog.y = ORIGINAL_HEIGHT - 200;
		dialog.visible = false;
		
		Button soloButton = new Button(new Label("SOLO", skin), skin.getStyle(ButtonStyle.class));
		Button createButton = new Button(new Label("CREATE GAME", skin), skin.getStyle(ButtonStyle.class));
		Button joinButton = new Button(new Label("JOIN GAME", skin), skin.getStyle(ButtonStyle.class));
		Button creditsButton = new Button(new Label("CREDITS", skin), skin.getStyle(ButtonStyle.class));
		Image image = new Image(manager.get("shittylogo.png", Texture.class));
		
		image.x = ORIGINAL_WIDTH / 2 - 128;
		image.y = ORIGINAL_HEIGHT / 2;
		
		image.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// Testing
				game.setGuessScreen("Dignity");
			}
		});
		
		soloButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.setGameScreen();
			}
		});
		
		createButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.setLobbyScreen(true);
			}
		});
		
		joinButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				
				game.setGameListScreen();
			}
		});

		creditsButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				Gdx.app.log("INFO", "Booting up credits screen...");
			}
		});
		
		stage.addActor(image);
		Table table = new Table();
		table.row();
		table.add(soloButton).pad(5);
		table.row();
		table.add(createButton).pad(5);
		table.row();
		table.add(joinButton).pad(5);
		table.row();
		table.add(creditsButton).pad(5);
		
		table.x = 320 / 2 - table.width / 2;
		table.y = 100;
		stage.addActor(table);
		
		stage.addActor(dialog);
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
	}

}

