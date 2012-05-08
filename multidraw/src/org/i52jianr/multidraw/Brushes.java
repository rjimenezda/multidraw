package org.i52jianr.multidraw;

public final class Brushes {
	
	/*
	 * Define brushes here.
	 * Brushes should have a size of n and a matrix of (2*n)+1 by (2*n)+1
	 */
	
	// Simple pixel
	public static Brush pixel = new Brush(0, new int[][]{{1}});
	
	// Small cross that has a less "squarish" feel
	public static Brush cross = new Brush(1, new int[][]{ 	{0, 1, 0}, 
															{1, 1, 1}, 
															{0, 1, 0} 
															});
	// Ugly 3 x 3 square
	public static Brush square3 = new Brush(1, new int[][]{ {1, 1, 1}, 
															{1, 1, 1}, 
															{1, 1, 1} 
															});
	
	// Something that gives a "slope" feel, but kind of fails to make continous drawings 
	public static Brush slope3 = new Brush(1, new int[][]{ 	{0, 0, 1}, 
															{0, 1, 0},
															{1, 0, 0} 
															});
	
	// This slope works far better than the above
	public static Brush slope5 = new Brush(2, new int[][]{  {1, 1, 0, 0, 0}, 
															{0, 1, 1, 0, 0}, 
															{0, 0, 1, 1, 0}, 
															{0, 0, 0, 1, 1}, 
															{0, 0, 0, 0, 1} 
															});
	
	// Nice simple and almost round brush
	public static Brush round = new Brush(2, new int[][]{  	{0, 1, 1, 1, 0}, 
															{1, 1, 1, 1, 1}, 
															{1, 1, 1, 1, 1}, 
															{1, 1, 1, 1, 1}, 
															{0, 1, 1, 1, 0} 
															});
	
	// Fugly big square
	public static Brush square5 = new Brush(2, new int[][]{  	{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1} 
																});
}
