package com.kekemei.kekemei.adapter.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

abstract public class BaseListViewAdapter extends BaseAdapter {

    protected Context mContext = null;
    public List<Object> infos = null;

    public BaseListViewAdapter(Context context) {
        mContext = context;
        infos = new ArrayList<>();
    }

    public BaseListViewAdapter reset() {
        return reset(true);
    }

    public BaseListViewAdapter reset(boolean notify) {
        if (infos != null) {
            infos.clear();
            if (notify) {
                notifyDataSetChanged();
            }
        }
        return this;
    }

    public void addListItems(List<?> items) {
        if (infos != null) {
            infos.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<?> infos) {
        if (infos != null && infos.size() > 0) {
            this.infos.addAll(infos);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<?> infos, boolean notify) {
        if (infos != null && infos.size() > 0) {
            this.infos.addAll(infos);
            if (notify) {
                notifyDataSetChanged();
            }
        }
    }

    public void addItems(Object[] infos) {
        addItems(infos, true);
    }

    public void addItems(Object[] infos, boolean notify) {
        if (infos != null) {
            Collections.addAll(this.infos, infos);
            if (infos.length > 0 && notify) {
                notifyDataSetChanged();
            }
        }
    }

    public void addItem(Object info) {
        if (info != null) {
            this.infos.add(0, info);
        }
        if (this.infos.size() > 0) {
            notifyDataSetChanged();
        }
    }

    public List<Object> getItems() {
        return infos;
    }

    @Override
    public int getCount() {
        return infos == null ? 0 : infos.size();
    }

    @Override
    public Object getItem(int position) {
        if ((position < getCount()) && (position >= 0)) {
            return infos.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
