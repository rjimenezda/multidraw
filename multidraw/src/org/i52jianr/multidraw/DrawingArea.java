package org.i52jianr.multidraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;

/**
 * Class that defines a Drawing Area on which you can draw things
 * @author Román Jiménez
 *
 */
public class DrawingArea {
	private Pixmap pixmap;
	private Pixmap minipixmap;
	private int size;
	private Color backgroundColor = Color.WHITE;
	private Color frontColor = Color.RED;
	private int lastx = -1;
	private int lasty = -1;
	private Brush currentBrush;
	private boolean eraseMode;
	
	/**
	 * Constructor that takes a size argument
	 * @param size amount of pixels inside the drawing area
	 */
	public DrawingArea(int size) {
		this();
		this.size = size;
	}
	
	/**
	 * Default Constructor. Sets size to 256
	 */
	public DrawingArea() {
		Pixmap.setFilter(Filter.NearestNeighbour);
		minipixmap = new Pixmap(256, 256, Format.RGBA8888);
		pixmap = new Pixmap(256, 256, Format.RGBA8888);
		size = 256;
		currentBrush = Brushes.round;
		
		pixmap.setColor(backgroundColor);
		pixmap.fill();
	}
	
	/**
	 * Draws directly at the given coordinates, public for little reason
	 * @param x x-axis position to draw
	 * @param y y-axis position to draw
	 */
	public void drawAt(int x, int y) {
		int brushSize = currentBrush.getSize();
		float[][] brush = currentBrush.getBrush();
		Color color;
		
		if (eraseMode) {
			color = backgroundColor;
		} else {
			color = frontColor;
		}

		// This might look redundant, but should be more efficient because
		// the condition is not evaluated for each pixel on the brush
		if (lastx != -1 && lasty != -1) {
			for (int i = -brushSize; i < brushSize+1; i++) {
				for (int j = -brushSize; j < brushSize+1; j++) {
					if (brush[brushSize-j][brushSize+i] != 0) {
						minipixmap.setColor(color.r, color.g, color.b, brush[brushSize-j][brushSize+i]);
						minipixmap.drawLine(lastx+i, lasty+j, x+i, y+j);	
					}			
				}
			}
		} else {
			for (int i = -brushSize; i < brushSize+1; i++) {
				for (int j = -brushSize; j < brushSize+1; j++) {
					if (brush[brushSize-j][brushSize+i] != 0) {
						minipixmap.setColor(color.r, color.g, color.b, brush[brushSize-j][brushSize+i]);
						minipixmap.drawPixel(x+i, y+j);	
					}				
				}
			}
		}
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
		lastx = x;
		lasty = y;
	}
	
	/**
	 * Getter for the Pixmap that stores pixel data
	 * @return {@link Pixmap} with drawn data
	 */
	public Pixmap getPixmap() {
		return pixmap;
	}

	/**
	 * Disposes whatever needed
	 */
	public void dispose() {
		pixmap.dispose();
		minipixmap.dispose();
	}

	/**
	 * Draws normalized, i.e. taking into account the amount of pixels
	 * @param x x-axis position to draw
	 * @param y y-axis position to draw
	 */
	public void normDraw(int x, int y) {
		drawAt((x * size) / 256, ((256 - y) * size) / 256);
	}
	
	/**
	 * Sets the current drawing {@link Color}
	 * @param color the color to draw next
	 */
	public void setColor(Color color) {
		frontColor = color;
	}
	
	/**
	 * Sets the drawing area to erase instead of draw
	 */
	public void setEraseMode() {
		eraseMode = true;
	}
	
	/**
	 * Sets the drawing area to draw instead of erase
	 */
	public void setDrawMode() {
		eraseMode = false;
	}

	/**
	 * Call this when you want to stop drawing lines (interpolation)
	 */
	public void removeLast() {
		lastx = lasty = -1;
	}
	
	/**
	 * Sets the {@link Brush} to draw with
	 * @param brush to draw with
	 */
	public void setBrush(Brush brush) {
		this.currentBrush = brush;
	}
	
	/**
	 * Clears the drawing area with the BackgroundColor
	 */
	public void clearArea() {
		minipixmap.setColor(backgroundColor);
		minipixmap.fill();
		pixmap.drawPixmap(minipixmap, 0, 0, size, size, 0, 0, 256, 256);
		minipixmap.setColor(frontColor);
	}
	
	/**
	 * Getter for the eraseMode field
	 * @return whether it's erasing or not
	 */
	public boolean isEraseMode() {
		return eraseMode;
	}

}
