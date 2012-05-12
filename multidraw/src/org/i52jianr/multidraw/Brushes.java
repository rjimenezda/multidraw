package org.i52jianr.multidraw;

public final class Brushes {
	
	/*
	 * Define brushes here.
	 * Brushes should have a size of n and a matrix of (2*n)+1 by (2*n)+1
	 * You can make the brush "soft" by using float values like 0.5f (alpha blends)
	 */
	
	// Simple pixel
	public static Brush pixel = new Brush(0, new float[][]{{1}});
	
	// Small cross that has a less "squarish" feel
	public static Brush cross = new Brush(1, new float[][]{ 	{0, 1, 0}, 
																{1, 1, 1}, 
																{0, 1, 0} 
																});
	
	// Small cross that has a less "squarish" feel
	public static Brush crossSmooth = new Brush(1, new float[][]{ 	{0,		0.5f, 	0}, 
																	{0.5f, 	1, 		0.5f}, 
																	{0, 	0.5f, 	0} 
																	});
	
	// Ugly 3 x 3 square
	public static Brush square3 = new Brush(1, new float[][]{ 	{1, 1, 1}, 
																{1, 1, 1}, 
																{1, 1, 1} 
																});
	
	// Something that gives a "slope" feel, but kind of fails to make continous drawings 
	public static Brush slope3 = new Brush(1, new float[][]{ 	{0, 0, 1}, 
																{0, 1, 0},
																{1, 0, 0} 
																});
	
	// This slope works far better than the above
	public static Brush slope5 = new Brush(2, new float[][]{  	{1, 1, 0, 0, 0}, 
																{0, 1, 1, 0, 0}, 
																{0, 0, 1, 1, 0}, 
																{0, 0, 0, 1, 1}, 
																{0, 0, 0, 0, 1} 
																});
	
	// Soft brush test, delicious
	public static Brush smooth5 = new Brush(2, new float[][]{  	{0.0f,	0.5f, 	0.5f, 0.5f, 0.0f}, 
																	{0.5f, 	0.8f, 	1.0f, 0.8f, 0.5f}, 
																	{0.5f, 	1.0f, 	1.0f, 1.0f, 0.5f}, 
																	{0.5f, 	0.8f, 	1.0f, 0.8f, 0.5f}, 
																	{0.0f, 	0.5f, 	0.5f, 0.5f, 0.0f} 
																	});
	
	// Nice simple and almost round brush
	public static Brush round = new Brush(2, new float[][]{  	{0, 1, 1, 1, 0}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{0, 1, 1, 1, 0} 
																});
	
	public static Brush round7 = new Brush(3, new float[][]{  	{0, 0, 1, 1, 1, 0, 0},
																{0, 1, 1, 1, 1, 1, 0}, 
																{1, 1, 1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1, 1, 1},
																{0, 1, 1, 1, 1, 1, 0}, 
																{0, 0, 1, 1, 1, 0, 0}, 
																});

	public static Brush smooth7 = new Brush(3, new float[][]{  	{0, 0, 0.4f, 0.4f, 0.4f, 0, 0},
																{0, 0.6f, 1, 1, 1, 0.6f, 0},
																{0.4f, 1, 1, 1, 1, 1, 0.4f}, 
																{0.4f, 1, 1, 1, 1, 1, 0.4f}, 
																{0.4f, 1, 1, 1, 1, 1, 0.4f},
																{0, 0.6f, 1, 1, 1, 0.6f, 0},
																{0, 0, 0.4f, 0.4f, 0.4f, 0, 0},
																});
	
	// Fugly big square
	public static Brush square5 = new Brush(2, new float[][]{  	{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1} 
																});
	
	public static Brush square11 = new Brush(5, new float[][]{  	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},  
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},  
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},  
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                                                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                                                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},                                                                     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	});
	
	public static Brush round11 = new Brush(5, new float[][]{  		{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, 
																	{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},  
																	{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},  
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},  
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
														            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
														            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, 
														            {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, 
																	});
	
	public static Brush smooth11 = new Brush(5, new float[][]{  	{0, 	0, 		0, 		0.4f, 	0.4f, 	0.6f, 	0.4f, 	0.4f, 	0, 		0, 		0}, 
																	{0, 	0, 		0.6f, 	0.6f, 	0.8f, 	0.8f,	0.8f, 	0.6f, 	0.6f, 	0, 		0},  
																	{0,	 	0.6f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		0.8f, 	0.6f, 	0},  
																	{0.4f, 	0.6f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		0.8f, 	0.6f, 	0.4f},  
																	{0.4f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		1, 		1, 		0.8f, 	0.4f},
																	{0.6f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		1, 		1, 		0.8f, 	0.6f}, 
																	{0.4f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		1, 		1, 		0.8f, 	0.4f}, 
																	{0.4f, 	0.6f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		0.8f, 	0.6f, 	0.4f}, 
														            {0, 	0.6f, 	0.8f, 	1, 		1, 		1, 		1, 		1, 		0.8f, 	0.6f, 	0}, 
														            {0, 	0, 		0.6f, 	0.6f, 	0.8f, 	0.8f, 	0.8f, 	0.6f, 	0.6f, 	0, 		0}, 
														            {0, 	0, 		0, 		0.4f, 	0.4f, 	0.6f, 	0.4f, 	0.4f, 	0, 		0, 		0}, 
																	});
	
	public static Brush square15 = new Brush(7, new float[][]{  	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	});
	
}