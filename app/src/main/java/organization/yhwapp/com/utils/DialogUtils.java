package organization.yhwapp.com.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import organization.yhwapp.com.R;

public class DialogUtils {

	private String title;
	private String content;
	private String right;
	private String left;

	private DialogUtils(){}

	private DialogUtils instance;

	/**
	 * 返回一个有确定按钮，取消按钮、带标题的对话框
	 */
	private Dialog createSexDialog(Context context, OnDialogButtonClickListener listener) {
		
		Dialog dialog = new Dialog(context, R.style.CustomDialog);
//		View view = View.inflate(context, R.layout.dialog_confirm_with_title, null);
//		LinearLayout dialogView = (LinearLayout) view.findViewById(R.id.lay_dialog);
//		LayoutParams lp = (LayoutParams) dialogView.getLayoutParams();
//		lp.width = (int) (BaseActivity.SCREEN_W * 0.8);
//		dialogView.setLayoutParams(lp);
//
//		TextView confirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);
//		TextView cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
//
//		TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
//		TextView tvContent = (TextView) view.findViewById(R.id.tv_dialog_content);
//		tvTitle.setText(title);
//		tvContent.setText(content);
//		confirm.setText(right);
//		cancel.setText(left);
//
//
//		OnButtonClickListener buttonListener = new OnButtonClickListener(dialog, listener);
//		confirm.setOnClickListener(buttonListener);
//		cancel.setOnClickListener(buttonListener);
//		dialog.setContentView(view);
		return dialog;
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public DialogUtils getInstance() {
		return instance;
	}

	public interface OnDialogButtonClickListener {
		public void onConfirm();
		public void onCancel();
	}
	}

class OnButtonClickListener implements OnClickListener {

	private Dialog mDialog;
	private DialogUtils.OnDialogButtonClickListener mListener;

	public OnButtonClickListener(Dialog dialog, DialogUtils.OnDialogButtonClickListener listener) {
		mDialog = dialog;
		mListener = listener;
	}

	@Override
	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tv_dialog_confirm:
//			if (mListener != null) {
//				mListener.onConfirm();
//			}
//			mDialog.dismiss();
//			break;
//
//		case R.id.tv_dialog_cancel:
//			if (mListener != null) {
//				mListener.onCancel();
//			}
//			mDialog.dismiss();
//			break;
//		}
	}
}