package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.OrderDetailBean;
import com.kekemei.kekemei.bean.ServiceOrderListBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务订单详情
 */
public class ServiceOrderDetailActivity extends BaseActivity {
    private static final String EXTRA_KEY_ITEM = "item";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.orderId)
    TextView orderId;
    @BindView(R.id.orderTime)
    TextView orderTime;
    @BindView(R.id.orderStatus)
    TextView orderStatus;
    @BindView(R.id.serviceProject)
    TextView serviceProject;
    @BindView(R.id.countNum)
    TextView countNum;
    @BindView(R.id.orderPrice)
    TextView orderPrice;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.callPhone)
    ImageView callPhone;

    @BindView(R.id.shopAddress)
    TextView shopAddress;

    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;
    @BindView(R.id.cancelOrder)
    Button cancelOrder;
    @BindView(R.id.acceptOrder)
    Button acceptOrder;
    @BindView(R.id.startService)
    Button startService;
    @BindView(R.id.finishService)
    Button finishService;
    @BindView(R.id.replayComment)
    Button replayComment;

    private ServiceOrderListBean serviceItemBean;
    OrderDetailBean.DataBean data;

    public static void start(Context context, ServiceOrderListBean serviceItemBean) {
        Intent intent = new Intent(context, ServiceOrderDetailActivity.class);
        intent.putExtra(EXTRA_KEY_ITEM, serviceItemBean);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_service_order_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        serviceItemBean = (ServiceOrderListBean) getIntent().getSerializableExtra(EXTRA_KEY_ITEM);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("订单详情");
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
        OkGo.<String>get(URLs.ORDER_DETAILS)
                .params("id", serviceItemBean.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                        data = orderDetailBean.getData();
                        orderId.setText(data.getId() + "");
                        orderTime.setText(AppUtil.getFormatTime(data.getCreatetime()));
                        orderStatus.setText(data.getState_text());
                        serviceProject.setText(data.getName());
                        countNum.setText("X " + data.getCount());
                        orderPrice.setText("¥ " + data.getPrice());
                        name.setText(serviceItemBean.getUser_name());
                        phone.setText(serviceItemBean.getUser_mobile());
                        shopAddress.setText(data.getAddress());
                        if (data.getState().equals("1")) {
                            bottomBar.setVisibility(View.VISIBLE);
                            cancelOrder.setVisibility(View.VISIBLE);
                            acceptOrder.setVisibility(View.VISIBLE);
                            startService.setVisibility(View.GONE);
                            finishService.setVisibility(View.GONE);
                            replayComment.setVisibility(View.GONE);
                        } else if (data.getState().equals("2")) {
                            bottomBar.setVisibility(View.VISIBLE);
                            cancelOrder.setVisibility(View.GONE);
                            acceptOrder.setVisibility(View.GONE);
                            startService.setVisibility(View.VISIBLE);
                            finishService.setVisibility(View.GONE);
                            replayComment.setVisibility(View.GONE);
                        } else if (data.getState().equals("3")) {
                            bottomBar.setVisibility(View.VISIBLE);
                            cancelOrder.setVisibility(View.GONE);
                            acceptOrder.setVisibility(View.GONE);
                            startService.setVisibility(View.GONE);
                            finishService.setVisibility(View.VISIBLE);
                            replayComment.setVisibility(View.GONE);
                        } else if (data.getState().equals("4") || data.getState().equals("6") || data.getState().equals("10")) {
                            bottomBar.setVisibility(View.GONE);
                        } else if (data.getState().equals("5")) {
                            bottomBar.setVisibility(View.VISIBLE);
                            cancelOrder.setVisibility(View.GONE);
                            acceptOrder.setVisibility(View.GONE);
                            startService.setVisibility(View.GONE);
                            finishService.setVisibility(View.GONE);
                            replayComment.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @OnClick({R.id.cancelOrder, R.id.acceptOrder, R.id.startService, R.id.finishService,
            R.id.replayComment})
    public void onClick(View view) {
        long userId = UserHelp.getUserId(ServiceOrderDetailActivity.this);
        if (userId == -1L) {
            LoginActivity.start(ServiceOrderDetailActivity.this);
            return;
        }
        switch (view.getId()) {
            case R.id.cancelOrder:
                OkGo.<String>get(URLs.CANCEL_ORDER)
                        .params("user_id", userId)
                        .params("order_id", data.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        return;
                                    }
                                    ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "取消成功");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "取消失败");
                            }
                        });
                break;
            case R.id.acceptOrder:
                OkGo.<String>get(URLs.ACCEPC_ORDER)
                        .params("user_id", userId)
                        .params("order_id", data.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        return;
                                    }
                                    ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "接单成功");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "接单成功");
                            }
                        });
                break;
            case R.id.startService:
                OkGo.<String>get(URLs.ACCEPC_ORDER)
                        .params("user_id", userId)
                        .params("order_id", data.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        return;
                                    }
                                    ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "开始服务");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case R.id.finishService:
                OkGo.<String>get(URLs.OVER_ORDER)
                        .params("user_id", userId)
                        .params("order_id", data.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        return;
                                    }
                                    ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "订单完成");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToastMsg(ServiceOrderDetailActivity.this, "完成失败");
                            }
                        });
                break;
            case R.id.replayComment:
                break;
        }
    }
}