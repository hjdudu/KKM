package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.UserBean;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context mContext;

    private List<UserBean> list;

    public GridAdapter(Context mContext, List<UserBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_status_item, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = convertView.findViewById(R.id.order_status_icon);
            viewHolder.name = convertView.findViewById(R.id.order_status_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setImageResource(list.get(position).getUserIcon());
        viewHolder.name.setText(list.get(position).getUserName());
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
    }
}