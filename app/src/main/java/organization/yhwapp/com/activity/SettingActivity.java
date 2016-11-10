package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import organization.yhwapp.com.R;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;

public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.title_name)
    TextView title;

    @OnClick(R.id.title_back)
    public void back(View v){
        IntentUtil.defaultFinish(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ViewUtils.inject(this);

        initView();
    }

    @Override
    public void initView() {
        title.setText("设置");
    }

    @Override
    public void initData() {

    }
}
