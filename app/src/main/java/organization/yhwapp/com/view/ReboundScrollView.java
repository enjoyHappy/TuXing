package organization.yhwapp.com.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * 有弹性的ScrollView 实现下拉弹回和上拉弹回 在这个项目中
 */
public class ReboundScrollView extends ScrollView {

	private static final String TAG = "ElasticScrollView";

	// 移动因子, 是一个百分比, 比如手指移动了100px, 那么View就只移动50px
	// 目的是达到一个延迟的效果
	private static final float MOVE_FACTOR = 0.5f;

	// 松开手指后, 界面回到正常位置需要的动画时间
	private static final int ANIM_TIME = 260;

	// ScrollView的子View， 也是ScrollView的唯一一个子View
	private View contentView;

	// 手指按下时的Y值, 用于在移动时计算移动距离
	// 如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
	private float startY;

	// 用于记录正常的布局位置
	private Rect originalRect = new Rect();

	// 手指按下时记录是否可以继续下拉
	private boolean canPullDown = false;

	// 手指按下时记录是否可以继续上拉
	private boolean canPullUp = false;

	// 在手指滑动的过程中记录是否移动了布局
	private boolean isMoved = false;

	// 能否滑动
	private boolean scorllable = true;
	// 正在下拉
	private boolean isPullDowning = false;
	// 正在上拉
	private boolean isPullUping = false;

	// 回弹开关变量
	private boolean isRebound = true;

	public boolean isRebound() {
		return isRebound;
	}

	public void setRebound(boolean isRebound) {
		this.isRebound = isRebound;
	}

	/**
	 * 下拉 监听
	 */
	private OnPullDownListener onPullDownListener;
	/**
	 * 上拉监听
	 */
	private OnPullUpListener onPullUpListener;

	private Scroller mScroller;

	public ReboundScrollView(Context context) {
		super(context);
		mScroller = new Scroller(context);
	}

	public ReboundScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		if (getChildCount() > 0) {
			contentView = getChildAt(0);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (contentView == null)
			return;

		// ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
		originalRect.set(contentView.getLeft(), contentView.getTop(),
				contentView.getRight(), contentView.getBottom());
	}

	/**
	 * 在该方法中获取ScrollView中的唯一子控件的位置信息 这个位置信息在整个控件的生命周期中保持不变
	 */

	/**
	 * 在触摸事件中, 处理上拉和下拉的逻辑
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		if (contentView == null) {
			return super.dispatchTouchEvent(ev);
		}

		if (!scorllable) {
			return super.dispatchTouchEvent(ev);
		}

		int action = ev.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:

			// 判断是否可以上拉和下拉
			canPullDown = isCanPullDown();
			canPullUp = isCanPullUp();

			// 记录按下时的Y值
			startY = ev.getY();
			isPullDowning = false;
			isPullUping = false;
			break;

		case MotionEvent.ACTION_UP:

			if (!isMoved)
				break; // 如果没有移动布局， 则跳过执行

			// 开启动画
			startAnim(contentView.getTop(), 0, ANIM_TIME);
			// 设置回到正常的布局位置
			contentView.layout(originalRect.left, originalRect.top,
					originalRect.right, originalRect.bottom);
			// 将标志位设回false
			if (isPullDowning) {// 下拉
				afterPullDown();
				canPullDown = false;
			} else if (isPullUping) {// 上拉
				afterPullUp();
				canPullUp = false;
			}
			isMoved = false;
			break;
		case MotionEvent.ACTION_MOVE:

			// 在移动的过程中， 既没有滚动到可以上拉的程度， 也没有滚动到可以下拉的程度
			if (!canPullDown && !canPullUp) {
				startY = ev.getY();
				canPullDown = isCanPullDown();
				canPullUp = isCanPullUp();

				break;
			}

			// 计算手指移动的距离
			float nowY = ev.getY();
			int deltaY = (int) (nowY - startY);
			if (canPullUp && deltaY < 0) {// 是否在上拉
				isPullUping = true;
			}

			if (canPullDown && deltaY > 0) {// 是否在下拉
				isPullDowning = true;
			}

			// 是否应该移动布局
			boolean shouldMove = (canPullDown && deltaY > 0) // 可以下拉， 并且手指向下移动
					|| (canPullUp && deltaY < 0) // 可以上拉， 并且手指向上移动
					|| (canPullUp && canPullDown); // 既可以上拉也可以下拉（这种情况出现在ScrollView包裹的控件比ScrollView还小）

			if (shouldMove && isRebound) {

				// 计算偏移量
				int offset = (int) (deltaY * MOVE_FACTOR);

				// 随着手指的移动而移动布局
				contentView.layout(originalRect.left,
						originalRect.top + offset, originalRect.right,
						originalRect.bottom + offset);

				isMoved = true; // 记录移动了布局
			}

			break;
		}

		return super.dispatchTouchEvent(ev);
	}

	private void startAnim(int y, int toY, int time) {
		TranslateAnimation anim = new TranslateAnimation(0, 0, y, toY);
		anim.setDuration(time);

		contentView.startAnimation(anim);
	}

	/**
	 * 判断是否滚动到顶部
	 */
	private boolean isCanPullDown() {
		return (getScrollY() == 0 || contentView.getHeight() < getHeight()
				+ getScrollY());
	}

	/**
	 * 判断是否滚动到底部
	 */
	private boolean isCanPullUp() {
		return (contentView.getHeight() <= getHeight() + getScrollY());
	}

	/**
	 * 防止ScrollView里面嵌套 viewPager 不能移动pager，的焦点冲突
	 */
	private float xDistance, yDistance, xLast, yLast;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {
				return false;
			}
		}

		return super.onInterceptTouchEvent(ev);
	}

	public interface ScrollViewListener {

		public void onScrollChanged(ReboundScrollView scrollView, int x, int y,
									int oldx, int oldy);

	}

	public interface OnPullDownListener {
		public void OnPullDownToDO();

	}

	public interface OnPullUpListener {
		public void OnPullUpToDO();

	}

	public void afterPullDown() {
		if (onPullDownListener != null) {
			this.onPullDownListener.OnPullDownToDO();
		}

	}

	public void afterPullUp() {
		if (onPullUpListener != null) {
			this.onPullUpListener.OnPullUpToDO();
		}

	}

	public void setOnPullDownListener(OnPullDownListener l) {
		this.onPullDownListener = l;
	}

	public void setOnPullUpListener(OnPullUpListener l) {
		this.onPullUpListener = l;
	}

	private ScrollViewListener scrollViewListener = null;

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}

	public boolean isAtBottom() {
		return (contentView.getMeasuredHeight() == (getScrollY() + getHeight()));
	}
	public boolean isAtTop() {
		return (0 == getScrollY());
	}

	public void setScrollable(boolean scorllable) {
		this.scorllable = scorllable;
	}
	
	
	public int getBottomHeight() {

		return contentView.getMeasuredHeight()-getHeight();
	}

}
