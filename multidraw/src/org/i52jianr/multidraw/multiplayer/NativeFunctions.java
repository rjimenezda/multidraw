package org.i52jianr.multidraw.multiplayer;

import org.i52jianr.multidraw.multiplayer.callbacks.DrawHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;
import org.i52jianr.multidraw.screens.MultidrawBaseGameScreen;

/**
 * Interface that defines network functionality and callbacks for the game
 * @author Román Jiménez
 *
 */
public interface NativeFunctions {
	/**
	 * Call to ask for a list of games from the server
	 * @param handler Handler to be executed on response
	 */
	void getGames(GetGamesHandler handler);

	/**
	 * Creates a game on the server with the given arguments
	 * @param handler Handler to be executed when a user Joins
	 * @param gameName Game name
	 * @param startHandler Handler to be executed when game starts
	 */
	void createGame(String gameName, UserJoinsHandler handler,  StartGameHandler startHandler);
	
	/**
	 * Joins a game with the given arguments
	 * @param endHandler Handler to be executed when the game ends
	 * @param gameName Game ID
	 * @param startHandler Handler to be executed when game starts
	 */
	void joinGame(String gameId, StartGameHandler startHandler, EndGameHandler endHandler);
	
	/**
	 * Test function to try out native code, ignore
	 */
	void testNativeness();
	
	/**
	 * Setter for the username field
	 * @param username New username value
	 */
	void setUsername(String username);

	/**
	 * This should be called on disconnect or when quitting the App
	 */
	void byebye();

	/**
	 * Finish a Game with some explanation
	 * @param why Explanation why the game is over
	 */
	void endGame(String why);

	/**
	 * Hack to be used when we join the {@link MultidrawBaseGameScreen}
	 * @param endHandler handler to be executed on Game end
	 */
	void gameStarted(EndGameHandler endHandler);

	/**
	 * Call this to issue a "draw" event with NORMALIZED coordinates
	 * @param x x-axis position to draw
	 * @param y y-axis position to draw
	 * @param r red color component
	 * @param g green color component
	 * @param b blue color component
	 * @param brush brush index, send -1 to issue a "clear screen"
	 */
	void draw(int x, int y, int r, int g, int b, int brush);

	/**
	 * This is called when a draw event is captured
	 * @param handler Handler to be executed when the event occurs
	 */
	void onDraw(DrawHandler handler);
	
	/**
	 * Clean the callbacks registered, useful when going back to the menu screen
	 * where no callbacks should be alive
	 */
	void cleanCallbacks();
}

