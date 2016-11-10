package organization.yhwapp.com.http;

import android.content.Context;

import com.google.gson.Gson;
import com.lidroid.xutils.util.LogUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import organization.yhwapp.com.utils.ProgressDialogUtil;
import organization.yhwapp.com.utils.ToastUtil;

/**
 * Created by Administrator on 2016/10/25.
 */
public class HttpUtilManager {

    /**
     * 请求队列
     */
    private RequestQueue requestQueue;


    private static HttpUtilManager instance;

    // 私有化 构造器
    private HttpUtilManager() {
        requestQueue = NoHttp.newRequestQueue();
    }

    public static HttpUtilManager getInstance() {
        if (instance == null) {
            synchronized (HttpUtilManager.class) {
                if (instance == null) {
                    instance = new HttpUtilManager();
                }
            }
            instance = new HttpUtilManager();
        }
        return instance;
    }

    /***
     * 带json解析的请求
     *
     * @param context
     * @param tag
     * @param isShowDialog
     * @param url
     * @param callback
     */
    public void doPostData(final Context context, final String tag, final boolean isShowDialog, String url, Request request, final ObjectCallback callback) {

        // 请求对象
        requestQueue.add(0, request, new OnResponseListener() {
            @Override
            public void onStart(int i) {
                if (isShowDialog) {
                    ProgressDialogUtil.getInstance().showtPD(context);
                }
            }

            @Override
            public void onSucceed(int i, Response response) {
                LogUtils.d(tag + "接口 请求--onSuccess");
                String result = response.get().toString();

                if (MyJsonUtil.isSuccess(context, result)) {

                    LogUtils.d(tag + "接口 获取数据 成功" + result);

                    try {
                        Gson gson = new Gson();
                        Object obj = gson.fromJson(MyJsonUtil.getData(result), callback.getDataClass());
                        if (callback != null) {
                            callback.onSuccess(obj);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        LogUtils.d("json解析错误");
                    }
                } else {
                    LogUtils.d(tag + "接口 请求成功——获取数据——失败" + result);
                    if (callback != null) {
                        callback.onFailure(2, MyJsonUtil.getMsg(result));
                    }
                }
            }

            @Override
            public void onFailed(int i, Response response) {
                String result = response.get().toString();

                ToastUtil.showToast(context, MyJsonUtil.getMsg(result));
                callback.onFailure(response.responseCode(), result);
            }

            @Override
            public void onFinish(int i) {
                if (isShowDialog) {
                    ProgressDialogUtil.getInstance().dismiss();
                }
            }
        });

    }

    /***
     * 不带json解析的请求
     *
     * @param context
     * @param tag
     * @param isShowDialog
     * @param request
     * @param callback
     */
    public void doNotingPostData(final Context context, final String tag, final boolean isShowDialog, Request request, final ObjectCallback callback) {

        // 请求对象
        requestQueue.add(0, request, new OnResponseListener() {
            @Override
            public void onStart(int i) {
                if (isShowDialog) {
                    ProgressDialogUtil.getInstance().showtPD(context);
                }
            }

            @Override
            public void onSucceed(int i, Response response) {
                LogUtils.d(tag + "接口 请求--onSuccess");
                String result = response.get().toString();

                if (MyJsonUtil.isSuccess(context, result)) {
                    LogUtils.d(tag + "接口 获取数据 成功" + result);

                    callback.onSuccess(response.get());
                } else {
                    LogUtils.d(tag + "接口 请求成功——获取数据——失败" + result);
                    if (callback != null) {
                        callback.onFailure(response.responseCode(), MyJsonUtil.getMsg(result));
                    }
                }
            }

            @Override
            public void onFailed(int i, Response response) {
                try {
                    String result = response.get().toString();

                    ToastUtil.showToast(context, MyJsonUtil.getMsg(result));
                    callback.onFailure(response.responseCode(), result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish(int i) {
                if (isShowDialog) {
                    ProgressDialogUtil.getInstance().dismiss();
                }
            }
        });
    }
}
