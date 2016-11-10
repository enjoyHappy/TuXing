package organization.yhwapp.com.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Calendar;

import organization.yhwapp.com.R;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;

/**
 * 个人资料
 */
public class InformationActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.title_name)
    TextView title;

    @ViewInject(R.id.info_name_tv)
    TextView name;// 昵称
    @ViewInject(R.id.info_sex_tv)
    TextView sex;// 性别
    @ViewInject(R.id.info_date_tv)
    TextView date;// 出生日期

    @ViewInject(R.id.info_head_iv)
    ImageView img;// 头像

    @OnClick(R.id.title_back)
    public void back(View v) {
        IntentUtil.defaultFinish(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ViewUtils.inject(this);

        initView();
        initListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        title.setText("个人资料");
    }

    private void initListener() {
        sex.setOnClickListener(this);
        img.setOnClickListener(this);
        name.setOnClickListener(this);
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_sex_tv:
                chooseSexDialog();
                break;
            case R.id.info_date_tv:
                chooseDateDialog();
                break;
            case R.id.info_name_tv:
                IntentUtil.openActivity(this, ChangeNameActivity.class);
                break;
            case R.id.info_head_iv:

                break;
        }
    }

    /**
     * 选择性别
     */
    private void chooseSexDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = {"男", "女"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        sex.setText("男");
                        break;
                    case 1:
                        sex.setText("女");
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * 修改出生日期
     */
    private void chooseDateDialog() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//禁止输入法
        Calendar calendar = Calendar.getInstance();
        String time = date.getText().toString();
        if (!TextUtils.isEmpty(time)) {

        }

        final DatePickerDialog mDialog = new DatePickerDialog(this, null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatePicker datePicker = mDialog.getDatePicker();
                datePicker.clearFocus();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                String time = year + "-" + (month + 1) + "-" + day;
                date.setText(time);
            }
        });
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        mDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        mDialog.show();
    }
}
