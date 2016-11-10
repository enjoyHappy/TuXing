package organization.yhwapp.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import organization.yhwapp.com.R;
import organization.yhwapp.com.activity.BlogsActivity;
import organization.yhwapp.com.activity.FriendsActivity;
import organization.yhwapp.com.activity.InformationActivity;
import organization.yhwapp.com.activity.MessageActivity;
import organization.yhwapp.com.activity.MyHuoDongActivity;
import organization.yhwapp.com.activity.SettingActivity;
import organization.yhwapp.com.utils.IntentUtil;

/**
 * 我的页面
 */
public class Fragment_4 extends Fragment implements View.OnClickListener {

    private View v;

    @ViewInject(R.id.img)
    ImageView img;//头像
    @ViewInject(R.id.fg3_name)
    TextView name;//昵称
    @ViewInject(R.id.fg3_tips)
    TextView tips;//标签

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        ViewUtils.inject(this, v);

        initListener();
        return v;
    }

    private void initListener() {
        v.findViewById(R.id.fg3_edit).setOnClickListener(this);
        v.findViewById(R.id.fg3_info).setOnClickListener(this);
        v.findViewById(R.id.fg3_blogs).setOnClickListener(this);
        v.findViewById(R.id.fg3_friends).setOnClickListener(this);
        v.findViewById(R.id.fg3_message).setOnClickListener(this);
        v.findViewById(R.id.fg3_setting).setOnClickListener(this);
        v.findViewById(R.id.fg3_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg3_edit:
                IntentUtil.openActivity(getActivity(),InformationActivity.class);
                break;
            case R.id.fg3_info:
                IntentUtil.openActivity(getActivity(),InformationActivity.class);
                break;
            case R.id.fg3_friends:
                IntentUtil.openActivity(getActivity(),FriendsActivity.class);
                break;
            case R.id.fg3_message:
                IntentUtil.openActivity(getActivity(),MessageActivity.class);
                break;
            case R.id.fg3_blogs:
                IntentUtil.openActivity(getActivity(),BlogsActivity.class);
                break;
            case R.id.fg3_activity:
                IntentUtil.openActivity(getActivity(),MyHuoDongActivity.class);
                break;
            case R.id.fg3_setting:
                IntentUtil.openActivity(getActivity(),SettingActivity.class);
                break;
        }
    }
}
