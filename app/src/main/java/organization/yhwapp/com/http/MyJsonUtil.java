package organization.yhwapp.com.http;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonUtil {

	private static JSONObject root;

	public static boolean isSuccess(Context context, String str) {
		if(TextUtils.isEmpty(str)){
			return false;
		}
		boolean isSuccess = false;
		try {
			root = new JSONObject(str);
			isSuccess = root.getBoolean("manageType");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public static String getData(String str) {
		if(TextUtils.isEmpty(str)){
			return "";
		}
		JSONObject data = null;
		String info = "";
		try {
			root = new JSONObject(str);
			data = root.getJSONObject("entityClass");
			info = data.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

	public static String getMsg(String str) {
		if(TextUtils.isEmpty(str)){
			return "";
		}
		String info = null;
		try {
			root = new JSONObject(str);
			if (root == null) {
				return "";
			}
			info = root.getString("msg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

}
