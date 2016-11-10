package organization.yhwapp.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

import organization.yhwapp.com.R;

/**
 * 系统消息
 */
public class Fragment_msg_1 extends Fragment {

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_msg_1, null);
            ViewUtils.inject(this, v);
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

}
