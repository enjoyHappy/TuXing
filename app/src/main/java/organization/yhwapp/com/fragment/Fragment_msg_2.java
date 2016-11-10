package organization.yhwapp.com.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import organization.yhwapp.com.R;
import organization.yhwapp.com.adapter.MsgFriendsAdapter;

/**
 * 好友消息
 */
public class Fragment_msg_2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.msg_2_srl)
    SwipeRefreshLayout srl;
    @ViewInject(R.id.msg_2_lv)
    ListView lv;

    private View v;
    private MsgFriendsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_msg_2, null);
            ViewUtils.inject(this, v);
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }

        initView();
        initData();
        return v;
    }

    private void initView() {
        srl.setColorSchemeResources(R.color.blue);
        srl.setOnRefreshListener(this);
    }

    private void initData() {
        adapter = new MsgFriendsAdapter();
        lv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(false);
            }
        }, 500);
    }
}
