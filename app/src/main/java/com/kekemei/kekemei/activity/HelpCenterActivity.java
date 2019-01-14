package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.MyInfoBean;
import com.kekemei.kekemei.bean.WaiterBean;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 帮助中心页面
 */
public class HelpCenterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.txtSectionOne)
    TextView txtSectionOne;
    @BindView(R.id.txtSectionTwo)
    TextView txtSectionTwo;
    @BindView(R.id.txtSectionThree)
    TextView txtSectionThree;
    @BindView(R.id.txtContactService)
    TextView txtContactService;
    private WaiterBean admin;

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HelpCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("帮助中心");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();


        getData();
    }

    private void getData() {
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>get(URLs.MY_INFO).params("user_id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MyInfoBean myInfoBean = gson.fromJson(response.body(), MyInfoBean.class);
                        admin = myInfoBean.getData().getAdmin();
                    }
                });

    }

    @OnClick(R.id.txtContactService)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtContactService:
                ChatActivity.start(this, admin);
                break;
        }
    }
}
