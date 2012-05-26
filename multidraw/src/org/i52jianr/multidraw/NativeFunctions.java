package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;


public interface NativeFunctions {	
	void getGames(GetGamesHandler handler);

	void createGame(UserJoinsHandler handler);
	
	void joinGame(/* StartGameHandler handler */);
	
	void testNativeness();
	
	void setUsername(String username);

	void byebye();
}

