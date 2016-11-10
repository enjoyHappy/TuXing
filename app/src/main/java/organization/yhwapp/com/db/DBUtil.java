package organization.yhwapp.com.db;

import android.os.Handler;

import organization.yhwapp.com.application.MyApplication;

/**
 * 数据库工具类
 */
public class DBUtil {

	public static Handler mHandler = new Handler();

	public static void saveData(final String key, final String data) {
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				DBHelper.getInstance(MyApplication.getInstance()).saveOrUpdate(key, data);
			}

		});
	}

	public static void getData(final String key, final GetDbData getDbData) {
		mHandler.post(new Runnable() {

			@Override
			public void run() {

				String data = DBHelper.getInstance(MyApplication.getInstance()).getData(key);
				getDbData.getData(data);
			}

		});
	}

}
