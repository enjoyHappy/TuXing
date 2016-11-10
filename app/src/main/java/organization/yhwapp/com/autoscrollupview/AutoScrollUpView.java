package organization.yhwapp.com.autoscrollupview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 制作主页的向上广告滚动条
 * AdvertisementObject是主页的数据源，假如通过GSON或FastJson获取的实体类
 */
public class AutoScrollUpView extends BaseAutoScrollUpTextView<String> {

	public AutoScrollUpView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AutoScrollUpView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoScrollUpView(Context context) {
		super(context);
	}

	/**
	 * 这里面的高度应该和你的xml里设置的高度一致
	 */
	@Override
	protected int getAdertisementHeight() {
		return 40;
	}

	@Override
	public String getTextInfo(String data) {
		return data;
	}

}
