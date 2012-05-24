package org.i52jianr.multidraw;

import java.util.List;

import org.i52jianr.multidraw.multiplayer.GameDescriptor;

public interface NativeFunctions {

	List<GameDescriptor> getGames();
	void testNativeness();
}
