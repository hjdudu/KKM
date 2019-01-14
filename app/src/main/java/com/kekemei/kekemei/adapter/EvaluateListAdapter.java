package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.LargeImageActivity;
import com.kekemei.kekemei.bean.EvaluateBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.view.NoScrollGridView;
import com.kekemei.kekemei.view.StarBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EvaluateListAdapter extends BaseQuickAdapter<EvaluateBean, BaseViewHolder> {
    private Context jContext;
    private boolean isMyComment;

    public EvaluateListAdapter(Context context, boolean isMyComment) {
        super(isMyComment ? R.layout.item_my_evaluate_list_layout : R.layout.item_evaluate_list_layout, null);
        this.jContext = context;
        this.isMyComment = isMyComment;
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluateBean item) {
        if (StringUtils.isNotEmpty(item.getImages())) {
            String[] split = item.getImages().split(",");
            final List<String> urlList = Arrays.asList(split);
            if (CollectionUtils.isEmpty(urlList)) {
                urlList.add(item.getImages());
            }
            NoScrollGridView noScrollGridView = helper.getView(R.id.ncgv);
            CommentGridAdapter gridAdapter = new CommentGridAdapter(jContext, urlList);
            noScrollGridView.setAdapter(gridAdapter);
            noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (CollectionUtils.isNotEmpty(urlList)) {
                        ArrayList<String> imageList = new ArrayList<>();
                        for (Object imgUrl : urlList) {
                            imageList.add((String) imgUrl);
                        }
                        LargeImageActivity.start(jContext, imageList, i);
                    }
                }
            });
        }
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getAvatar(), (ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getNickname());
        helper.setText(R.id.subTitle, item.getSatisfaction_text());
        helper.setText(R.id.commentContent, item.getContent());
        helper.setText(R.id.date, AppUtil.getFormatTime2(item.getCreatetime()));
        StarBar startBar = helper.getView(R.id.star_bar);
        startBar.setStarMark(Float.parseFloat(item.getStart()));
        LinearLayout container = helper.getView(R.id.replyContent);
        if (CollectionUtils.isNotEmpty(item.getReply())) {
            container.setVisibility(View.VISIBLE);
            addReplyContent(container, item.getReply());
        } else {
            container.setVisibility(View.INVISIBLE);
        }
    }

    private void addReplyContent(LinearLayout container, List<EvaluateBean.ReplyBean> contentList) {
        container.removeAllViews();
        for (EvaluateBean.ReplyBean replyBean : contentList) {
            TextView tvContent = new TextView(jContext);
            tvContent.setTextSize(14);
            tvContent.setTextColor(ContextCompat.getColor(jContext, R.color.common_text_dark));
            tvContent.setText(replyBean.getContent());
            container.addView(tvContent);
        }
    }
}


