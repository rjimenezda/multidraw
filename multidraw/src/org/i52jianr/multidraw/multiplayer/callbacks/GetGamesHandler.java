package org.i52jianr.multidraw.multiplayer.callbacks;

import java.util.List;

import org.i52jianr.multidraw.multiplayer.GameDescriptor;
/**
 * After issuing a "get games" command to the server, this should be called
 * @author Román Jiménez
 *
 */
public interface GetGamesHandler {
	/**
	 * This will be called with the List of {@link GameDescriptor} received 
	 * @param games {@link GameDescriptor} list received from the Server
	 */
	void onGamesReceived(List<GameDescriptor> games);
	
}
