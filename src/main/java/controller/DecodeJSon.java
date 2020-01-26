/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used to decode the messages from the server
 * 
 */

package controller;

import java.util.ArrayList;

import org.json.JSONObject;

public class DecodeJSon {

	/*
	 * Variables
	 */
	private JSONObject obj;
	private ArrayList<Object> aObj;

	public DecodeJSon(String message) {
		obj = new JSONObject(message);
		aObj = new ArrayList<Object>();

		aObj.add(obj.getInt("code"));
		aObj.add(obj.getString("message"));
	}

	public ArrayList<Object> getaObj() {
		return aObj;
	}

	public void setaObj(ArrayList<Object> aObj) {
		this.aObj = aObj;
	}

}
