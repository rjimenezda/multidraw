package org.i52jianr.multidraw.multiplayer;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.i52jianr.multidraw.NativeFunctions;
import org.i52jianr.multidraw.multiplayer.callbacks.GetGamesHandler;
import org.i52jianr.multidraw.multiplayer.callbacks.UserJoinsHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

public class NativeJavaImpl implements NativeFunctions {

	public SocketIO socket;
	public JSONArray games;
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
							if (callbacks.containsKey("get_games")) {
								GetGamesHandler handler = (GetGamesHandler) callbacks.get("get_games");
								handler.onGamesReceived(new ArrayList<GameDescriptor>());
								callbacks.remove("get_games");
							}
						} else if (on.equals("user_id")) {
							userid =(String) arguments[0]; 
						} else if (on.equals("joined")) {
							if (callbacks.containsKey("user_joins")) {
								UserJoinsHandler handler = (UserJoinsHandler) callbacks.get("user_joins");
								JSONObject obj = (JSONObject) arguments[0];
								handler.onUserJoined(new User(obj.getString("username"), obj.getString("user_id")));
								callbacks.remove("user_joins");
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
		callbacks.put("get_games", handler);
		// We could apply filters here
		socket.emit("get_games");
	}

	@Override
	public void createGame(UserJoinsHandler handler) {
		
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
		
		callbacks.put("user_joins", handler);
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
	public void joinGame() {
		JSONObject args = factoryJSONUserInfo();
		
		socket.emit("join_game", args);
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
	
}
