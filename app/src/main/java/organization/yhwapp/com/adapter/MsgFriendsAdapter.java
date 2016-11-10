package organization.yhwapp.com.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import organization.yhwapp.com.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MsgFriendsAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_friends, null);
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.item_msg_friends_img)
        ImageView img;
        @ViewInject(R.id.item_msg_friends_name)
        TextView name;// 昵称
        @ViewInject(R.id.item_msg_friends_des)
        TextView des;// 简介
    }
}
