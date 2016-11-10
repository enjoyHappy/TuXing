package organization.yhwapp.com.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;

import organization.yhwapp.com.R;
import organization.yhwapp.com.bean.ItemFriends;
import organization.yhwapp.com.utils.PingYinUtil;

/**
 * Created by Administrator on 2016/11/3.
 */
public class FriendsAdapter extends BaseAdapter implements SectionIndexer {

    public HashMap<String, Integer> alphaIndexer = new HashMap<String, Integer>();

    private ArrayList<ItemFriends> list;

    public FriendsAdapter(ArrayList<ItemFriends> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = PingYinUtil.converterToFirstSpell(list.get(i).name);
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ?  PingYinUtil.converterToFirstSpell(list.get(i - 1).name) : " ";
            if (!previewStr.equals(currentStr)) {
                alphaIndexer.put(currentStr, i);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends, null);
            ViewUtils.inject(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String nextLetter = PingYinUtil.converterToFirstSpell(list.get(position).name);
        if (position > 0) {
            String preLetter = PingYinUtil.converterToFirstSpell(list.get(position - 1).name);

            if (TextUtils.equals(preLetter, nextLetter)) {
                holder.header.setVisibility(View.GONE);
            } else {
                holder.header.setVisibility(View.VISIBLE);
                holder.header.setText(nextLetter);
            }
        }else{
            holder.header.setVisibility(View.VISIBLE);
            holder.header.setText(nextLetter);
        }
        holder.name.setText(list.get(position).name);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.item_friends_img)
        ImageView img;
        @ViewInject(R.id.item_friends_name)
        TextView name;// 昵称
        @ViewInject(R.id.item_friends_des)
        TextView des;// 简介

        @ViewInject(R.id.item_friends_header)
        TextView header;// 顶部字母
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    /**
     * 得到首字母的ascii值
     */
    public int getSectionForPosition(int position) {
        String str = PingYinUtil.converterToFirstSpell(list.get(position).name);
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.charAt(0);
    }

    public int getPositionForSection(int section) {
        if (list.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < getCount(); i++) {
            String sortStr = PingYinUtil.converterToFirstSpell(list.get(i).name);
            try {
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
