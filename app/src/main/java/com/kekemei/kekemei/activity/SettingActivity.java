package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.AbousListBean;
import com.kekemei.kekemei.bean.HelpBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.SectionRowView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.txtSafely)
    SectionRowView safelySetting;
    //    @BindView(R.id.txtFeedBack)
    //    SectionRowView feedBack;
    @BindView(R.id.txtHelpCenter)
    SectionRowView helpCenter;
    @BindView(R.id.txtAboutUs)
    SectionRowView aboutUs;
    @BindView(R.id.txtCleanMemory)
    SectionRowView cleanMemory;
    @BindView(R.id.txtVersion)
    SectionRowView version;

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("设置");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        version.setContentTxt("V" + AppUtil.getVersionName(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.txtSafely, R.id.txtHelpCenter, R.id.txtAboutUs,
            R.id.txtCleanMemory, R.id.txtVersion, R.id.txt_quit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSafely:
                break;
            case R.id.txtHelpCenter:
                HelpCenterActivity.start(SettingActivity.this);
                break;
            case R.id.txtAboutUs:
                OkGo.<String>get(URLs.ABOUT_LIST).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AbousListBean abousListBean = gson.fromJson(response.body(), AbousListBean.class);

                    }
                });
                break;
            case R.id.txtCleanMemory:
                break;
            case R.id.txt_quit_login:
                chatOut();
                loginOut();
                MainActivity.start(SettingActivity.this, 0);
                break;
        }


    }

    private void loginOut() {
        UserHelp.setMobile(SettingActivity.this, "");
        UserHelp.setUserName(SettingActivity.this, "");
        UserHelp.setNickName(SettingActivity.this, "");
        UserHelp.setToken(SettingActivity.this, "");
        UserHelp.setAvatar(SettingActivity.this, "");
        UserHelp.setUserId(SettingActivity.this, -1L);
        UserHelp.setIsNew(SettingActivity.this, -1);
    }

    private void chatOut() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(true, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub

                    }
                });
            }

        }).start();
    }
}
