package com.kekemei.kekemei.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.ALiPayResultBean;
import com.kekemei.kekemei.bean.OrderByIdBean;
import com.kekemei.kekemei.bean.OrderGeneratingBean;
import com.kekemei.kekemei.bean.WXPayResultBean;
import com.kekemei.kekemei.bean.YuYueActivityBean;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.Common;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.SPUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CheckBoxSample;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 支付页面
 */
public class PayActivity extends BaseActivity {
    public static final String EXTRA_KEY_YUYUE_BEAN = "ORDER_CREATE_TIME";

    private static final int PAY_TO_VOUCHER_CODE = 100;
    private static final int PAY_TO_RED_CODE = PAY_TO_VOUCHER_CODE + 1;
    private static final int PAY_TO_MAN_JIAN_CODE = PAY_TO_RED_CODE + 1;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_order_icon)
    ImageView ivOrderIcon;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_jian)
    TextView tvJian;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_jia)
    TextView tvJia;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.tv_youhuiquan_num)
    TextView tvYouhuiquanNum;
    @BindView(R.id.ll_toyouhuiquan)
    LinearLayout llToYouhuiquan;
    @BindView(R.id.tv_red_bao_num)
    TextView tvRedBaoNum;
    @BindView(R.id.ll_red_bao)
    LinearLayout llRedBao;
    @BindView(R.id.tv_man_jian_hint)
    TextView tvManJianHint;
    @BindView(R.id.tv_man_jian_num)
    TextView tvManJianNum;
    @BindView(R.id.ll_man_jian)
    LinearLayout llManJian;
    @BindView(R.id.iv_check_wechat)
    CheckBoxSample ivCheckWechat;
    @BindView(R.id.iv_check_ali)
    CheckBoxSample ivCheckAli;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.iv_jian)
    ImageView ivJian;
    @BindView(R.id.iv_man)
    ImageView ivMan;
    @BindView(R.id.tv_man)
    TextView tvMan;
    @BindView(R.id.tv_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_juli)
    TextView tvJuli;
    @BindView(R.id.ll_julli)
    LinearLayout llJulli;
    @BindView(R.id.tv_wait_to_pay)
    TextView tvWaitToPay;

    private float order_price;
    private int order_count;
    private String order_image;
    private String order_name;
    private String order_id;
    private String orderCreatTime;

    private int hongbaonum, youhuiqunnum, manjiannum = 0;

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
    private YuYueActivityBean yuYueActivityBean;
    private String project_id;
    private String redId;
    private String couponId;
    private String manjianId;
    private String out_trade_no;

    private OrderGeneratingBean orderGeneratingBean;

    public static void start(Activity activity, YuYueActivityBean yuYueActivityBean) {
        long userId = UserHelp.getUserId(activity);
        if (userId == -1L) {
            LoginActivity.start(activity);
            return;
        }
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(EXTRA_KEY_YUYUE_BEAN, yuYueActivityBean);
        activity.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("支付");
        tvOrderNum.setVisibility(View.VISIBLE);

        registerReciver();
        yuYueActivityBean = (YuYueActivityBean) getIntent().getSerializableExtra(EXTRA_KEY_YUYUE_BEAN);
        order_name = yuYueActivityBean.getOrderName();
        order_image = yuYueActivityBean.getOrderIconUrl();
        order_price = yuYueActivityBean.getOrderPrice();
        order_count = yuYueActivityBean.getOrderCount();
        project_id = yuYueActivityBean.getProject_id();
    }

    @Override
    protected void initData() {
        super.initData();
        if (!yuYueActivityBean.getFromDetail())
            getData();
        else
            getDataFromDetail();
    }

    private void getDataFromDetail() {
//        generatingOrder(redId, couponId);
        order_name = yuYueActivityBean.getOrderName();

        tvOrderName.setText(order_name);
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + order_image, ivOrderIcon);
//                        order_price = orderByIdBean.getData().getPrice();
        tvPrice.setText("¥ " + order_price);
        order_count = yuYueActivityBean.getOrderCount();
        tvOrderNum.setText("X" + order_count);

        money.setText("¥ " + (order_price * order_count - youhuiqunnum - hongbaonum - manjiannum));

        redId = yuYueActivityBean.getRed_id() + "";
        couponId = yuYueActivityBean.getCoupon_id() + "";
        tvRedBaoNum.setText(yuYueActivityBean.getRed_text());
        tvYouhuiquanNum.setText(yuYueActivityBean.getCoupon_text());
        generatingOrder(redId, couponId);

    }

    private void getData() {
        OkGo.<String>get(URLs.ORDER_BY_ID)
                .params("user_id", UserHelp.getUserId(this))
                .params("id", yuYueActivityBean.getOrderId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            if (msg.equals("暂无数据")) {
                                ToastUtil.showToastMsg(PayActivity.this, "订单生成失败，请稍后重试...");
                                finish();
                                return;
                            }
                            Gson gson = new Gson();
                            OrderByIdBean orderByIdBean = gson.fromJson(response.body(), OrderByIdBean.class);
                            if (CollectionUtils.isNotEmpty(orderByIdBean.getData().getUser_red())) {
                                tvRedBaoNum.setText("- ¥ " + orderByIdBean.getData().getUser_red().get(0).getPrice_reduction());
                                redId = orderByIdBean.getData().getUser_red().get(0).getId() + "";
                            }
                            if (CollectionUtils.isNotEmpty(orderByIdBean.getData().getUser_coupon()) && orderByIdBean.getData().getUser_coupon().get(0).getPrice_reduction() > -1) {
                                tvYouhuiquanNum.setText("- ¥ " + orderByIdBean.getData().getUser_coupon().get(0).getPrice_reduction() + "");
                                couponId = orderByIdBean.getData().getUser_coupon().get(0).getId() + "";
                            }
                            order_name = orderByIdBean.getData().getName();

                            tvOrderName.setText(order_name);
                            ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + order_image, ivOrderIcon);
//                        order_price = orderByIdBean.getData().getPrice();
                            tvPrice.setText("¥ " + order_price);
                            order_count = orderByIdBean.getData().getCount();
                            tvOrderNum.setText("X" + order_count);

                            money.setText("¥ " + (order_price * order_count - youhuiqunnum - hongbaonum - manjiannum));

                            order_id = yuYueActivityBean.getOrderId();
                            orderCreatTime = yuYueActivityBean.getOrderCreateTime();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void generatingOrder(String redId, String couponId) {
        OkGo.<String>get(URLs.ORDER_GENERATING)
                .params("user_id", UserHelp.getUserId(this))
                .params("name", yuYueActivityBean.getOrderName())
                .params("project_id", yuYueActivityBean.getProject_id())
                .params("reden", redId)
                .params("coupon", couponId)
                .params("shop_id",
                        yuYueActivityBean.getShopDetailBean() == null ? ""
                                : yuYueActivityBean.getShopDetailBean().getId())
                .params("buautician_id",
                        yuYueActivityBean.getBeauticianDetailBean() == null ? ""
                                : yuYueActivityBean.getBeauticianDetailBean().getId())
                .params("count", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            if (msg.equals("暂无数据")) {
                                ToastUtil.showToastMsg(PayActivity.this, "订单生成失败，请稍后重试...");
                                finish();
                                return;
                            }
                            Gson gson = new Gson();
                            orderGeneratingBean = gson.fromJson(response.body(), OrderGeneratingBean.class);
                            order_id = orderGeneratingBean.getData().getOrder_id();
                            orderCreatTime = orderGeneratingBean.getTime();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick({R.id.ll_toyouhuiquan, R.id.ll_red_bao, R.id.ll_man_jian, R.id.iv_check_wechat, R.id.iv_check_ali, R.id.btn_pay})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_toyouhuiquan:
                intent = new Intent(PayActivity.this, MyVoucherActivity.class);
                startActivityForResult(intent, PAY_TO_VOUCHER_CODE);
                break;
            case R.id.ll_red_bao:
                intent = new Intent(PayActivity.this, MyRedBaoActivity.class);
                startActivityForResult(intent, PAY_TO_RED_CODE);
                break;
            case R.id.ll_man_jian:
                //                intent = new Intent(PayActivity.this, MyVoucherActivity.class);
                //                startActivityForResult(intent, PAY_TO_MAN_JIAN_CODE);
                break;
            case R.id.iv_check_wechat:
                ivCheckWechat.toggle();
                ivCheckAli.setChecked(false);
                break;
            case R.id.iv_check_ali:
                ivCheckAli.toggle();
                ivCheckWechat.setChecked(false);
                break;
            case R.id.btn_pay:
                if (orderGeneratingBean == null || orderGeneratingBean.getData() == null) {
                    ToastUtil.showToastMsg(PayActivity.this, "订单生成有误，请稍后重试...");
                    return;
                }
                if (!ivCheckAli.isChecked() && !ivCheckWechat.isChecked()) {
                    ToastUtil.showToastMsg(PayActivity.this, "请选择一种支付方式");
                    return;
                }
                if (ivCheckAli.isChecked()) {
                    toALiPay(order_id, orderCreatTime);
                } else {
                    toWXPay(order_id, orderCreatTime);
                }
                break;
        }
    }

    private void toWXPay(String order_id, String time) {
        OkGo.<String>get(URLs.ORDER_WX_PAY)
                .params("order_id", order_id)
                .params("project_id", project_id)
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
                            out_trade_no = data.getOut_trade_no();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            LogUtil.d("PAY_GET", req.toString());
                            if (!api.isWXAppInstalled()) {
                                ToastUtil.showToastMsg(PayActivity.this, "您还未安装微信客户端,请先安装微信客户端");
                                return;
                            }
                            api.sendReq(req);
                        } else {
                            LogUtil.d("PAY_GET", "返回错误" + payResult.getMsg());
                        }

                    }
                });
    }

    private void toALiPay(String order_id, String time) {
        OkGo.<String>get(URLs.ORDER_ALI_PAY)
                .params("order_id", order_id)
                .params("project_id", project_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        final ALiPayResultBean payResultBean = gson.fromJson(response.body(), ALiPayResultBean.class);
                        out_trade_no = payResultBean.getData().getOut_trade_no();
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(PayActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAY_TO_RED_CODE && resultCode == Activity.RESULT_OK) {
            hongbaonum = data.getExtras().getInt("price");//得到新Activity 关闭后返回的数据
            redId = data.getStringExtra("id");
            tvRedBaoNum.setText("- ¥ " + hongbaonum);

        }

        if (requestCode == PAY_TO_VOUCHER_CODE && resultCode == Activity.RESULT_OK) {
            youhuiqunnum = data.getExtras().getInt("price");//得到新Activity 关闭后返回的数据
            couponId = data.getStringExtra("id");
            tvYouhuiquanNum.setText("- ¥ " + youhuiqunnum);
        }

        if (requestCode == PAY_TO_MAN_JIAN_CODE && resultCode == Activity.RESULT_OK) {
            manjiannum = data.getExtras().getInt("price");//得到新Activity 关闭后返回的数据
            manjianId = data.getStringExtra("id");
            tvManJianNum.setText("- ¥ " + manjiannum);
        }

        money.setText("¥ " + (order_price * order_count - youhuiqunnum - hongbaonum - manjiannum));
    }


    // TODO: 2018/11/13 去预约美容师页面
    private void toSelectActivity() {
        OkGo.<String>post(URLs.ORDER_REFUND)
                .params("out_trade_no", out_trade_no)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = (int) jsonObject.opt("code");
                            if (code == 1) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                                builder.setTitle("提示");
                                builder.setMessage("您已支付成功，请预约美容师");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                        MainActivity.start(PayActivity.this, 2);
//                                        SPUtils.putBoolean(getApplicationContext(), SPUtils.SELECT_YUYUE, true);
                                        yuYueActivityBean.setOrderId(order_id);
                                        PushOrderActivity.start(PayActivity.this,yuYueActivityBean);
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.show();
                            } else {
                                ToastUtil.showToastMsg(getApplicationContext(), (String) jsonObject.opt("msg"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtil.showToastMsg(getApplicationContext(), response.body());
                    }
                });

    }


    private void handleWxPayResult(Message msg) {
        String str = "";
        int code = (int) msg.obj;
        switch (code) {
            case 0:
                str = "支付成功";
                toSelectActivity();
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
                toSelectActivity();
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
