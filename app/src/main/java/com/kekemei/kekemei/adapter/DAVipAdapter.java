package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.ShopBean;
import com.kekemei.kekemei.utils.URLs;

public class DAVipAdapter extends BaseQuickAdapter<ShopBean, BaseViewHolder> {
    private Context context;

    public DAVipAdapter(Context context) {
        super(R.layout.da_vip_layout, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.starPoint, item.getStarts() + "  " + item.getTag());
        helper.setText(R.id.commentNum, item.getComment_count() + "条评论");
        helper.setText(R.id.distance, "距您" + item.getDistance() + "公里");
        helper.setText(R.id.price, "￥" + item.getMinimum() + "起");
        ImageLoaderUtil.getInstance().loadImageTopRound(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.iv_shop_pic), 30);
    }

}

