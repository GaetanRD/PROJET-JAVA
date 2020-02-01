/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used to decode the messages from the server
 * 
 */

package controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.userConfigs.UserConfigs;

public class DecodeJSon {

	private ArrayList<Object> aObj;

	public DecodeJSon(String message) {
		try {
			aObj = new ArrayList<>();
			JSONObject obj = new JSONObject(message);
			aObj.add(obj.getInt("code"));

			if (UserConfigs.getInstruction() == "list_channels" && obj.getInt("code") == 120) {
				JSONArray jAObj = new JSONArray();
				jAObj = obj.optJSONArray("all_members");

				for (int i = 0; i < jAObj.length(); i++) {
					aObj.add(jAObj.getString(i));
				}

			} else {
				aObj.add(obj.getString("message"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Object> getaObj() {
		return aObj;
	}

	public void setaObj(ArrayList<Object> aObj) {
		this.aObj = aObj;
	}

}
