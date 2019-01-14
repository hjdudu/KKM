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

public class MyGridAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {
    public static final int PERSON_TUI_JIAN = 5;
    private Context context;
    private int type;

    public static final int NewmemberdataBean = 0;
    public static final int HotdataBean = 1;
    public static final int MemberdataBean = 2;
    public static final int SpecialdataBean = 3;
    public static final int ORDER_HOT_BEAN = 4;

    public MyGridAdapter(Context context, int type) {
        super(R.layout.active_layout);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.now_price, "￥" + item.getPrice_discount());
        TextView beforePrice = helper.getView(R.id.before_price);
        beforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        beforePrice.getPaint().setAntiAlias(true);// 抗锯齿
        helper.setText(R.id.before_price, "￥" + item.getPrice_market());
        helper.setText(R.id.personNum, String.valueOf(item.getLike_count()));
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.iv_beautician_photo));
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
