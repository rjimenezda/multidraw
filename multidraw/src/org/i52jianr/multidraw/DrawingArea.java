package org.i52jianr.multidraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class DrawingArea {
	private Pixmap pixmap;
	private Pixmap minipixmap;
	private Texture texture;
	private int size;
	private Color backgroundColor = Color.BLACK;
	private Color frontColor = Color.RED;
	
	public DrawingArea(int size) {
		this();
		this.size = size;
	}
	
	public DrawingArea() {
		minipixmap = new Pixmap(256, 256, Format.RGB888);
		pixmap = new Pixmap(256, 256, Format.RGB888);
		Pixmap.setFilter(Filter.NearestNeighbour);
		texture = new Texture(pixmap);
		size = 256;
		
		pixmap.setColor(backgroundColor);
		pixmap.fill();
		texture.draw(pixmap, 0, 0);
		texture.bind();
	}
	
	public void drawAt(int x, int y) {
		minipixmap.setColor(frontColor);
		minipixmap.drawPixel(x, y);
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
//		pixmap.setColor(color);
//		pixmap.drawPixel(x, y);
	}
	
	public void clear() {
		minipixmap.setColor(Color.BLACK);
		minipixmap.fill();
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
	}
	
	public Texture getBindedTexture() {
		texture.draw(pixmap, 0, 0);
		texture.bind();
		return texture;
	}

	public void dispose() {
		pixmap.dispose();
		texture.dispose();
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public int getHeight() {
		return texture.getHeight();
	}

	public void normDraw(int x, int y) {
		drawAt((x * size) / 256, ((256 - y) * size) / 256);
	}
	
	public void setColor(Color color) {
		frontColor = color;
	}
	
	public void setEraseMode() {
		setColor(backgroundColor);
	}
	

}
