package controller;

import org.json.JSONObject;
import org.json.JSONTokener;

public class CreateJSon {
	
	public CreateJSon(String message) {
		JSONObject json = new JSONObject(new JSONTokener(message));

		
	}

}
