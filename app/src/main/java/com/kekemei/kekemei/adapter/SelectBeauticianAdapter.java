package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.EvaluateBean;
import com.kekemei.kekemei.utils.URLs;

import java.util.List;


public class SelectBeauticianAdapter extends BaseQuickAdapter<BeauticianBean, BaseViewHolder> {
    private Context jContext;

    public SelectBeauticianAdapter(Context context) {
        super(R.layout.item_select_beautician_layout, null);
        this.jContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BeauticianBean item) {
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.ivImage));
        helper.setText(R.id.tvName, item.getName());
        /*helper.setText(R.id.subTitle, item.getSatisfaction_text());
        helper.setText(R.id.commentContent, item.getContent());
        helper.setText(R.id.date, AppUtil.getFormatTime2(item.getCreatetime()));
        StarBar startBar = helper.getView(R.id.star_bar);
        startBar.setStarMark(Float.parseFloat(item.getStart()));
        LinearLayout container = helper.getView(R.id.llCommentTag);
        if (CollectionUtils.isNotEmpty(item.getReply())) {
            container.setVisibility(View.VISIBLE);
            addReplyContent(container, item.getReply());
        } else {
            container.setVisibility(View.INVISIBLE);
        }*/
    }

    private void addReplyContent(LinearLayout container, List<EvaluateBean.ReplyBean> contentList) {
        container.removeAllViews();
        for (EvaluateBean.ReplyBean replyBean : contentList) {
            TextView tvContent = new TextView(jContext);
            tvContent.setTextSize(40);
            tvContent.setTextColor(ContextCompat.getColor(jContext, R.color.common_text_dark));
            tvContent.setText(replyBean.getContent());
            container.addView(tvContent);
        }
    }
}


