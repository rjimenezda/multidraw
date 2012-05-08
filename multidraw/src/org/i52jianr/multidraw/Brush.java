package org.i52jianr.multidraw;

public class Brush {
	int size;
	int[][] brush;
	
	public Brush(int size, int[][] brush) {
		this.size = size;
		this.brush = brush;
	}
	
	public int getSize() {
		return size;
	}
	
	public int[][] getBrush() {
		return brush;
	}
}
