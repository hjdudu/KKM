package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.BaseActivity;
import com.kekemei.kekemei.activity.LoginActivity;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by peiyangfan on 2018/10/12.
 */

public class MeiRongShiAdapter extends BaseQuickAdapter<BeauticianBean, BaseViewHolder> {
    private Context context;

    public MeiRongShiAdapter(Context context) {
        super(R.layout.beautician_layout, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final BeauticianBean item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.num, item.getContent());
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + item.getImage(), (ImageView) helper.getView(R.id.icon_pic));

//        helper.addOnClickListener(R.id.btn_guanzhu);


        final Button guanzhu = helper.getView(R.id.btn_guanzhu);
        guanzhu.setText(item.getIsfriend() == 0 ? "关注" : "已关注");
        guanzhu.setBackgroundColor(item.getIsfriend() == 0 ? Color.GRAY : Color.RED);
        if (item.getIsfriend() == 0)
            guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    long userId = UserHelp.getUserId(mContext);
                    if (userId == -1L) {
                        LoginActivity.start((BaseActivity) mContext);
                        return;
                    }
                    OkGo.<String>post(URLs.FOLLOW_BEAUTICIAN)
                            .params("beautician_id", item.getId())
                            .params("user_id", userId)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    LogUtil.e("Me", "follow beautician:" + response.body());
                                    Gson gson = new Gson();
                                    guanzhu.setText("已关注");
                                    guanzhu.setBackgroundColor(Color.RED);
                                }
                            });
                }
            });
    }

}



