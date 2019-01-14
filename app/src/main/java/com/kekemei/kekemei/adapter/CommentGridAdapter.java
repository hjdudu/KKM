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
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.URLs;
import com.jcloud.image_loader_module.ImageLoaderUtil;

import java.util.List;


public class CommentGridAdapter extends BaseAdapter {
    private Context mContext;

    private List<String> list;

    public CommentGridAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
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

    /*public void replaceData(List<String> newList) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.clear();
            list.addAll(newList);
            notifyDataSetChanged();
        }
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_image_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = convertView.findViewById(R.id.commentImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + list.get(position), viewHolder.icon);
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
    }
}