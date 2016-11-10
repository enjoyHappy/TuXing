package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import organization.yhwapp.com.R;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;
import organization.yhwapp.com.utils.RegEx;
import organization.yhwapp.com.utils.ToastUtil;

public class ChangeNameActivity extends BaseActivity {

    @ViewInject(R.id.title_name)
    TextView title;
    @ViewInject(R.id.right_tv)
    TextView right;
    @ViewInject(R.id.change_name_tv)
    EditText nameEt;

    @OnClick(R.id.title_back)
    public void back(View v) {
        IntentUtil.defaultFinish(this);
    }

    @OnClick(R.id.right_tv)
    public void submit(View v) {
        String name = nameEt.getText().toString();
        if (RegEx.setNumbers(name, 8)) {
            ToastUtil.showToast(this, name);
        } else {
            ToastUtil.showToast(this, "昵称不合法");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ViewUtils.inject(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        title.setText("修改昵称");
        right.setVisibility(View.VISIBLE);
        right.setText("确定");
        right.setTextColor(getResources().getColor(R.color.blue));
    }

    @Override
    public void initData() {

    }
}
