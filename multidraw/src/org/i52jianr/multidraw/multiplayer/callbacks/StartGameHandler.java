package org.i52jianr.multidraw.multiplayer.callbacks;

/**
 * This should be used and called when a game starts
 * @author Román Jiménez
 *
 */
public interface StartGameHandler {
	/**
	 * Called when a Game starts
	 * @param word The Word you have to draw / solve
	 */
	void onGameStarted(String word);
}
