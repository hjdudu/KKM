package com.kekemei.kekemei.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.MsgBean;
import com.kekemei.kekemei.utils.AppUtil;

/**
 * Created by peiyangfan on 2018/10/12.
 */

public class MsgListAdapter extends BaseQuickAdapter<MsgBean.DataBean, BaseViewHolder> {
    private Context context;

    public MsgListAdapter(Context context) {
        super(R.layout.msg_layout, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean.DataBean item) {
//        helper.setText(R.id.name, item.getName());
//        helper.setText(R.id.num, item.getContent());
//        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.icon_pic));
        switch (item.getType()) {
            case "0":
                helper.setImageResource(R.id.iv_msg_icon,R.mipmap.user_xiaoxi_gongao_ic);
                break;
            case "1":
                helper.setImageResource(R.id.iv_msg_icon,R.mipmap.user_xiaoxi_xiaoxi_ic);
                break;
            case "2":
                helper.setImageResource(R.id.iv_msg_icon,R.mipmap.user_xiaoxi_tishi_ic);
                break;
            case "3":
                helper.setImageResource(R.id.iv_msg_icon,R.mipmap.user_xiaoxi_xiaoxi_ic);
                break;
        }

        if (!item.getState_text().equals("未读"))
            helper.setBackgroundColor(R.id.ll_msg,0x999999);


        helper.setText(R.id.tv_msg_icon,item.getName());
        helper.setText(R.id.tv_time,AppUtil.getFormatTime1(item.getCreatetime()));
    }

}



