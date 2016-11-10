package organization.yhwapp.com.application;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.URLConnectionNetworkExecutor;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initNohttp();
    }

    /**
     * 初始化NoHttp
     */
    private void initNohttp() {
        NoHttp.Config config = new NoHttp.Config();
        config.setConnectTimeout(5000);
        config.setReadTimeout(5000);
        config.setNetworkExecutor(new URLConnectionNetworkExecutor());

        NoHttp.initialize(this, config);
    }
}
