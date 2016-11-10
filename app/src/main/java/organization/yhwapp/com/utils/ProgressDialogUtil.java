package organization.yhwapp.com.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import organization.yhwapp.com.R;

public class ProgressDialogUtil {

	private static ProgressDialogUtil instance;

	private ProgressDialogUtil() {
	}

	public static ProgressDialogUtil getInstance() {

		if (instance == null) {
			instance = new ProgressDialogUtil();
		}
		return instance;
	}

	private ProgressDialog dialog;
	Context context;

	public void showtPD(Context context) {
		// 不同Activity调用 该方法的时候 要判断 context
		if (dialog == null || context != this.context) {

			while (((Activity) context).getParent() != null) {
				context = ((Activity) context).getParent();
			}
			
			dialog = new ProgressDialog(context, R.style.new_circle_progress);
			dialog.setCancelable(true);
			dialog.show();
			dialog.setContentView(R.layout.loading_process_dialog_anim);
			
			this.context = context;
		}
		if (!dialog.isShowing()) {
			dialog.show();
		}

	}

	public void dismiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	
	public ProgressDialog getDialog() {
		return dialog;
	}

}
