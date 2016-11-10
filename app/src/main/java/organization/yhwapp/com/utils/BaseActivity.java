package organization.yhwapp.com.utils;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

public abstract class BaseActivity extends FragmentActivity {

    public abstract void initData();

    public abstract void initView();

    /** 屏幕的宽，高 ScreenW */
    public static int SCREEN_W, SCREEN_H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSize();
        // 4.4.2 以上让，手机状态透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化，宽 高
     */
    public void initSize() {
        // 获得量度对象
        DisplayMetrics metrics = new DisplayMetrics();
        // 获得手机的屏幕量度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // 屏幕宽
        SCREEN_W = metrics.widthPixels;
        // 屏幕高
        SCREEN_H = metrics.heightPixels;
    }
}
