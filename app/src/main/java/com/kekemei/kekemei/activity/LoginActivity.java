package com.kekemei.kekemei.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.LoginBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created 登录 by peiyangfan on 2018/10/15.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.top_bg)
    View topBg;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.btn_yanzhengma)
    Button btnYanzhengma;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.weixin_login)
    ImageView weixinLogin;
    @BindView(R.id.qq_login)
    ImageView qqLogin;
    @BindView(R.id.weibo_login)
    ImageView weiboLogin;

    private int thirdType = -1;
    private String openId = "";
    private String eventType = "";
    private Context baseContext;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        baseContext = getBaseContext();

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(LoginActivity.this).setShareConfig(config);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tvTitle.setText("登录");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yanzhengma:
                sendYanZhengMa();
                break;
            case R.id.btn_login:
                // 跳转到聊天界面，开始聊天
                login(etPhoneNum.getText().toString().trim(), eventType, etYanzhengma.getText().toString().trim());
                break;
            case R.id.weibo_login:
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
                break;
            case R.id.weixin_login:
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.qq_login:
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }

    private void sendYanZhengMa() {
        if (thirdType != -1) {
            eventType = "login";
        } else {
            eventType = "login";
        }
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (phoneNum.isEmpty()) {
            ToastUtil.showToastMsg(LoginActivity.this, "请输入手机号");
            return;
        }
        AppUtil.sendYanZhengMa(phoneNum, eventType, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body());
                    String msg = jsonObject.optString("msg");
                    if (msg.equals("发送失败")) {
                        ToastUtil.showToastMsg(getApplicationContext(),msg);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                restart();
            }
        });
    }

    private void login(final String moblie, String eType, final String captcha) {
        if (moblie.isEmpty()) {
            ToastUtil.showToastMsg(LoginActivity.this, "请输入手机号");
            return;
        }
        if (captcha.isEmpty()) {
            ToastUtil.showToastMsg(LoginActivity.this, "请输入验证码");
            return;
        }
        if (thirdType != -1) {
            eventType = "login";
            eType = eventType;
            AppUtil.checkCaptcha(moblie,
                    eType,
                    captcha,
                    new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            verifyBind(thirdType, openId, moblie, captcha);
                        }
                    });
        } else {
            eventType = "login";
            eType = eventType;

            OkGo.<String>get(URLs.MOBILE_LOGIN)
                    .params("mobile", moblie)
                    .params("event", eType)
                    .params("captcha", captcha)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            LogUtil.d(LoginActivity.this.getLocalClassName(), response.body());
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.body());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String msg = jsonObject.optString("msg");
                            if (msg.equals("Captcha is incorrect")) {
                                ToastUtil.showToastMsg(getApplicationContext(),"验证码错误");
                            } else {
                                saveUserInfo(response);
                            }

                        }
                    });

        }
    }


    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            LogUtil.d("LoginActivity", platform + "开始授权成功");
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data == null && action == 1) {
                LogUtil.d("LoginActivity", platform + "取消授权成功");
                return;
            }
            if (data != null) {
                String temp = "";
                for (String key : data.keySet()) {
                    temp = temp + key + " : " + data.get(key) + "\n";
                }
                LogUtil.d("LoginActivity", temp);

                if (platform == SHARE_MEDIA.WEIXIN) {
                    thirdType = 1;
                    openId = data.get("openid");
                } else if (platform == SHARE_MEDIA.QQ) {
                    thirdType = 2;
                    openId = data.get("openid");
                } else if (platform == SHARE_MEDIA.SINA) {
                    thirdType = 3;
                    openId = data.get("uid");
                }


                thirdLogin(openId, thirdType);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            LogUtil.e("LoginActivity", t.getMessage());
            if (platform == SHARE_MEDIA.WEIXIN) {
                if (!UMShareAPI.get(LoginActivity.this).isInstall(LoginActivity.this, SHARE_MEDIA.WEIXIN)) {
                    ToastUtil.showToastMsg(LoginActivity.this, "请安装微信客户端");
                    return;
                }
            } else if (platform == SHARE_MEDIA.QQ) {
                if (!UMShareAPI.get(LoginActivity.this).isInstall(LoginActivity.this, SHARE_MEDIA.QQ)) {
                    ToastUtil.showToastMsg(LoginActivity.this, "请安装腾讯QQ客户端");
                    return;
                }
            } else if (platform == SHARE_MEDIA.SINA) {
                if (!UMShareAPI.get(LoginActivity.this).isInstall(LoginActivity.this, SHARE_MEDIA.QQ)) {
                    ToastUtil.showToastMsg(LoginActivity.this, "请安装微博客户端");
                    return;
                }
            }
            ToastUtil.showToastMsg(LoginActivity.this, "错误" + t.getMessage());

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showToastMsg(LoginActivity.this, "授权取消");
        }
    };

    private void thirdLogin(String openId, int thirdType) {
        OkGo.<String>get(URLs.APP_THIRD).params("type", thirdType)
                .params("openid", openId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.d(LoginActivity.this.getLocalClassName(), response.body());
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String msg = jsonObject.optString("msg");
                        if (msg.equals("您还没有绑定手机号,请绑定手机号")) {
                            tvTitle.setText("绑定手机");
                        } else {
                            saveUserInfo(response);
                        }
                        ToastUtil.showToastMsg(LoginActivity.this, "三方登录成功");
                    }
                });
    }

    private void saveUserInfo(Response<String> response) {
        LogUtil.d("LoginActivity", response.body());
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(response.body(), LoginBean.class);
        UserHelp.setLogin(getBaseContext(), true);
        LoginBean.DataBean.UserinfoBean userinfo = loginBean.getData().getUserinfo();
        UserHelp.setMobile(baseContext, userinfo.getMobile());
        UserHelp.setUserName(baseContext, userinfo.getUsername());
        UserHelp.setNickName(baseContext, userinfo.getNickname());
        UserHelp.setToken(baseContext, userinfo.getToken());
        UserHelp.setAvatar(baseContext, userinfo.getAvatar());
        UserHelp.setUserId(baseContext, userinfo.getUser_id());
        UserHelp.setIsNew(baseContext, userinfo.getIsnew());

        createAccount(userinfo.getUser_id());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void verifyBind(int type, final String openId, String mobile, String captcha) {
        OkGo.<String>get(URLs.USER_BINDING).params("type", type)
                .params("openid", openId)
                .params("mobile", mobile)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String msg = jsonObject.optString("msg");
                        if (msg.equals("已被注册,请换个手机号")) {
                            ToastUtil.showToastMsg(getBaseContext(), msg.toString());
                            return;
                        }
                        thirdLogin(openId, thirdType);
                        ToastUtil.showToastMsg(LoginActivity.this, "绑定用户成功");


                    }
                });
    }

    public void chatLogin(final String nickName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(nickName, "DAWEIKEKEMEI", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        LogUtil.d("main", nickName + "pyf 登录聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        LogUtil.d("main", "登录聊天服务器失败！");
                    }
                });
            }
        }).start();

    }



    public void createAccount(final int user_id) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    EMClient.getInstance().createAccount("" + user_id, "DAWEIKEKEMEI");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chatLogin("" + user_id);
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /**
                             * 关于错误码可以参考官方api详细说明
                             * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                             */
                            int errorCode = e.getErrorCode();
                            String message = e.getMessage();
                            LogUtil.d("lzan13",
                                    String.format("sign up - errorCode:%d, errorMsg:%s", errorCode,
                                            e.getMessage()));
                            switch (errorCode) {
                                // 网络错误
                                case EMError.NETWORK_ERROR:
                                    Toast.makeText(LoginActivity.this,
                                            "网络错误 code: " + errorCode + ", message:" + message,
                                            Toast.LENGTH_LONG).show();
                                    break;
                                // 用户已存在
                                case EMError.USER_ALREADY_EXIST:

                                    chatLogin("" + user_id);
                                    //                                    Toast.makeText(LoginActivity.this,
                                    //                                            "用户已存在 code: " + errorCode + ", message:" + message,
                                    //                                            Toast.LENGTH_LONG).show();
                                    break;
                                // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
                                case EMError.USER_ILLEGAL_ARGUMENT:
                                    Toast.makeText(LoginActivity.this,
                                            "参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册 code: "
                                                    + errorCode
                                                    + ", message:"
                                                    + message, Toast.LENGTH_LONG).show();
                                    break;
                                // 服务器未知错误
                                case EMError.SERVER_UNKNOWN_ERROR:
                                    Toast.makeText(LoginActivity.this,
                                            "服务器未知错误 code: " + errorCode + ", message:" + message,
                                            Toast.LENGTH_LONG).show();
                                    break;
                                case EMError.USER_REG_FAILED:
                                    Toast.makeText(LoginActivity.this,
                                            "账户注册失败 code: " + errorCode + ", message:" + message,
                                            Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    Toast.makeText(LoginActivity.this,
                                            "ml_sign_up_failed code: " + errorCode + ", message:" + message,
                                            Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 取消倒计时
     */
    public void oncancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void restart() {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(120000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (btnYanzhengma != null) {
                btnYanzhengma.setEnabled(false);
                btnYanzhengma.setText((millisUntilFinished / 1000) + "秒后可重发");
            }
        }

        @Override
        public void onFinish() {
            if (btnYanzhengma != null) {
                btnYanzhengma.setEnabled(true);
                btnYanzhengma.setText("获取验证码");
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
