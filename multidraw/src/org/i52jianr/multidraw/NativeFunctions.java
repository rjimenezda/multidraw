package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;


public interface NativeFunctions {	
	void getGames(GetGamesHandler handler);

	void createGame(UserJoinsHandler handler,  StartGameHandler startHandler);
	
	void joinGame(String gameId, StartGameHandler startHandler, EndGameHandler endHandler);
	
	void testNativeness();
	
	void setUsername(String username);

	void byebye();

	void endGame();
}

