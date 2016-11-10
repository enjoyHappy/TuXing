package organization.yhwapp.com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import organization.yhwapp.com.R;
import organization.yhwapp.com.adapter.MyFragmentAdapter;
import organization.yhwapp.com.fragment.Fragment_act_1;
import organization.yhwapp.com.fragment.Fragment_act_2;
import organization.yhwapp.com.fragment.Fragment_act_3;
import organization.yhwapp.com.utils.BaseActivity;
import organization.yhwapp.com.utils.IntentUtil;

/**
 * 我的活动
 */
public class MyHuoDongActivity extends BaseActivity {

	@OnClick(R.id.title_back)
	public void back(View view) {
		IntentUtil.defaultFinish(this);
	}

	@ViewInject(R.id.title_name)
	TextView tvTitle;

	@ViewInject(R.id.tab_1)
	RadioButton first_rb;
	@ViewInject(R.id.tab_2)
	RadioButton secend_rb;
	@ViewInject(R.id.tab_3)
	RadioButton three_rb;

	@ViewInject(R.id.rg)
	RadioGroup radiogroup_my_order;

	/**
	 * 下标指示线
	 */
	@ViewInject(R.id.ll_my_tag)
	private LinearLayout ll_line;

	@ViewInject(R.id.vp_my_activity)
	private ViewPager mViewPager;

	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private MyFragmentAdapter adapter;
	// 横线 tag的坐标
	private float mStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_huodong);

		ViewUtils.inject(this);
		initView();
		initListenner();
		initData();
		
		judgeChecked(0);
	}

	@SuppressWarnings("deprecation")
	private void initListenner() {

		// RdiaoButton 的点击事件监听
		radiogroup_my_order
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.tab_1:
							mViewPager.setCurrentItem(0);
							break;
						case R.id.tab_2:
							mViewPager.setCurrentItem(1);
							break;
						case R.id.tab_3:
							mViewPager.setCurrentItem(2);
							break;
						}
					}

				});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				((RadioButton) radiogroup_my_order.getChildAt(position))
						.setChecked(true);

				moveImgTag((RadioButton) radiogroup_my_order
						.getChildAt(position));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		tvTitle.setText("我的活动");

		mFragmentList.add(new Fragment_act_1());
		mFragmentList.add(new Fragment_act_2());
		mFragmentList.add(new Fragment_act_3());

		adapter = new MyFragmentAdapter(getSupportFragmentManager(),
				mFragmentList);
		mViewPager.setAdapter(adapter);
		mStart = ll_line.getLeft();
	}

	private void judgeChecked(int index) {
		switch (index) {
		case 1:
			secend_rb.setChecked(true);
			mViewPager.setCurrentItem(1);
			moveImgTag(secend_rb);
			break;
		case 2:
			three_rb.setChecked(true);
			mViewPager.setCurrentItem(2);
			moveImgTag(three_rb);
			break;

		default:
			first_rb.setChecked(true);
			mViewPager.setCurrentItem(0);
			break;
		}
	}

	private void moveImgTag(RadioButton button) {

		float mEnd = button.getLeft();
		ObjectAnimator.ofFloat(ll_line, "translationX", mStart, mEnd).setDuration(300).start();

		mStart = mEnd;
	}

}
