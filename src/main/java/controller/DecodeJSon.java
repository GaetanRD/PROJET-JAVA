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

public class DecodeJSon {

	private ArrayList<Object> aObj;

	public DecodeJSon(String message) {
		try {
			aObj = new ArrayList<>();
			JSONObject obj = new JSONObject(message);
			aObj.add(obj.getInt("code"));

			// 120 -> all_channel, 110 -> all_members
			if (obj.getInt("code") == 120 || obj.getInt("code") == 110) {
				JSONArray jAObj = new JSONArray();

				if (obj.getInt("code") == 120) {
					
					jAObj = obj.optJSONArray("all_channel");
				} else {
					jAObj = obj.optJSONArray("all_members");
				}

				for (int i = 0; i < jAObj.length(); i++) {
					aObj.add(jAObj.getString(i));
				}

			} else if (obj.getInt("code") == 130) {
				aObj.add(obj.getString("message"));
				aObj.add(obj.getString("user"));
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
