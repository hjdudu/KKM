package com.kekemei.kekemei.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.ALiPayResultBean;
import com.kekemei.kekemei.bean.OrderGeneratingBean;
import com.kekemei.kekemei.bean.WXPayResultBean;
import com.kekemei.kekemei.utils.Common;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CheckBoxSample;
import com.kekemei.kekemei.view.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by peiyangfan on 2018/11/6.
 */

public class MemberActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.top_bg)
    View topBg;
    @BindView(R.id.icon)
    CircleImageView icon;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.tv_daoqishijian)
    TextView tvDaoqishijian;
    @BindView(R.id.iv_check_wechat)
    CheckBoxSample ivCheckWechat;
    @BindView(R.id.iv_check_ali)
    CheckBoxSample ivCheckAli;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.member_price)
    TextView memberPrice;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tvTitle.setText("会员优惠");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userName.setText(UserHelp.getUserName(MemberActivity.this));
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + UserHelp.getAccount(MemberActivity.this), icon);
    }

    @Override
    protected void initData() {
        super.initData();
        registerReciver();


        OkGo.<String>get(URLs.MEMBER_CAR)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body());
                            String data = jsonObject.opt("data")+"";
                            memberPrice.setText("¥ " + data + "元");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @OnClick({R.id.iv_check_wechat, R.id.iv_check_ali, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_check_wechat:
                ivCheckWechat.toggle();
                ivCheckAli.setChecked(false);
                break;
            case R.id.iv_check_ali:
                ivCheckAli.toggle();
                ivCheckWechat.setChecked(false);
                break;
            case R.id.btn_pay:

                //                toSelectActivity();
                if (!ivCheckAli.isChecked() && !ivCheckWechat.isChecked()) {
                    ToastUtil.showToastMsg(MemberActivity.this, "请选择一种支付方式");
                    return;
                }
                long userId = UserHelp.getUserId(this);
                if (userId == -1L) {
                    LoginActivity.start(this);
                    return;
                }
                OkGo.<String>get(URLs.BUY_MEMBER_CAR)
                        .params("user_id", userId)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                OrderGeneratingBean orderGeneratingBean = gson.fromJson(response.body(), OrderGeneratingBean.class);
                                String payUrl = "";
                                if (ivCheckAli.isChecked()) {
                                    payUrl = URLs.ORDER_ALI_PAY;
                                    toALiPay(payUrl, orderGeneratingBean.getData().getOrder_id(), orderGeneratingBean.getTime());
                                } else {
                                    payUrl = URLs.ORDER_WX_PAY;
                                    toWXPay(payUrl, orderGeneratingBean.getData().getOrder_id(), orderGeneratingBean.getTime());
                                }
                            }
                        });


                break;
        }
    }


    private void toWXPay(String payUrl, String order_id, String time) {
        OkGo.<String>get(payUrl)
                .params("order_id", order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.d("aaaa", response.body());
                        Gson gson = new Gson();
                        WXPayResultBean payResult = gson.fromJson(response.body(), WXPayResultBean.class);
                        // TODO: 2018/11/13 进行支付操作
                        if (null != payResult && null != payResult.getData()) {
                            PayReq req = new PayReq();
                            WXPayResultBean.DataBean data = payResult.getData();
                            req.appId = data.getAppid();
                            req.partnerId = data.getMch_id();
                            req.prepayId = data.getPrepay_id();
                            req.packageValue = "Sign=WXPay";
                            req.nonceStr = data.getNonce_str();
                            req.timeStamp = data.getPay_time() + "";
                            req.sign = data.getSign2();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            LogUtil.d("PAY_GET", req.toString());
                            if (!api.isWXAppInstalled()) {
                                ToastUtil.showToastMsg(MemberActivity.this, "您还未安装微信客户端,请先安装微信客户端");
                                return;
                            }
                            api.sendReq(req);
                        } else {
                            LogUtil.d("PAY_GET", "返回错误" + payResult.getMsg());
                        }

                    }
                });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == Common.ACTIVITY_REQUEST_CODE_ALI_PAY) {
                handleAliPayResult(msg);
            } else if (msg.what == Common.ACTIVITY_REQUEST_CODE_WX_PAY) {
                handleWxPayResult(msg);
            }
        }
    };

    private void toALiPay(String payUrl, String order_id, String time) {
        OkGo.<String>get(payUrl)
                .params("order_id", order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        final ALiPayResultBean payResultBean = gson.fromJson(response.body(), ALiPayResultBean.class);
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(MemberActivity.this);
                                Map<String, String> result = alipay.payV2(payResultBean.getData().getInfo(), true);
                                Message msg = handler.obtainMessage();
                                msg.what = Common.ACTIVITY_REQUEST_CODE_ALI_PAY;
                                msg.obj = result;
                                handler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }
                });
    }


    private void handleWxPayResult(Message msg) {
        String str = "";
        int code = (int) msg.obj;
        switch (code) {
            case 0:
                str = "支付成功";
                toMyActivity();
                break;
            case -1:
                str = "订单信息错误";
                break;
            case -2:
                str = "用户取消";
                break;
        }
        LogUtil.d("PAY_GET", code + "");
    }

    @SuppressWarnings("unchecked")
    private void handleAliPayResult(Message msg) {
        Map<String, String> result = (Map<String, String>) msg.obj;
        int resultStatus = Integer.parseInt(result.get("resultStatus"));
        switch (resultStatus) {
            //支付成功
            case 9000:
                toMyActivity();
                break;
            //未知结果
            case 8000:
            case 6004:
                break;
            //取消支付
            case 6001:
                break;
            //其他情况,支付失败
            default:
                break;
        }
    }

    private void toMyActivity() {
        MainActivity.start(MemberActivity.this, 3);
    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int errCode;
            if (Common.WX_PAY_RESULT.equals(action)) {
                errCode = intent.getIntExtra(Common.ERROR_CODE, -9999);
                onWxPayResult(errCode);
            }
        }
    };

    public void onWxPayResult(int errCode) {
        Message message = handler.obtainMessage();
        message.what = Common.ACTIVITY_REQUEST_CODE_WX_PAY;
        message.obj = errCode;
        handler.sendMessage(message);
    }

    public void registerReciver() {

        //注册微信支付结果广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Common.WX_PAY_RESULT);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public void unregisterReciver() {
        unregisterReceiver(mBroadcastReceiver); //取消监听
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReciver();
    }
}
