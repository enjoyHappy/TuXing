package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import organization.yhwapp.com.R;
import organization.yhwapp.com.fragment.Fragment_1;
import organization.yhwapp.com.fragment.Fragment_2;
import organization.yhwapp.com.fragment.Fragment_3;
import organization.yhwapp.com.fragment.Fragment_4;
import organization.yhwapp.com.utils.BaseActivity;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.rg)
    RadioGroup rg;

    private Fragment fg1, fg2, fg3,fg4;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        showFragment(1);
        initListener();
    }

    private void showFragment(int index) {
        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 1:
                if (fg1 == null) {
                    fg1 = new Fragment_1();
                    ft.add(R.id.content, fg1);
                } else {
                    ft.show(fg1);
                }
                break;
            case 2:
                if (fg2 == null) {
                    fg2 = new Fragment_2();
                    ft.add(R.id.content, fg2);
                } else {
                    ft.show(fg2);
                }
                break;
            case 3:
                if (fg3 == null) {
                    fg3 = new Fragment_3();
                    ft.add(R.id.content, fg3);
                } else {
                    ft.show(fg3);
                }
                break;
            case 4:
                if (fg4 == null) {
                    fg4 = new Fragment_4();
                    ft.add(R.id.content, fg4);
                } else {
                    ft.show(fg4);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (fg1 != null) {
            ft.hide(fg1);
        }
        if (fg2 != null) {
            ft.hide(fg2);
        }
        if (fg3 != null) {
            ft.hide(fg3);
        }
        if (fg4 != null) {
            ft.hide(fg4);
        }
    }

    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.table_1:
                        showFragment(1);
                        break;
                    case R.id.table_2:
                        showFragment(2);
                        break;
                    case R.id.table_3:
                        showFragment(3);
                        break;
                    case R.id.table_4:
                        showFragment(4);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
//        String url = "http://u.uh520.com/codeController/code/countCodeDateCodeType.yhw";
//        // 请求对象
//        Request request = NoHttp.createStringRequest(url, RequestMethod.POST);
//        // 非标准Http协议，REQUEST_NETWORK_FAILED_READ_CACHE
//        request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
//
//        request.add("targetValue", "8af409d34f318260014f318c799c03d6");
//        HttpUtilManager.getInstance().doNotingPostData(this, "访问流量统计", true, request, new ObjectCallback<String>() {
//            @Override
//            public void onSuccess(String modle) {
//
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorString) {
//                ToastUtil.showToast(MainActivity.this, errorString);
//            }
//        });
    }

    @Override
    public void initView() {

    }
}
