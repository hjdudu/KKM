package com.kekemei.kekemei.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.HongBaoDataBean;

/**
 * Created by peiyangfan on 2018/11/5.
 */

public class HongBaoDataAdapter extends BaseQuickAdapter<HongBaoDataBean.DataBean, BaseViewHolder> {
    public HongBaoDataAdapter() {
        super(R.layout.layout_hongbao);
    }

    @Override
    protected void convert(BaseViewHolder helper, HongBaoDataBean.DataBean item) {
        helper.setBackgroundRes(R.id.ll_voucher_bg, R.mipmap.orderform_youhuijuan_hongbao);
        helper.setText(R.id.tv_price, item.getPrice_reduction() + "");
//        helper.setText(R.id.data, strSub(item.getRedenvelopes().getActivitytime()) + " — " + strSub(item.getRedenvelopes().getActivitytime_end()));

        helper.addOnClickListener(R.id.btn_now_use);
    }


    private String strSub(String string) {
        return string.substring(0, 11);
    }
}
