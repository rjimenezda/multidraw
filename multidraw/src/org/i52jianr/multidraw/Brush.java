package org.i52jianr.multidraw;

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
}
