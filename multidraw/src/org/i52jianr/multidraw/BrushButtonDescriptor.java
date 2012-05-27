package org.i52jianr.multidraw;

public class BrushButtonDescriptor {
	private Brush brush;
	private int x;
	private int y;
	
	public BrushButtonDescriptor(Brush brush, int x, int y) {
		super();
		this.brush = brush;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Brush getBrush() {
		return brush;
	}
}
