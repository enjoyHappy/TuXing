package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import organization.yhwapp.com.R;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;

/**
 * 我的博客
 */
public class BlogsActivity extends BaseActivity {

    @ViewInject(R.id.title_name)
    TextView title;
    @ViewInject(R.id.right_tv)
    TextView right_tv;

    @ViewInject(R.id.blogs_srl)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.blogs_lv)
    ListView lv;

    ArrayAdapter<String> adapter;

    @OnClick(R.id.title_back)
    public void back(View v) {
        IntentUtil.defaultFinish(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        ViewUtils.inject(this);

        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        title.setText("博客");
        right_tv.setText("发布");
        right_tv.setVisibility(View.VISIBLE);

        right_tv.setBackgroundResource(R.drawable.selector_button_white);
        refreshLayout.setColorSchemeResources(R.color.blue);
    }

    @Override
    public void initData() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.add("博客1");
        adapter.add("博客2");
        adapter.add("博客3");
        lv.setAdapter(adapter);
    }

    java.text.DecimalFormat df = new java.text.DecimalFormat("#");
    private void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        adapter.clear();
                        for (int i = 0; i < 5; i++) {
                            adapter.add("博客" + df.format(Math.random() * 100));
                        }
                        adapter.notifyDataSetChanged();
                    }
                },500);
            }
        });
    }

}
