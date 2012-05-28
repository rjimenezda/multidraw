package org.i52jianr.multidraw.multiplayer;

import org.i52jianr.multidraw.multiplayer.callbacks.DrawHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;


public interface NativeFunctions {	
	void getGames(GetGamesHandler handler);

	void createGame(String gameName, UserJoinsHandler handler,  StartGameHandler startHandler);
	
	void joinGame(String gameId, StartGameHandler startHandler, EndGameHandler endHandler);
	
	void testNativeness();
	
	void setUsername(String username);

	void byebye();

	void endGame(String why);

	void gameStarted(EndGameHandler endHandler);

	void draw(int x, int y, int r, int g, int b, int brush);

	void onDraw(DrawHandler handler);
	
	void cleanCallbacks();
}

