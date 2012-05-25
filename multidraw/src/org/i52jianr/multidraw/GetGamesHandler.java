package org.i52jianr.multidraw;

import java.util.List;

import org.i52jianr.multidraw.multiplayer.GameDescriptor;

public interface GetGamesHandler {

	void onGamesReceived(List<GameDescriptor> games);
	
}
