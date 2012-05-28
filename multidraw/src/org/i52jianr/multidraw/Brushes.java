package org.i52jianr.multidraw;

/**
 * Static class that stores some predefined {@link Brush}es
 * @author Román Jiménez
 *
 */
public final class Brushes {
	
	/*
	 * Define brushes here.
	 * Brushes should have a size of n and a matrix of (2*n)+1 by (2*n)+1
	 * You can make the brush "soft" by using float values like 0.5f (alpha blends)
	 */
	
	/**
	 * Simple pixel
	 */
	public static Brush pixel = new Brush(0, new float[][]{{1}});
	 
	/**
	 * Small cross that has a less "squarish" feel
	 */
	public static Brush cross = new Brush(1, new float[][]{ 	{0, 1, 0}, 
																{1, 1, 1}, 
																{0, 1, 0} 
																});
	
	/**
	 * Smooth 3x3 cross
	 */
	public static Brush crossSmooth = new Brush(1, new float[][]{ 	{0,		0.5f, 	0}, 
																	{0.5f, 	1, 		0.5f}, 
																	{0, 	0.5f, 	0} 
																	});
	
	/**
	 * 3 by 3 square
	 */
	public static Brush square3 = new Brush(1, new float[][]{ 	{1, 1, 1}, 
																{1, 1, 1}, 
																{1, 1, 1} 
																});
	
	/**
	 * Slope brush, doesn't work well on continuos drawing
	 */
	public static Brush slope3 = new Brush(1, new float[][]{ 	{0, 0, 1}, 
																{0, 1, 0},
																{1, 0, 0} 
																});
	
	/**
	 * Big slope that works great
	 */
	public static Brush slope5 = new Brush(2, new float[][]{  	{1, 1, 0, 0, 0}, 
																{0, 1, 1, 0, 0}, 
																{0, 0, 1, 1, 0}, 
																{0, 0, 0, 1, 1}, 
																{0, 0, 0, 0, 1} 
																});
	/**
	 * Just a test brush, feel free to fiddle with this
	 */
	public static Brush test = new Brush(2, new float[][]{  	{1, 0, 1, 0, 1}, 
																{0, 1, 1, 1, 0}, 
																{0, 0, 1, 0, 0}, 
																{0, 1, 1, 1, 0}, 
																{1, 0, 1, 0, 1} 
																});
	
	/**
	 * 5x5 Soft roundish brush
	 */
	public static Brush smooth5 = new Brush(2, new float[][]{  	{0.0f,	0.5f, 	0.5f, 0.5f, 0.0f}, 
																	{0.5f, 	0.8f, 	1.0f, 0.8f, 0.5f}, 
																	{0.5f, 	1.0f, 	1.0f, 1.0f, 0.5f}, 
																	{0.5f, 	0.8f, 	1.0f, 0.8f, 0.5f}, 
																	{0.0f, 	0.5f, 	0.5f, 0.5f, 0.0f} 
																	});
	
	/**
	 * Round hard 5x5 brush
	 */
	public static Brush round = new Brush(2, new float[][]{  	{0, 1, 1, 1, 0}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{0, 1, 1, 1, 0} 
																});
	
	/**
	 * Round hard 7x7 brush
	 */
	public static Brush round7 = new Brush(3, new float[][]{  	{0, 0, 1, 1, 1, 0, 0},
																{0, 1, 1, 1, 1, 1, 0}, 
																{1, 1, 1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1, 1, 1},
																{0, 1, 1, 1, 1, 1, 0}, 
																{0, 0, 1, 1, 1, 0, 0}, 
																});

	/**
	 * Round Smooth 7x7 brush
	 */
	public static Brush smooth7 = new Brush(3, new float[][]{  	{0, 0, 0.4f, 0.4f, 0.4f, 0, 0},
																{0, 0.6f, 1, 1, 1, 0.6f, 0},
																{0.4f, 1, 1, 1, 1, 1, 0.4f}, 
																{0.4f, 1, 1, 1, 1, 1, 0.4f}, 
																{0.4f, 1, 1, 1, 1, 1, 0.4f},
																{0, 0.6f, 1, 1, 1, 0.6f, 0},
																{0, 0, 0.4f, 0.4f, 0.4f, 0, 0},
																});
	
	/**
	 * 5x5 square
	 */
	public static Brush square5 = new Brush(2, new float[][]{  	{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1}, 
																{1, 1, 1, 1, 1} 
																});
	/**
	 * Huge 11x11 square
	 */
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
	/**
	 * Hard 11x11 round brush
	 */
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
	/**
	 * Smooth 11x11 round brush
	 */
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
	/**
	 * 15x15 square brush, great for erasing
	 */
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