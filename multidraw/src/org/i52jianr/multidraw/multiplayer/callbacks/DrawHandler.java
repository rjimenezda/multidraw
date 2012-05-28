package org.i52jianr.multidraw.multiplayer.callbacks;

/**
 * Handler to be called on draw events
 * @author Román Jiménez
 *
 */
public interface DrawHandler {
	/**
	 * This will be called from the server when the game creator issues a draw event
	 * @param x Normalized x position to draw
	 * @param y Normalized y position to draw
	 * @param r red component of the color
	 * @param g green component of the color
	 * @param b blue component of the color
	 * @param brush Brush index to set, -1 means clear screen
	 */
	void onDraw(int x, int y, int r, int g, int b, int brush);
}
