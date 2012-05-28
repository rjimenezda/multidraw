package org.i52jianr.multidraw.multiplayer.callbacks;

import org.i52jianr.multidraw.multiplayer.User;
/**
 * This is registered to know when a user Joins
 * @author Román Jiménez
 *
 */
public interface UserJoinsHandler {
	/**
	 * When a user Joins this will be called with the user that just joined
	 * @param user {@link User} that just joined
	 */
	void onUserJoined(User user);
}
