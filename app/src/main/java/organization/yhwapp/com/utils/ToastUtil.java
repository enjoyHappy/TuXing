package organization.yhwapp.com.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	/**
	 * Title: 弹出吐司封装方法showToast Description: 弹出消息，避免多次弹出消息，出现很多土司的现象
	 * 
	 * @param str
	 *            需要弹出的信息内容
	 */
	private static Toast toast;

	private ToastUtil() {
	}

	public static void showToast(Context context, String str) {

		// if (str == null || "".equals(str)) {
		// return;
		// }
		// 如果Toast为空的话再创建，避免短时间内同时创建多个弹出信息
		if (toast == null) {
			// 创建
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		// 设置信息
		toast.setText(str);
		// 不要忘了show出来
		toast.show();
	}

	public static void showToastLong(Context context, String str) {
		// 如果Toast为空的话再创建，避免短时间内同时创建多个弹出信息
		if (toast == null) {
			// 创建
			toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
		}
		// 设置信息
		toast.setText(str);
		// 不要忘了show出来
		toast.show();
	}

	public static void showCanNotAccessNet(Context context) {
		// 如果Toast为空的话再创建，避免短时间内同时创建多个弹出信息
		if (toast == null) {
			// 创建
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		// 设置信息
		toast.setText("无法访问服务器，请检查网络连接！");
		// 不要忘了show出来
		toast.show();
	}

}
