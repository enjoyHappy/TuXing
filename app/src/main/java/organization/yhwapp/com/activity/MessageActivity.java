package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

import organization.yhwapp.com.R;
import organization.yhwapp.com.adapter.MyFragmentAdapter;
import organization.yhwapp.com.fragment.Fragment_msg_1;
import organization.yhwapp.com.fragment.Fragment_msg_2;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;

/**
 * 消息界面
 */
public class MessageActivity extends BaseActivity {

    @ViewInject(R.id.rg)
    RadioGroup rg;
    @ViewInject(R.id.msg_vp)
    private ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private MyFragmentAdapter adapter;

    @OnClick(R.id.title_back)
    public void back(View v){
        IntentUtil.defaultFinish(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ViewUtils.inject(this);

        initView();
        initListener();
    }

    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sys_msg_rb:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.friends_msg:
                        mViewPager.setCurrentItem(1,false);
                        break;
                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rg.getChildAt(position)) .setChecked(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mFragmentList.add(new Fragment_msg_1());
        mFragmentList.add(new Fragment_msg_2());

        adapter = new MyFragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);
    }
}
