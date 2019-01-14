package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.URLs;

public class ProjectListAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    private Context jContext;

    public ProjectListAdapter(Context context) {
        super(R.layout.item_project_list_layout, null);
        jContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_order_name, item.getName());
        helper.setText(R.id.tv_price, "¥ " + item.getPrice_discount());
        TextView oldPrice = helper.getView(R.id.tv_old_price);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        oldPrice.getPaint().setAntiAlias(true);// 抗锯齿
        helper.setText(R.id.tv_old_price, "¥ " + item.getPrice_market());
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.iv_order_icon));
        if (CollectionUtils.isEmpty(item.getFull()) && CollectionUtils.isEmpty(item.getRedenvelopes())) {
            helper.setVisible(R.id.ll_youhuiquan, false);
        }else{
            helper.setVisible(R.id.ll_youhuiquan, true);
        }
        if (CollectionUtils.isEmpty(item.getFull())) {
            helper.setVisible(R.id.tv_man, false);
            helper.setVisible(R.id.iv_man, false);
        } else {
            helper.setVisible(R.id.iv_man, true);
            helper.setVisible(R.id.tv_man, true);
            helper.setText(R.id.tv_man, item.getFull().get(0));
        }
        if (CollectionUtils.isEmpty(item.getRedenvelopes())) {
            helper.setVisible(R.id.tv_jian, false);
            helper.setVisible(R.id.iv_jian, false);
        } else {
            helper.setVisible(R.id.iv_jian, true);
            helper.setVisible(R.id.tv_jian, true);
            helper.setText(R.id.tv_jian, item.getRedenvelopes().get(0));
        }
    }
}


