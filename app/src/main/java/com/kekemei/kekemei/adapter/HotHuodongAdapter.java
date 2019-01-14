package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.bean.DataBean;

import java.util.List;

/**
 * Created by peiyangfan on 2018/10/11.
 */

public class HotHuodongAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    private Context context;

    public HotHuodongAdapter(Context context) {
//        super(layoutResId, data);
        super(null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {

    }
}
