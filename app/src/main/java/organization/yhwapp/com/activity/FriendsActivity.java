package organization.yhwapp.com.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import organization.yhwapp.com.R;
import organization.yhwapp.com.adapter.FriendsAdapter;
import organization.yhwapp.com.bean.ItemFriends;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;
import organization.yhwapp.com.utils.PingYinUtil;
import organization.yhwapp.com.view.LetterListView;

/**
 * 我的好友
 */
public class FriendsActivity extends BaseActivity {

    @ViewInject(R.id.title_name)
    TextView title;

    TextView overlay;

    @ViewInject(R.id.friends_srl)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.friends_lv)
    ListView lv;

    @ViewInject(R.id.letter)
    LetterListView letterLv;

    @ViewInject(R.id.top_layout)
    LinearLayout xuanfuLayout; // 顶部悬浮的layout
    @ViewInject(R.id.top_char)
    TextView xuanfaText; // 悬浮的文字
    private int lastFirstVisibleItem = -1;

    private FriendsAdapter adapter;
    private ArrayList<ItemFriends> list = new ArrayList<>();

    OverlayThread overlayThread;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            overlay.setVisibility(View.GONE);
        }
    };

    @OnClick(R.id.title_back)
    public void back(View v) {
        IntentUtil.defaultFinish(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ViewUtils.inject(this);

        initView();
        initOverlay();
        initData();
        initListener();
        initHeaderListener();
    }

    @Override
    public void initView() {
        title.setText("好友列表");
        refreshLayout.setColorSchemeResources(R.color.blue);
    }

    @Override
    public void initData() {
        fillData();
    }

    private void initListener() {
        letterLv.setOnTouchingLetterChangedListener(new LetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s, int type) {
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);

                if(adapter.alphaIndexer.get(s) != null){
                    int index = adapter.alphaIndexer.get(s);
                    lv.setSelection(index);
                }

                if (type == MotionEvent.ACTION_UP) {
                    handler.removeCallbacks(overlayThread);
                    handler.postDelayed(overlayThread, 1000);
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 500);
            }
        });
    }

    private void fillData(){
        String[] data = getResources().getStringArray(R.array.date);
        for (int i = 0; i < data.length; i++){
            ItemFriends itemFriends = new ItemFriends();
            itemFriends.name = data[i];
            list.add(itemFriends);
        }
        Collections.sort(list,comparator);
        adapter = new FriendsAdapter(list);
        lv.setAdapter(adapter);
    }

    /**
     * a-z排序
     */
    Comparator comparator = new Comparator<ItemFriends>() {
        @Override
        public int compare(ItemFriends lhs, ItemFriends rhs) {
            String a = PingYinUtil.converterToFirstSpell(lhs.name);
            String b = PingYinUtil.converterToFirstSpell(rhs.name);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    /**
     * 创建字母悬浮窗
     */
    private void initHeaderListener() {

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int section = adapter.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = adapter.getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout.getLayoutParams();
                    params.topMargin = 0;
                    xuanfuLayout.setLayoutParams(params);
                    xuanfaText.setText(String.valueOf((char) section).toUpperCase());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = xuanfuLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout.getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            xuanfuLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                xuanfuLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    /**
     * 初始化汉语拼音首字母弹出提示框
     */
    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    /**
     * 设置overlay不可见
     */
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    }
}
