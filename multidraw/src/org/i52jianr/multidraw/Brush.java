package org.i52jianr.multidraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class Brush {
	int size;
	float[][] brush;
	
	public Brush(int size, float[][] brush) {
		this.size = size;
		this.brush = brush;
	}
	
	public int getSize() {
		return size;
	}
	
	public float[][] getBrush() {
		return brush;
	}
	
	public Texture getTexture() {
		Pixmap pix = new Pixmap(brush.length, brush.length, Format.RGB888);
		Texture tex = new Texture(32, 32, Format.RGBA8888);
		
		for (int i = 0; i < brush.length; i++) {
			for (int j = 0; j < brush.length; j++) {
				pix.setColor(new Color(brush[i][j], brush[i][j], brush[i][j], brush[i][j]));
				pix.drawPixel(brush.length - i -1, j);
			}
		}
		
		Pixmap canvas = new Pixmap(32, 32, Format.RGB888);
		Pixmap.setFilter(Filter.NearestNeighbour);
		canvas.drawPixmap(pix, 0, 0, brush.length, brush.length, 0, 0, 32, 32);
		
		tex.draw(canvas, 0, 0);
		
		return tex;
	}
}
