package org.i52jianr.multidraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class MultidrawMenuScreen implements Screen {
	
	private Multidraw game;

	public MultidrawMenuScreen(Multidraw multidrawGame) {
		this.game = multidrawGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.justTouched())
			// Gdx.app.log("INFO", "Touched at: " + Gdx.input.getX() + " / " + Gdx.input.getY());
			this.game.setGameScreen(new MultidrawGameScreen());
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		
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
