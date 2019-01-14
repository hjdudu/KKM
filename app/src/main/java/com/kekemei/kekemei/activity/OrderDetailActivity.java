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
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.OrderDetailBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.URLs;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 订单详情页面
 */
public class OrderDetailActivity extends BaseActivity {

    private static final String EXTRA_KEY_ID = "";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_order_icon)
    ImageView ivOrderIcon;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.ll_youhuiquan)
    LinearLayout llYouhuiquan;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_juli)
    TextView tvJuli;
    @BindView(R.id.ll_julli)
    LinearLayout llJulli;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_meirongshi_name)
    TextView tvMeirongshiName;
    @BindView(R.id.ll_meirongshi_info_hint)
    LinearLayout llMeirongshiInfoHint;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.ll_shop_place)
    LinearLayout llShopPlace;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_service_time)
    LinearLayout llServiceTime;
    @BindView(R.id.tv_order_num_)
    TextView tvOrderNum_;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_info_order_num)
    TextView tvInfoOrderNum;
    @BindView(R.id.tv_info_order_cre_time)
    TextView tvInfoOrderCreTime;
    @BindView(R.id.tv_info_order_youhui)
    TextView tvInfoOrderYouhui;
    @BindView(R.id.tv_info_order_red)
    TextView tvInfoOrderRed;
    @BindView(R.id.tv_info_order_manjian)
    TextView tvInfoOrderManjian;
    @BindView(R.id.tv_info_order_state)
    TextView tvInfoOrderState;
    @BindView(R.id.tv_info_order_fin_time)
    TextView tvInfoOrderFinTime;
    @BindView(R.id.tv_youhuiquan)
    TextView tvYouhuiquan;
    @BindView(R.id.tv_hongbao)
    TextView tvHongbao;
    @BindView(R.id.tv_manjian)
    TextView tvManjian;


    private String id;
    private OrderDetailBean.DataBean data;


    public static void start(Context context, String id) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_KEY_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        id = super.getStringExtraSecure(EXTRA_KEY_ID);
        tvOrderNum.setVisibility(View.VISIBLE);

        toolbar.setNavigationIcon(R.mipmap.back);
        tvTitle.setText("订单详情");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();


        getData();
    }

    private void getData() {
        OkGo.<String>get(URLs.ORDER_DETAILS)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                        data = orderDetailBean.getData();
                        tvOrderName.setText(data.getName());
                        tvOrderNum.setText("X " + data.getCount() + "");
                        tvOrderNum_.setText("x " + data.getCount() + "  ");
                        tvOrderPrice.setText("¥ " + data.getPrice() + "");
                        tvMeirongshiName.setText(data.getBeauticianname() == null ? "" : data.getBeauticianname().toString());
                        tvPlace.setText(data.getAddress() == null ? "" : data.getAddress().toString());
                        tvTime.setText(data.getServicetime() + "");
                        tvPrice.setText("¥ " + data.getProject_price() + " ");
                        tvInfoOrderCreTime.setText(AppUtil.getFormatTime(data.getCreatetime()) + "");
                        tvInfoOrderFinTime.setText(data.getFinishtime_text() + "");
                        tvInfoOrderNum.setText(data.getId() + "");
                        if (data.getConpou() == null || data.getConpou() == "") {
                            tvInfoOrderYouhui.setVisibility(View.GONE);
                            tvYouhuiquan.setVisibility(View.GONE);
                        } else {
                            tvInfoOrderYouhui.setVisibility(View.VISIBLE);
                            tvYouhuiquan.setVisibility(View.VISIBLE);
                            tvInfoOrderYouhui.setText(data.getConpou() + "");
                        }
                        if (data.getRedenvelopes() == null || data.getRedenvelopes() == "") {
                            tvInfoOrderRed.setVisibility(View.GONE);
                            tvHongbao.setVisibility(View.GONE);
                        } else {
                            tvInfoOrderRed.setVisibility(View.VISIBLE);
                            tvHongbao.setVisibility(View.VISIBLE);
                            tvInfoOrderRed.setText(data.getRedenvelopes() + "");
                        }
                        if (data.getFull() == null || data.getFull() == "") {
                            tvInfoOrderManjian.setVisibility(View.GONE);
                            tvManjian.setVisibility(View.GONE);
                        } else {
                            tvInfoOrderManjian.setVisibility(View.VISIBLE);
                            tvManjian.setVisibility(View.VISIBLE);
                            tvInfoOrderManjian.setText(data.getFull() + "");
                        }
                        tvInfoOrderState.setText(data.getState_text() + "");
                        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + data.getProject_image(), ivOrderIcon);
                    }
                });
    }

    @OnClick({R.id.ll_meirongshi_info_hint, R.id.tv_place, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_meirongshi_info_hint:
                if (data.getBeauticianid() == null) return;
                ShopActivity.start(OrderDetailActivity.this, data.getBeauticianid() + "", DetailEnum.BEAUTICIAN);
                break;
            case R.id.tv_place:
                if (data.getProject_id() == null) return;
                ShopActivity.start(OrderDetailActivity.this, data.getProject_id() + "", DetailEnum.SHOP);
                break;
            case R.id.btn_pay:
                AddCommentActivity.start(OrderDetailActivity.this, data.getSource(), data.getId() + "");
                break;
        }
    }
}
