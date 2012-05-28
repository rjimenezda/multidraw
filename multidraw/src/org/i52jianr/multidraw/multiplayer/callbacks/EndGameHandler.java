package org.i52jianr.multidraw.multiplayer.callbacks;

/**
 * Handler to be called on End Game events 
 * @author Román Jiménez
 *
 */
public interface EndGameHandler {
	/**
	 * This will be fired when a Game has been ended by someone
	 * @param why The reason why the game ended
	 */
	void onGameEnd(String why);
}
