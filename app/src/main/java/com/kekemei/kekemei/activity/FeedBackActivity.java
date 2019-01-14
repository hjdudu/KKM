package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 用户反馈
 */
public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.txtContactService)
    TextView txtContactService;

    public static void start(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("用户反馈");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtContactService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editContent.getText().toString();
                if (StringUtils.isNotBlank(content)) {
                    submitFeedBackContent(content);
                } else {
                    ToastUtil.showToastMsg(FeedBackActivity.this, "请输入内容");
                }
            }
        });
    }

    private void submitFeedBackContent(String content) {
        long userId = UserHelp.getUserId(this);
        if (userId==-1L){
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.ADD_COMPLAINT).params("user_id", userId)
                .params("content", content).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(FeedBackActivity.this, "提交失败");
                        return;
                    }
                    ToastUtil.showToastMsg(FeedBackActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
