package org.i52jianr.multidraw;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class stores {@link Stage} information for a brush
 * @author Román Jiménez
 *
 */
public class BrushButtonDescriptor {
	private Brush brush;
	private int x;
	private int y;
	
	/**
	 * Main constructor of the class
	 * @param brush {@link Brush} to be used
	 * @param x position on x-axis
	 * @param y position on y-axis
	 */
	public BrushButtonDescriptor(Brush brush, int x, int y) {
		super();
		this.brush = brush;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter on the x attribute
	 * @return x position of the {@link Brush}
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter on the y attribute
	 * @return y position of the {@link Brush}
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Getter on the {@link Brush}
	 * @return the {@link Brush} the object stores
	 */
	public Brush getBrush() {
		return brush;
	}
}
