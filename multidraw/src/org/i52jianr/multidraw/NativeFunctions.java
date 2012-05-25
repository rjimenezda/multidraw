package org.i52jianr.multidraw;

import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;


public interface NativeFunctions {	
	void getGames(GetGamesHandler handler);

	void createGame();
	
	void testNativeness();
	
	void setUsername(String username);

	void byebye();
}
