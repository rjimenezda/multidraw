package org.i52jianr.multidraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class DrawingArea {
	private Pixmap pixmap;
	private Pixmap minipixmap;
	private int size;
	private Color backgroundColor = Color.WHITE;
	private Color frontColor = Color.RED;
	private int lastx = -1;
	private int lasty = -1;
	private Brush currentBrush;
	
	public DrawingArea(int size) {
		this();
		this.size = size;
	}
	
	public DrawingArea() {
		Pixmap.setFilter(Filter.NearestNeighbour);
		minipixmap = new Pixmap(256, 256, Format.RGBA8888);
		pixmap = new Pixmap(256, 256, Format.RGBA8888);
		size = 256;
		currentBrush = Brushes.round;
		
		pixmap.setColor(backgroundColor);
		pixmap.fill();
	}
	
	public void drawAt(int x, int y) {
		int brushSize = currentBrush.getSize();
		float[][] brush = currentBrush.getBrush();
		
		minipixmap.setColor(frontColor);
		if (lastx != -1 && lasty != -1) {
			for (int i = -brushSize; i < brushSize+1; i++) {
				for (int j = -brushSize; j < brushSize+1; j++) {
					if (brush[brushSize+i][brushSize+j] != 0) {
						minipixmap.setColor(frontColor.r, frontColor.g, frontColor.b, brush[brushSize+i][brushSize+j]);
						minipixmap.drawLine(lastx+i, lasty+j, x+i, y+j);	
					}				
				}
			}
		} else {
			
			for (int i = -brushSize; i < brushSize+1; i++) {
				for (int j = -brushSize; j < brushSize+1; j++) {
					if (brush[brushSize+i][brushSize+j] != 0) {
						minipixmap.setColor(frontColor.r, frontColor.g, frontColor.b, brush[brushSize+i][brushSize+j]);
						minipixmap.drawPixel(x+i, y+j);	
					}				
				}
			}
		}
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
		lastx = x;
		lasty = y;
	}
	
	public Pixmap getPixmap() {
		return pixmap;
	}

	public void dispose() {
		pixmap.dispose();
		minipixmap.dispose();
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
	
	public void setDrawMode() {
		setColor(frontColor);
	}

	public void removeLast() {
		lastx = lasty = -1;
	}
	
	public void setBrush(Brush brush) {
		this.currentBrush = brush;
	}
	
	public void clearArea() {
		minipixmap.setColor(backgroundColor);
		minipixmap.fill();
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
		minipixmap.setColor(frontColor);
	}

}
