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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
					
					if (on.equals("current_games")) {
						if (callbacks.containsKey("get_games")) {
							GetGamesHandler handler = (GetGamesHandler) callbacks.get("get_games");
							handler.onGamesReceived(new ArrayList<GameDescriptor>());
							callbacks.remove("get_games");
						}
					} else if (on.equals("user_id")) {
						userid =(String) arguments[0]; 
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
	public void createGame() {
		
		JSONObject args = new JSONObject();
		try {
			args.put("user_id", userid);
			args.put("name", "Test game name");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		socket.emit("create_game", args);
	}

	@Override
	public void setUsername(String username) {
		JSONObject args = new JSONObject();
		try {
			args.put("user_id", userid);
			args.put("username", username);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		this.username = username;
		socket.emit("signup", args);
		
	}

	@Override
	public void byebye() {
		JSONObject args = new JSONObject();
		try {
			args.put("user_id", userid);
			args.put("username", username);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		socket.emit("byebye", args);
		socket.disconnect();
	}
	
}
