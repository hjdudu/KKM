package com.kekemei.kekemei.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.ShopBean;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.view.StarBar;

public class ShopListAdapter extends BaseQuickAdapter<ShopBean, BaseViewHolder> {
    private Context mContext;

    public ShopListAdapter(Context mContext, int layoutResId) {
        super(layoutResId, null);
        this.mContext = mContext;
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, ShopBean item) {
        helper.setText(R.id.tv_name, item.getName());
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.iv_shop_pic));
        helper.setText(R.id.tv_juli,
                "距离" +
                       item.getDistance() + "KM");

        StarBar star_bar = helper.getView(R.id.star_bar);
        star_bar.setStarMark(item.getStart());
        helper.setText(R.id.tv_pinglun_num,item.getComment_count()+"");
        helper.setText(R.id.tv_fuwu_num,item.getOrder_count()+"");
        helper.setText(R.id.tv_num,item.getAppointment()+"人预约");
        helper.setText(R.id.tv_manyidu, "满意度：" + (item.getSatisfaction() * 100) + "%");
        helper.addOnClickListener(R.id.btn_buy_now);
    }
}
