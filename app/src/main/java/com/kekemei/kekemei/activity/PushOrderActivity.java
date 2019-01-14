package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.DayCheckAdapter2;
import com.kekemei.kekemei.adapter.YuYueDataListAdapter;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.BeauticianDetailBean;
import com.kekemei.kekemei.bean.CanlBean;
import com.kekemei.kekemei.bean.NearShopBean;
import com.kekemei.kekemei.bean.ShopBean;
import com.kekemei.kekemei.bean.ShopDetailBean;
import com.kekemei.kekemei.bean.YuYueActivityBean;
import com.kekemei.kekemei.bean.YuYueDataBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CircleImageView;
import com.kekemei.kekemei.view.StarBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kekemei.kekemei.activity.PayActivity.EXTRA_KEY_YUYUE_BEAN;

/**
 * Created 订单提交 by peiyangfan on 2018/10/23.
 */

public class PushOrderActivity extends BaseActivity {

    private static String icon_name = "";
    private static String icon_url = "";
    private static String order_name = "";
    private static String order_price = "";
    private static String image_url = "";
    private static String shop_place = "";
    private static long server_time = -1L;
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
    @BindView(R.id.iv_jian)
    ImageView ivJian;
    @BindView(R.id.tv_jian)
    TextView tvJian;
    @BindView(R.id.iv_man)
    ImageView ivMan;
    @BindView(R.id.tv_man)
    TextView tvMan;
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
    @BindView(R.id.tv_jia)
    TextView tvJia;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.civ_icon)
    CircleImageView civIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.sb_num)
    StarBar sbNum;
    @BindView(R.id.ll_meirongshi_select)
    LinearLayout llMeirongshiSelect;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.ll_shop_place)
    LinearLayout llShopPlace;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_service_time)
    LinearLayout llServiceTime;
    @BindView(R.id.btn_yuyue)
    Button btnYuyue;
    @BindView(R.id.show_select_time)
    LinearLayout showSelectTime;
    @BindView(R.id.rv_list_yuyue)
    RecyclerView rvListYuyue;
    @BindView(R.id.queding)
    Button queding;
    @BindView(R.id.ll_select_time)
    LinearLayout llSelectTime;
    @BindView(R.id.id_recyclerview_horizontal)
    RecyclerView idRecyclerviewHorizontal;
    @BindView(R.id.ll_meirongshi_info)
    LinearLayout llMeirongshiInfo;
    @BindView(R.id.ll_meirongshi_info_hint)
    LinearLayout llMeirongshiInfoHint;

    private String name = "";
    private int orderNumber = 0;

    private YuYueActivityBean yuYueActivityBean;
    private TextView tv_date_and_week;
    private TextView tv_can_yuyue;

    public static final int EXTRA_KEY_SHOP_CODE = 1000;
    public static final int EXTRA_KEY_BEAUTICIAN_CODE = 1001;
    private BeauticianDetailBean beauticianDataBeanData;
    private ShopDetailBean shpDataBeanData;
    private String shopId;
    private String beauticianId;
    private NearShopBean nearShopBean;

    public static void start(Context context, YuYueActivityBean yuYueActivityBean) {
        Intent intent = new Intent(context, PushOrderActivity.class);
        intent.putExtra(EXTRA_KEY_YUYUE_BEAN, yuYueActivityBean);
        context.startActivity(intent);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_push_order;
    }


    @Override
    protected void initData() {
        super.initData();

        // TODO: 2018/11/14   根據訂單號獲取訂單信息
        initOrderData();


        if (yuYueActivityBean.getShopDetailBean() != null) {
            // TODO: 2018/11/14  通过店铺查找美容师
            llShopPlace.setClickable(false);
            tvPlace.setText(yuYueActivityBean.getShopDetailBean().getAddress());
        } else {
            tvPlace.setText("请选择店铺");
        }
        if (yuYueActivityBean.getBeauticianDetailBean() != null) {
            llMeirongshiInfo.setVisibility(View.VISIBLE);
            llMeirongshiInfoHint.setVisibility(View.GONE);
            // TODO: 2018/11/14  店铺主页
            llMeirongshiSelect.setClickable(false);
            tvName.setText(yuYueActivityBean.getBeauticianDetailBean().getNickname());
            tvPlace.setText(yuYueActivityBean.getBeauticianDetailBean().getPlace());
            ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + yuYueActivityBean.getBeauticianDetailBean().getImage(), civIcon);
            sbNum.setStarMark(Float.valueOf(yuYueActivityBean.getBeauticianDetailBean().getStart()));
        } else {
            llMeirongshiInfo.setVisibility(View.GONE);
            llMeirongshiInfoHint.setVisibility(View.VISIBLE);
        }
        if (yuYueActivityBean.getNearShopBean() != null && tvPlace.getText().equals("请选择店铺")) {
            llShopPlace.setClickable(false);
            tvPlace.setText(yuYueActivityBean.getNearShopBean().getShopname());
        } else {
            tvPlace.setText("请选择店铺");
        }


        if (yuYueActivityBean.getTimeSelect() != -1 && yuYueActivityBean.getDateSelect() != -1L) {
            llServiceTime.setClickable(false);
            tvTime.setText(AppUtil.getFormatTime1(yuYueActivityBean.getDateSelect()) + "  " + yuYueActivityBean.getTimeSelectName());

        }

    }

    private void initOrderData() {

    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);


        toolbar.setNavigationIcon(R.mipmap.back);
        tvTitle.setText("预约美容师");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llAdd.setVisibility(View.GONE);
        tvOrderNum.setVisibility(View.GONE);


        yuYueActivityBean = (YuYueActivityBean) getIntent().getSerializableExtra(EXTRA_KEY_YUYUE_BEAN);
        order_name = yuYueActivityBean.getOrderName();
        image_url = yuYueActivityBean.getOrderIconUrl();
        order_price = yuYueActivityBean.getOrderPrice() + "";
        timeSelectName = yuYueActivityBean.getTimeSelectName();
        timeSelectPosition = yuYueActivityBean.getTimeSelect();
        daySelectPosition = yuYueActivityBean.getDateSelect();


        if (yuYueActivityBean.getBeauticianDetailBean() != null) {
            beauticianDataBeanData = yuYueActivityBean.getBeauticianDetailBean();
            beauticianId = beauticianDataBeanData.getId();
        }
        if (yuYueActivityBean.getShopDetailBean() != null) {
            shpDataBeanData = yuYueActivityBean.getShopDetailBean();
            shopId = shpDataBeanData.getId();
        }
        if (yuYueActivityBean.getNearShopBean() != null) {
            nearShopBean = yuYueActivityBean.getNearShopBean();
            shopId = nearShopBean.getId() + "";
        }

        tvNum.setText(orderNumber + "");
        tvPrice.setText("¥ " + order_price);
        tvName.setText(icon_name);
        tvOrderName.setText(order_name);
        tvTime.setText(timeSelectName == null ? "请选择预约时间" : AppUtil.getFormatTime1(daySelectPosition) + "    " + timeSelectName);

        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + image_url, ivOrderIcon);
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + icon_url, ivMan);


        rvListYuyue.setLayoutManager(new GridLayoutManager(getBaseContext(), 4));
        yuYueDataListAdapter = new YuYueDataListAdapter(this);
        rvListYuyue.setAdapter(yuYueDataListAdapter);
        yuYueDataListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<YuYueDataBean.DataBean> data = yuYueDataListAdapter.getData();
                if (view.getId() == R.id.ll_select_data_time) {

                    tv_date_and_week = (TextView) adapter.getViewByPosition(rvListYuyue, position, R.id.tv_date_and_week);
                    tv_can_yuyue = (TextView) adapter.getViewByPosition(rvListYuyue, position, R.id.tv_can_yuyue);
                    for (YuYueDataBean.DataBean item : data) {
                        item.setSelect(false);
                        view.setBackground(ContextCompat.getDrawable(PushOrderActivity.this, R.drawable.btn_white_background));
                        tv_date_and_week.setTextColor(0XFF999999);
                        tv_can_yuyue.setTextColor(0XFF999999);
                    }
                    data.get(position).setSelect(true);
                    timeSelectPosition = data.get(position).getId();
                    timeSelectName = data.get(position).getName();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }


    @OnClick({R.id.ll_meirongshi_select, R.id.ll_shop_place, R.id.ll_service_time, R.id.btn_yuyue
            , R.id.show_select_time, R.id.queding, R.id.ll_select_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_meirongshi_select:
                if (StringUtils.isEmpty(shopId))
                    ToastUtil.showToastMsg(getBaseContext(), "请先选择店铺");
                else
                    ShopBeauticianActivity.start(PushOrderActivity.this,
                            shopId + "",
                            false, EXTRA_KEY_BEAUTICIAN_CODE);
                break;
            case R.id.ll_shop_place:
                if (StringUtils.isEmpty(beauticianId))
                    ShopBeauticianActivity.start(PushOrderActivity.this,
                            null,
                            true, EXTRA_KEY_SHOP_CODE);
                else
                    ShopBeauticianActivity.start(PushOrderActivity.this,
                            beauticianId + "",
                            true, EXTRA_KEY_SHOP_CODE);
                break;
            case R.id.ll_service_time:
                if (StringUtils.isEmpty(beauticianId) || StringUtils.isEmpty(shopId)) {
                    ToastUtil.showToastMsg(PushOrderActivity.this, "请确认已选择美容师及店铺");
                    return;
                }
                initDayTime();
                break;
            case R.id.btn_yuyue:
                long userId = UserHelp.getUserId(this);
                if (userId == -1L) {
                    LoginActivity.start(this);
                    return;
                }
                if (shpDataBeanData != null && StringUtils.isEmpty(shopId))
                    shopId = shpDataBeanData.getId();
                if (beauticianDataBeanData != null && StringUtils.isEmpty(beauticianId)) {
                    beauticianId = beauticianDataBeanData.getId();
                }
                if (StringUtils.isEmpty(shopId)) shopId = nearShopBean.getId()+"";
                if (StringUtils.isEmpty(shopId) || StringUtils.isEmpty(beauticianId) || timeSelectPosition == -1 || daySelectPosition == -1L) {
                    ToastUtil.showToastMsg(getBaseContext(), "请依次确定预约信息");
                    return;
                }
                OkGo.<String>get(URLs.ADD_APPOINTMENT)
                        .params("user_id", userId)
                        .params("shop_id", shopId)
                        .params("beautician_id", beauticianId)
                        .params("timedata", timeSelectPosition)
                        .params("timedstartdate", (daySelectPosition + "").substring(0, 10))
                        .params("order_id", yuYueActivityBean.getOrderId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                MainActivity.start(PushOrderActivity.this, 2);
                                finish();
                            }
                        });
                break;

            case R.id.show_select_time:
                break;
            case R.id.queding:
                llSelectTime.setVisibility(View.GONE);
                btnYuyue.setVisibility(View.VISIBLE);

                tvTime.setText(AppUtil.getFormatTime1(daySelectPosition) + "  " + timeSelectName);
                break;
            case R.id.ll_select_time:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == EXTRA_KEY_SHOP_CODE) {
            ShopBean shopBean = (ShopBean) data.getSerializableExtra("ShopBean");
            if (shpDataBeanData == null) {
                shpDataBeanData = new ShopDetailBean();
            }
            shpDataBeanData.setAddress(shopBean.getAddress());
            shopId = shopBean.getId();
            shpDataBeanData.setId(shopId);
            tvPlace.setText(shopBean.getCity() + "    " + shopBean.getAddress());

        }
        if (requestCode == EXTRA_KEY_BEAUTICIAN_CODE) {
            BeauticianBean beauticianBean = (BeauticianBean) data.getSerializableExtra("BeauticianBean");
            if (beauticianDataBeanData == null)
                beauticianDataBeanData = new BeauticianDetailBean();
            beauticianId = beauticianBean.getId();
            beauticianDataBeanData.setId(beauticianId);
            beauticianDataBeanData.setName(beauticianBean.getName());
            beauticianDataBeanData.setImage(beauticianBean.getImage());
            beauticianDataBeanData.setStart(beauticianBean.getStart());


            llMeirongshiInfo.setVisibility(View.VISIBLE);
            llMeirongshiInfoHint.setVisibility(View.GONE);
            ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + beauticianBean.getImage(), civIcon);
            tvName.setText(beauticianBean.getName());
            sbNum.setStarMark(beauticianBean.getStart());
        }
    }

    private ArrayList<Calendar> calList = new ArrayList<>();

    private void initDayTime() {
        final CanlBean canlBean = new CanlBean();
        calList.clear();
        for (int i = 0; i < 30; i++) {
            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i);
            calList.add(cal);

        }
        canlBean.setDataBean(calList);

        long timeInMillis = canlBean.getDataBean().get(0).getTimeInMillis();
        String timestamp = String.format("%010d", timeInMillis);
        timeData(Long.valueOf(timestamp));
        daySelectPosition = Long.valueOf(timestamp);
        LogUtil.d("ShopActivity", daySelectPosition + "");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        idRecyclerviewHorizontal.setLayoutManager(linearLayoutManager);
        dayAdapter = new DayCheckAdapter2(this, canlBean.getDataBean(), beauticianId);
        idRecyclerviewHorizontal.setAdapter(dayAdapter);

        dayAdapter.setOnItemClickLitener(new DayCheckAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                long timeInMillis1 = canlBean.getDataBean().get(position).getTimeInMillis();
                String format = String.format("%010d", timeInMillis1);
                timeData(Long.valueOf(format));
                daySelectPosition = Long.valueOf(format);
            }
        });


        llSelectTime.setVisibility(View.VISIBLE);

        btnYuyue.setVisibility(View.GONE);
        showSelectTime.setVisibility(View.GONE);
    }

    private Calendar cal;
    private DayCheckAdapter2 dayAdapter;
    private YuYueDataListAdapter yuYueDataListAdapter;

    private int timeSelectPosition = -1;
    private String timeSelectName = "";
    private long daySelectPosition = -1L;

    /**
     * 预约时间
     */
    public void timeData(long timedstartdate) {
        OkGo.<String>post(URLs.APPOINTMENT_TIME_DATA)
                .params("beautician", beauticianId)
                .params("startdate", (timedstartdate+"").substring(0,10)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.e("PushOrderActivity", "follow beautician:" + response.body());
                Gson gson = new Gson();
                YuYueDataBean yuYueDataBean = gson.fromJson(response.body(), YuYueDataBean.class);
                yuYueDataListAdapter.setNewData(yuYueDataBean.getData());
            }
        });
    }
}
