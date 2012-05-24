package org.i52jianr.multidraw.multiplayer;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;
import java.util.List;

import org.i52jianr.multidraw.NativeFunctions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeJavaImpl implements NativeFunctions {

	public SocketIO socket;
	public JSONArray games;
	
	public NativeJavaImpl() {
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
				public void on(String arg0, IOAcknowledge arg1, Object... arg2) {
//					System.out.println("On: " + arg0);
//					int a = 0;
//					a++;
					games = (JSONArray) arg2[0];
					notify();
				}
			});
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<GameDescriptor> getGames() {
		socket.emit("get_games");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONArray tmp = games;
		return null;
	}

	@Override
	public void testNativeness() {
		// TODO Auto-generated method stub
		
	}
	
}
