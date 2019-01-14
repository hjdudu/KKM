package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.OrderListBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.URLs;


public class OrderListAdapter extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {
    private Context jContext;
    private String showOrHide;

    public OrderListAdapter(Context context) {
        super(R.layout.work_refund_list_item, null);
        jContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean item) {
        helper.addOnClickListener(R.id.quxiaodingdan);
        helper.addOnClickListener(R.id.chakan);
        helper.addOnClickListener(R.id.querenfuwu);
        helper.addOnClickListener(R.id.lijifukuan);
        helper.addOnClickListener(R.id.zaicigoumai);
        helper.addOnClickListener(R.id.qupingjia);
        helper.addOnClickListener(R.id.cuiwuliu);
        helper.addOnClickListener(R.id.iv_del_order);
        helper.addOnClickListener(R.id.yuyue);
        helper.setText(R.id.order_status_tv, item.getState_text());
        helper.setText(R.id.order_id_tv, item.getId() + " " + AppUtil.getFormatTime1(item.getCreatetime()));
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.goods_img));
        helper.setText(R.id.goods_title, item.getName());
        helper.setText(R.id.goods_price, "¥" +item.getPrice() + "");
        helper.setText(R.id.goods_price_total, "¥" + (item.getPrice() * item.getCount()));
        //个数
        helper.setText(R.id.goods_count, item.getCount()+"");


        setShowOrHide(item.getState(), helper);

    }


    public void setShowOrHide(String showOrHide, BaseViewHolder helper) {
        //状态值:0=待支付,1=待预约,2=待服务,3=服务中,4=服务完成,5=待评价,6=完成,10=已取消
        switch (showOrHide) {
            case "0":  //待支付
                helper.setGone(R.id.quxiaodingdan, false);
                helper.setGone(R.id.chakan, false);
                helper.setGone(R.id.querenfuwu, false);
                helper.setGone(R.id.lijifukuan, true);
                helper.setGone(R.id.zaicigoumai, false);
                helper.setGone(R.id.qupingjia, false);
                helper.setGone(R.id.cuiwuliu, false);
                helper.setGone(R.id.yuyue, false);
                break;
            case "1": //待预约
                helper.setGone(R.id.quxiaodingdan, false);
                helper.setGone(R.id.chakan, false);
                helper.setGone(R.id.querenfuwu, false);
                helper.setGone(R.id.lijifukuan, false);
                helper.setGone(R.id.zaicigoumai, false);
                helper.setGone(R.id.qupingjia, false);
                helper.setGone(R.id.cuiwuliu, false);
                helper.setGone(R.id.yuyue, true);
                break;
            case "2": //待服务
            case "3": //服务中
            case "6"://完成
            case "10"://已取消
                helper.setGone(R.id.quxiaodingdan, false);
                helper.setGone(R.id.chakan, true);
                helper.setGone(R.id.querenfuwu, false);
                helper.setGone(R.id.lijifukuan, false);
                helper.setGone(R.id.zaicigoumai, false);
                helper.setGone(R.id.qupingjia, false);
                helper.setGone(R.id.cuiwuliu, false);
                helper.setGone(R.id.yuyue, false);
                break;
            case "4": //服务完成
            case "5"://待评价
                helper.setGone(R.id.quxiaodingdan, true);
                helper.setGone(R.id.chakan, false);
                helper.setGone(R.id.querenfuwu, false);
                helper.setGone(R.id.lijifukuan, false);
                helper.setGone(R.id.zaicigoumai, true);
                helper.setGone(R.id.qupingjia, true);
                helper.setGone(R.id.cuiwuliu, false);
                helper.setGone(R.id.yuyue, false);
                break;
        }
    }

}


