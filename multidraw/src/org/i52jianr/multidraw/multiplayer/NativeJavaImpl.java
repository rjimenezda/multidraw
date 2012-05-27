package org.i52jianr.multidraw.multiplayer;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.i52jianr.multidraw.NativeFunctions;
import org.i52jianr.multidraw.multiplayer.callbacks.EndGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.StartGameHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

public class NativeJavaImpl implements NativeFunctions {

	private static final String USER_JOINS_CALLBACK = "user_joins";
	private static final String GET_GAMES_CALLBACK = "get_games";
	private static final String START_GAME_CALLBACK = "start_game";
	private static final String END_GAME_CALLBACK = "end_game";
	public SocketIO socket;
	private String userid;
	private String username;
	
	public Hashtable<String, Object> callbacks;
	
	public NativeJavaImpl() {
		callbacks = new Hashtable<String, Object>();
		try {
			socket = new SocketIO("http://192.168.0.198:1234");
			
			socket.connect(new IOCallback() {
				
				@Override
				public void onMessage(JSONObject json, IOAcknowledge arg1) {
                    try {
						System.out.println("onMessage (JSON) => Server said:" + json.toString(2));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onMessage(String message, IOAcknowledge arg1) {
					System.out.println("onMessage => Server said: " + message);
				}
				
				@Override
				public void onError(SocketIOException socketIOException) {
	                System.out.println("an Error occured");
	                socketIOException.printStackTrace();
				}
				
				@Override
				public void onDisconnect() {
	                System.out.println("Connection terminated.");
				}
				
				@Override
				public void onConnect() {
	                System.out.println("Connection established");
	                socket.send("Hello nurse!");
				}
				
				@Override
				public void on(String on, IOAcknowledge ack, Object... arguments) {
					
					// Horrible code, wish I had more time to refactor and do all this with reflect or templates
					try {
						if (on.equals("current_games")) {
							if (callbacks.containsKey(GET_GAMES_CALLBACK)) {
								GetGamesHandler handler = (GetGamesHandler) callbacks.get(GET_GAMES_CALLBACK);
								ArrayList<GameDescriptor> games = new ArrayList<GameDescriptor>();
								JSONArray array = (JSONArray) arguments[0];
								for (int i = 0; i < array.length(); i++) {
									JSONObject obj = array.getJSONObject(i);
									games.add(new GameDescriptor(obj.getString("game_id"), 
																obj.getString("name"), 
																obj.getString("owner_name")));
								}
								handler.onGamesReceived(games);
								callbacks.remove(GET_GAMES_CALLBACK);
							}
						} else if (on.equals("user_id")) {
							userid =(String) arguments[0]; 
						} else if (on.equals("joined")) {
							if (callbacks.containsKey(USER_JOINS_CALLBACK)) {
								UserJoinsHandler handler = (UserJoinsHandler) callbacks.get(USER_JOINS_CALLBACK);
								JSONObject obj = (JSONObject) arguments[0];
								handler.onUserJoined(new User(obj.getString("username"), obj.getString("user_id")));
								callbacks.remove(USER_JOINS_CALLBACK);
							}
						} else if (on.equals("game_start")) {
							if (callbacks.containsKey(START_GAME_CALLBACK)) {
								StartGameHandler handler = (StartGameHandler) callbacks.get(START_GAME_CALLBACK);
								handler.onGameStarted();
								callbacks.remove(START_GAME_CALLBACK);
							}
						} else if (on.equals("endgame")) {
							if (callbacks.containsKey(END_GAME_CALLBACK)) {
								EndGameHandler handler = (EndGameHandler) callbacks.get(END_GAME_CALLBACK);
								JSONObject rc = (JSONObject) arguments[0];
								handler.onGameEnd(rc.getString("why"));
								callbacks.remove(END_GAME_CALLBACK);
							}
						}
					} catch (JSONException e) {
						Gdx.app.error("JSON", "Caught JSONException on : " + on, e);
					}
					
					
				}
			});
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void testNativeness() {
		
		JSONObject args = new JSONObject();
		try {
			args.put("game", "15");
			args.put("x", 150);
			args.put("y", 100);
		} catch (JSONException e) {
			// Why in the seven hells ?
			e.printStackTrace();
		}
		
		socket.emit("send_message", args);
	}

	@Override
	public void getGames(GetGamesHandler handler) {
		callbacks.put(GET_GAMES_CALLBACK, handler);
		// We could apply filters here
		socket.emit(GET_GAMES_CALLBACK);
	}

	@Override
	public void createGame(UserJoinsHandler handler, StartGameHandler startHandler) {
		
		JSONObject args = new JSONObject();
		try {
			args.put("owner", userid);
			args.put("name", "Test game name");
			args.put("username", username);
			// Get a random word
			args.put("word", "Dignity");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		callbacks.put(USER_JOINS_CALLBACK, handler);
		callbacks.put(START_GAME_CALLBACK, startHandler);
		socket.emit("create_game", args);
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
		JSONObject args = factoryJSONUserInfo();
		socket.emit("signup", args);	
	}

	@Override
	public void byebye() {
		JSONObject args = factoryJSONUserInfo();

		socket.emit("byebye", args);
		socket.disconnect();
	}

	@Override
	public void joinGame(String gameId, StartGameHandler handler, EndGameHandler endHandler) {
		callbacks.put(START_GAME_CALLBACK, handler);
		callbacks.put(END_GAME_CALLBACK, endHandler);
		JSONObject args = factoryJSONUserInfo();
		putJSON(args, "game_id", gameId);
		
		socket.emit("join_game", args);
	}
	
	@Override
	public void endGame() {
		JSONObject args = factoryJSONUserInfo();
		putJSON(args, "why", "Server stopped the game");
		socket.emit("end_game", args);
	}
	
	/* Helper method */
	private JSONObject factoryJSONUserInfo() {
		JSONObject args = new JSONObject();
		try {
			args.put("user_id", userid);
			args.put("username", username);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return args;
	}
	
	private void putJSON(JSONObject obj, String key, String value) {
		try {
			obj.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
