package com.kekemei.kekemei.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.ShopActivity;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.YuYueDataBean;
import com.kekemei.kekemei.utils.URLs;
import com.jcloud.image_loader_module.ImageLoaderUtil;

import java.util.List;

public class YuYueDataListAdapter extends BaseQuickAdapter<YuYueDataBean.DataBean, BaseViewHolder> {


    public Context mContext;
    private View ll_select_date_time;

    public YuYueDataListAdapter(Context mContext) {
        super(R.layout.layout_date_list);
        this.mContext = mContext;
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, YuYueDataBean.DataBean item) {
        helper.setText(R.id.tv_date_and_week, item.getName());
        helper.setTextColor(R.id.tv_can_yuyue, 0XFF999999);
        helper.setTextColor(R.id.tv_date_and_week, 0XFF999999);

        ll_select_date_time = helper.getView(R.id.ll_select_data_time);
        if (item.getState() == 0) {
            helper.setText(R.id.tv_can_yuyue, "可预约");
            ll_select_date_time.setBackground(mContext.getDrawable(R.drawable.btn_white_background));
            ll_select_date_time.setElevation(4);
            ll_select_date_time.setEnabled(true);
        } else {
            helper.setText(R.id.tv_can_yuyue, "不可约");
            ll_select_date_time.setBackground(mContext.getDrawable(R.drawable.btn_c1c1c1_background));
            ll_select_date_time.setElevation(4);
            helper.setTextColor(R.id.tv_can_yuyue, 0XFFFFFFFF);
            helper.setTextColor(R.id.tv_date_and_week, 0XFFFFFFFF);
            ll_select_date_time.setEnabled(false);
        }
        if (item.isSelect()) {
            helper.setTextColor(R.id.tv_can_yuyue, 0XFFFFFFFF);
            helper.setTextColor(R.id.tv_date_and_week, 0XFFFFFFFF);
            ll_select_date_time.setBackground(mContext.getDrawable(R.drawable.btn_7ad2d2_background));
        }
        helper.addOnClickListener(R.id.ll_select_data_time);

    }
}
