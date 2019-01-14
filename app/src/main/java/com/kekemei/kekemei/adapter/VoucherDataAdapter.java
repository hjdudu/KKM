package com.kekemei.kekemei.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.CouponDataBean;

import java.util.List;

/**
 * Created by peiyangfan on 2018/11/5.
 */

public class VoucherDataAdapter extends BaseQuickAdapter<CouponDataBean.DataBean, BaseViewHolder> {
    public VoucherDataAdapter() {
        super(R.layout.layout_voucher);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponDataBean.DataBean item) {
        helper.setBackgroundRes(R.id.ll_voucher_bg, R.mipmap.orderform_youhuijuan_youhuijuan_);
        helper.setText(R.id.tv_price, item.getCoupon().getPrice_reduction() + "");
        helper.setText(R.id.data, strSub(item.getCoupon().getActivitytime()) + " — " + strSub(item.getCoupon().getActivitytime_end()));

        helper.addOnClickListener(R.id.btn_now_use);
    }


    private String strSub(String string) {
        return string.substring(0, 11);
    }
}
