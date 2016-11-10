package organization.yhwapp.com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import organization.yhwapp.com.R;

/**
 * @author lishuai 用于跳转和finish的工具类，带动画
 */
public class IntentUtil {

    /**
     * Title: 跳转界面openActivity Description:直接跳转到目标界面 跳转对象的Activity.class
     */
    public static void openActivity(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        ((Activity) context).startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.screen_right_in, R.anim.screen_left_out);
    }

    /**
     * Title: 跳转界面openActivity Description:请求返回的 跳转
     *
     * @param cls
     * @param requestCode
     */
    public static void openActivity(Context context, Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.screen_right_in, R.anim.screen_left_out);
    }

    /**
     * Title: 重写openActivity Description:传递传数据的跳转
     *
     * @param cls
     * @param extras
     */
    public static void openActivity(Context context, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(extras);
        ((Activity) context).startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.screen_right_in, R.anim.screen_left_out);
    }

    /**
     * <p/>
     * Title: 重写openActivity Description:传递传数据并且要求返回值的跳转
     *
     * @param cls
     * @param extras      数据Bundle对象
     * @param requestCode 请求码
     */
    public static void openActivity(Context context, Class<?> cls, Bundle extras, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(extras);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.screen_right_in, R.anim.screen_left_out);
    }

    /**
     * Title:退出Activity defaultFinish Description: 默认退出方法
     */
    public static void defaultFinish(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.screen_left_in, R.anim.screen_right_out);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
