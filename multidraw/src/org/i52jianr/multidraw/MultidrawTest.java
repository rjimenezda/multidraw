package org.i52jianr.multidraw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/* TEST CODE, please ignore :) */
public class MultidrawTest implements ApplicationListener {
	private Texture text;
	private Pixmap pixmap;
	private SpriteBatch batch;
	private DrawingArea drawingArea;
	
	@Override
	public void create() {
		pixmap = new Pixmap(64, 64, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		drawingArea = new DrawingArea(8);
		drawingArea.drawAt(3, 3);
		text = new Texture(pixmap);
		text.bind();
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Texture tmp = new Texture(drawingArea.getPixmap());
		tmp.bind();
		
		batch.begin();
		batch.draw(tmp, 0, 0);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
