package com.kekemei.kekemei.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.DayCheckAdapter2;
import com.kekemei.kekemei.adapter.EvaluateListAdapter;
import com.kekemei.kekemei.adapter.MyGridAdapter;
import com.kekemei.kekemei.adapter.YuYueDataListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.BeauticianDetailBean;
import com.kekemei.kekemei.bean.CanlBean;
import com.kekemei.kekemei.bean.CommentTagsBean;
import com.kekemei.kekemei.bean.CommentdataBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.ProjectDetailBean;
import com.kekemei.kekemei.bean.ShopDetailBean;
import com.kekemei.kekemei.bean.TradingBean;
import com.kekemei.kekemei.bean.YuYueActivityBean;
import com.kekemei.kekemei.bean.YuYueDataBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.CustomDatePicker;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.MapUtil;
import com.kekemei.kekemei.utils.SPUtils;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CommonDialog;
import com.kekemei.kekemei.view.MultipleStatusView;
import com.kekemei.kekemei.view.StarBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMShareAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目详情页面
 */
public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_KEY_BEAUTICIAN_ID = "beauticianId";
    public static final String EXTRA_KEY_TIME_SELECT = "timeSelectId";
    public static final String EXTRA_KEY_DAY_SELECT = "daySelect";
    public static final String EXTRA_KEY_TIME_NAME = "timeSelectName";
    public static final String EXTRA_KEY_SHOP_DETAIL_BEAN = "shopDetailBean";
    public static final String EXTRA_KEY_BEAUTICIAN_DETAIL_BEAN = "beauticianDetailBean";
    public static final String EXTRA_KEY_DETAIL_ENUM = "detailEnum";
    private double latx = 39.9555282581;
    private double laty = 116.3211617110;
    private String mAddress = "北京天安门";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_share)
    ImageView iv_share;

    @BindView(R.id.scrollLayout)
    ScrollView scrollLayout;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    @BindView(R.id.shop_detail_icon)
    ImageView shop_detail_icon;

    @BindView(R.id.shopName)
    TextView shopName;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.marketPrice)
    TextView marketPrice;
    @BindView(R.id.tvCollection)
    TextView tvCollection;
    @BindView(R.id.tvFollowNum)
    TextView tvFollowNum;

    @BindView(R.id.projectDetail)
    RelativeLayout projectDetail;
    @BindView(R.id.indicatorProjectDetail)
    ImageView indicatorProjectDetail;

    @BindView(R.id.userEvaluate)
    RelativeLayout userEvaluate;
    @BindView(R.id.indicatorEvaluate)
    ImageView indicatorEvaluate;

    @BindView(R.id.contentView)
    LinearLayout contentView;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_buy_now)
    TextView tvBuyNow;

    @BindView(R.id.ll_dianpu_tab)
    LinearLayout llDianpuTab;
    @BindView(R.id.id_recyclerview_horizontal)
    RecyclerView id_recyclerview_horizontal;
    @BindView(R.id.show_select_time)
    LinearLayout showSelectTime;
    @BindView(R.id.ll_select_time)
    LinearLayout llSelectTime;
    @BindView(R.id.rv_list_yuyue)
    RecyclerView rvListYuyue;
    @BindView(R.id.tvToShopDetail)
    TextView tvToShopDetail;
    @BindView(R.id.layoutBottomBar)
    LinearLayout layoutBottomBar;
    @BindView(R.id.tvRedBao)
    TextView tvRedBao;
    @BindView(R.id.ivRedBao)
    ImageView ivRedBao;
    @BindView(R.id.tvFul)
    TextView tvFul;
    @BindView(R.id.ivFull)
    ImageView ivFull;

    private WebView webContainer;

    private CommentdataBean commentdata;
    private View commentSectionView;
    private TextView userCommentNum;
    private TextView commentTabAll;
    private TextView commentTabNew;
    private TextView commentTabPhoto;
    private TextView tvCommentPeer;
    private LinearLayout markLayout;
    private TextView starNum;
    private StarBar starBar;
    private FlexboxLayout commentTagFlowLayout;
    private RecyclerView rvCommentList;

    private RecyclerView hotProjectRv;

    private MyGridAdapter contentSectionAdapter;
    private EvaluateListAdapter commentAdapter;

    private ProjectDetailBean detailBean;


    private String beauticianId;
    private long daySelectPosition = -1L;

    private int timeSelectPosition = -1;
    private String timeSelectName = "";
    @Nullable
    private LinearLayout ll_yuyue;
    private Calendar cal;
    private DayCheckAdapter2 dayAdapter;
    private YuYueDataListAdapter yuYueDataListAdapter;
    private TextView tv_date_and_week;
    private TextView tv_can_yuyue;
    private ShopDetailBean shopDetailBean;
    private BeauticianDetailBean beauticianDetailBean;


    public static void start(Context context, String beauticianId) {
        Intent intent = new Intent(context, ProjectDetailActivity.class);
        intent.putExtra(EXTRA_KEY_BEAUTICIAN_ID, beauticianId);
        context.startActivity(intent);
    }

    public static void start(Context context, String beauticianId, int timeSelectPosition, String timeSelectName, long daySelectPosition, ShopDetailBean shopDetailBean, DetailEnum detailEnum) {
        Intent intent = new Intent(context, ProjectDetailActivity.class);
        intent.putExtra(EXTRA_KEY_BEAUTICIAN_ID, beauticianId);
        intent.putExtra(EXTRA_KEY_TIME_SELECT, timeSelectPosition);
        intent.putExtra(EXTRA_KEY_TIME_NAME, timeSelectName);
        intent.putExtra(EXTRA_KEY_DAY_SELECT, daySelectPosition);
        intent.putExtra(EXTRA_KEY_SHOP_DETAIL_BEAN, shopDetailBean);
        intent.putExtra(EXTRA_KEY_DETAIL_ENUM, detailEnum);
        context.startActivity(intent);
    }

    public static void start(Context context, String beauticianId, int timeSelectPosition, String timeSelectName, long daySelectPosition, BeauticianDetailBean beauticianDetailBean, DetailEnum detailEnum) {
        Intent intent = new Intent(context, ProjectDetailActivity.class);
        intent.putExtra(EXTRA_KEY_BEAUTICIAN_ID, beauticianId);
        intent.putExtra(EXTRA_KEY_TIME_SELECT, timeSelectPosition);
        intent.putExtra(EXTRA_KEY_TIME_NAME, timeSelectName);
        intent.putExtra(EXTRA_KEY_DAY_SELECT, daySelectPosition);
        intent.putExtra(EXTRA_KEY_BEAUTICIAN_DETAIL_BEAN, beauticianDetailBean);
        intent.putExtra(EXTRA_KEY_DETAIL_ENUM, detailEnum);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_project_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        beauticianId = super.getStringExtraSecure(EXTRA_KEY_BEAUTICIAN_ID);
        timeSelectPosition = super.getIntExtraSecure(EXTRA_KEY_TIME_SELECT);
        daySelectPosition = super.getLongExtraSecure(EXTRA_KEY_DAY_SELECT);
        timeSelectName = super.getStringExtraSecure(EXTRA_KEY_TIME_NAME);

        if ((DetailEnum) getIntent().getSerializableExtra(EXTRA_KEY_DETAIL_ENUM) == DetailEnum.SHOP) {
            shopDetailBean = (ShopDetailBean) getIntent().getSerializableExtra(EXTRA_KEY_SHOP_DETAIL_BEAN);
        } else if ((DetailEnum) getIntent().getSerializableExtra(EXTRA_KEY_DETAIL_ENUM) == DetailEnum.BEAUTICIAN) {
            beauticianDetailBean = (BeauticianDetailBean) getIntent().getSerializableExtra(EXTRA_KEY_BEAUTICIAN_DETAIL_BEAN);
        }


        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("项目详情");
        iv_share.setVisibility(View.VISIBLE);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtil.shareUm(ProjectDetailActivity.this,
                        detailBean.getName(),
                        detailBean.getContent(),
                        URLs.BASE_URL + detailBean.getImage(),
                        URLs.SHARE_PROJECT_URL + detailBean.getId());


            }
        });
        indicatorProjectDetail.setVisibility(View.VISIBLE);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        multipleStatusView.showOutContentView(scrollLayout);

        View contentHead = View.inflate(this, R.layout.layout_detail_content_head, null);
        View ll_address = contentHead.findViewById(R.id.ll_address);
        ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MapUtil.isBaiduMapInstalled() && !MapUtil.isGdMapInstalled() && !MapUtil.isTencentMapInstalled()) {
                    ToastUtil.showToastMsg(ProjectDetailActivity.this, "您还未安装三方地图应用，请安装");
                    return;
                }
                // TODO: 2018/12/10
                //这里的经纬度是直接获取的，在实际开发中从应用的地图中获取经纬度;

                AlertDialog.Builder builder = new AlertDialog.Builder(ProjectDetailActivity.this, R.style.Dialog);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.map_alert_dialog, null);//获取自定义布局
                builder.setView(layout);
                TextView baidu_map = layout.findViewById(R.id.baidu_map);
                TextView gaode_map = layout.findViewById(R.id.gaode_map);
                TextView tencent_map = layout.findViewById(R.id.tencent_map);
                Button btn_cancle = layout.findViewById(R.id.btn_cancle);
                View gaode_view = layout.findViewById(R.id.gaode_view);
                View baidu_view = layout.findViewById(R.id.baidu_view);
                if (!MapUtil.isBaiduMapInstalled()) {
                    baidu_map.setVisibility(View.GONE);
                    baidu_view.setVisibility(View.GONE);
                }
                if (!MapUtil.isGdMapInstalled()) {
                    gaode_map.setVisibility(View.GONE);
                    gaode_view.setVisibility(View.GONE);
                }
                if (!MapUtil.isTencentMapInstalled()) {
                    tencent_map.setVisibility(View.GONE);
                    baidu_view.setVisibility(View.GONE);
                }
                final AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                baidu_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapUtil.openBaiDuNavi(ProjectDetailActivity.this, 0, 0, null, latx, laty, mAddress);
                        dialog.dismiss();
                    }
                });
                gaode_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapUtil.openGaoDeNavi(ProjectDetailActivity.this, 0, 0, null, latx, laty, mAddress);
                        dialog.dismiss();

                    }
                });
                tencent_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapUtil.openTencentMap(ProjectDetailActivity.this, 0, 0, null, latx, laty, mAddress);
                        dialog.dismiss();

                    }
                });
                btn_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }
        });
        initContentHead(contentHead);

        View webLayout = View.inflate(this, R.layout.layout_detail_content_web, null);
        webContainer = webLayout.findViewById(R.id.webContainer);

        commentSectionView = View.inflate(this, R.layout.layout_comment_top_head, null);
        initCommentView(commentSectionView);

        View contentSectionView = View.inflate(this, R.layout.layout_shop_content_section_view, null);
        initContentSectionView(contentSectionView);

        contentView.addView(contentHead);
        contentView.addView(webLayout);
        contentView.addView(commentSectionView);
        contentView.addView(contentSectionView);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void displayForWebView(String html, String baseUrl) {
        webContainer.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webContainer.getSettings().setDefaultFontSize(13);
        webContainer.getSettings().setJavaScriptEnabled(true);
        webContainer.getSettings().setBuiltInZoomControls(true);
        webContainer.getSettings().setDisplayZoomControls(false);
        webContainer.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        /*if (StringUtils.isEmpty(baseUrl)) {
            baseUrl = "about:blank";
        }
        webContainer.loadDataWithBaseURL(baseUrl, html, mimeType, encoding, "about:blank");*/
        webContainer.loadUrl(html);
        webContainer.scrollTo(0, 0);
    }

    private void initCommentView(View view) {
        userCommentNum = view.findViewById(R.id.userCommentNum);
        commentTabAll = view.findViewById(R.id.commentTabAll);
        commentTabNew = view.findViewById(R.id.commentTabNew);
        commentTabPhoto = view.findViewById(R.id.commentTabPhoto);
        tvCommentPeer = view.findViewById(R.id.tvCommentPeer);
        starNum = view.findViewById(R.id.starNum);
        starBar = view.findViewById(R.id.starBar);
        markLayout = view.findViewById(R.id.markLayout);
        markLayout.setVisibility(View.VISIBLE);
        commentTagFlowLayout = view.findViewById(R.id.commentTagFlowLayout);
        rvCommentList = view.findViewById(R.id.rvCommentList);
        commentTabAll.setSelected(true);
        commentTabAll.setOnClickListener(this);
        commentTabNew.setOnClickListener(this);
        commentTabPhoto.setOnClickListener(this);
        commentAdapter = new EvaluateListAdapter(this, false);
        rvCommentList.setLayoutManager(new LinearLayoutManager(this));
        rvCommentList.setHasFixedSize(true);
        rvCommentList.setNestedScrollingEnabled(false);
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        rvCommentList.setAdapter(commentAdapter);
        TextView lookMore = view.findViewById(R.id.lookMore);
        lookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEvaluateActivity.start(ProjectDetailActivity.this, false, "1", "1", "1");
            }
        });
        view.findViewById(R.id.layoutUserComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEvaluateActivity.start(ProjectDetailActivity.this, false,
                        "", "", "");
            }
        });
    }

    private void initContentSectionView(View view) {
        hotProjectRv = view.findViewById(R.id.sectionRv);
        hotProjectRv.setLayoutManager(new GridLayoutManager(this, 2));
        hotProjectRv.setHasFixedSize(true);
        hotProjectRv.setNestedScrollingEnabled(false);
        contentSectionAdapter = new MyGridAdapter(this, MyGridAdapter.HotdataBean);
        hotProjectRv.setAdapter(contentSectionAdapter);
        view.findViewById(R.id.lookMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectListActivity.start(ProjectDetailActivity.this, "2");
            }
        });
        view.findViewById(R.id.tvHotProject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectListActivity.start(ProjectDetailActivity.this, "2");
            }
        });
    }

    private TextView tradingArea;
    private TextView serviceOne;
    private TextView serviceTwo;
    private TextView serviceThree;
    private TextView tvAddress;
    private TextView tvDistance;
    private TextView coupon;
    private TextView subtract;
    private TextView redBao;

    private void initContentHead(View contentHead) {
        tradingArea = contentHead.findViewById(R.id.tradingArea);
        tradingArea.setOnClickListener(this);
        serviceOne = contentHead.findViewById(R.id.serviceOne);
        serviceTwo = contentHead.findViewById(R.id.serviceTwo);
        serviceThree = contentHead.findViewById(R.id.serviceThree);
        tvAddress = contentHead.findViewById(R.id.tvAddress);
        tvDistance = contentHead.findViewById(R.id.tvDistance);
        coupon = contentHead.findViewById(R.id.coupon);
        coupon.setOnClickListener(this);
        subtract = contentHead.findViewById(R.id.subtract);
        subtract.setOnClickListener(this);
        redBao = contentHead.findViewById(R.id.redBao);
        redBao.setOnClickListener(this);
        ll_yuyue = contentHead.findViewById(R.id.ll_yuyue);
        ll_yuyue.setOnClickListener(this);

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
                        view.setBackground(ContextCompat.getDrawable(ProjectDetailActivity.this, R.drawable.btn_white_background));
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

    @OnClick({R.id.projectDetail, R.id.userEvaluate, R.id.tv_buy_now, R.id.queding, R.id.tvCollection, R.id.tvToShopDetail, R.id.onLineService})
    public void onClick(View view) {
        long userId = UserHelp.getUserId(this);

        switch (view.getId()) {
            case R.id.onLineService:
                if (detailBean != null && detailBean.getWaiter() != null) {
                    ChatActivity.start(this, detailBean.getWaiter());
                }
                break;
            case R.id.tvCollection:
                if (userId == -1L) {
                    LoginActivity.start(this);
                    return;
                }
                OkGo.<String>get(URLs.ADD_COLLECTION)
                        .params("user_id", userId)
                        .params("type", "1")
                        .params("project_id", detailBean.getId())
                        .execute(new StringCallback() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        ToastUtil.showToastMsg(ProjectDetailActivity.this, "关注失败");
                                        return;
                                    }
                                    ToastUtil.showToastMsg(ProjectDetailActivity.this, msg.toString());
                                    setTvCollectionLeft();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToastMsg(ProjectDetailActivity.this, "关注失败");
                            }
                        });
                break;
            case R.id.projectDetail:
                indicatorProjectDetail.setVisibility(View.VISIBLE);
                indicatorEvaluate.setVisibility(View.GONE);
                scrollTo(0);
                break;
            case R.id.userEvaluate:
                indicatorProjectDetail.setVisibility(View.GONE);
                indicatorEvaluate.setVisibility(View.VISIBLE);
                scrollTo(2);
                break;
            case R.id.commentTabAll:
                commentTabAll.setSelected(true);
                commentTabNew.setSelected(false);
                commentTabPhoto.setSelected(false);
                if (commentdata != null) {
                    commentAdapter.replaceData(commentdata.getAll());
                }
                break;
            case R.id.commentTabNew:
                commentTabAll.setSelected(false);
                commentTabNew.setSelected(true);
                commentTabPhoto.setSelected(false);
                if (commentdata != null) {
                    commentAdapter.replaceData(commentdata.getNewX());
                }
                break;
            case R.id.commentTabPhoto:
                commentTabAll.setSelected(false);
                commentTabNew.setSelected(false);
                commentTabPhoto.setSelected(true);
                if (commentdata != null) {
                    commentAdapter.replaceData(commentdata.getHaveimg());
                }
                break;
            case R.id.tv_buy_now:
                toPayActivity();
                break;
            case R.id.ll_yuyue:
                if (timeSelectPosition == -1 || daySelectPosition == -1L) {
                    llSelectTime.setVisibility(View.VISIBLE);
                    llDianpuTab.setVisibility(View.GONE);
                    layoutBottomBar.setVisibility(View.GONE);
                }
                break;
            case R.id.queding:
                llSelectTime.setVisibility(View.GONE);
                llDianpuTab.setVisibility(View.VISIBLE);
                layoutBottomBar.setVisibility(View.VISIBLE);
                break;
            case R.id.tvToShopDetail:
                if (detailBean != null) {
                    ShopActivity.start(this, detailBean.getNearshop().getId() + "", DetailEnum.SHOP);
                }
                break;
            case R.id.coupon:
                int tagId = (int) coupon.getTag();
                receiveCoupon(tagId);
                break;
            case R.id.subtract:
                int fullId = (int) subtract.getTag();
                receiveFull(fullId);
                break;
            case R.id.redBao:
                int redBaoId = (int) redBao.getTag();
                receiveRedBao(redBaoId);
                break;
            case R.id.tradingArea:
                if (CollectionUtils.isEmpty(tradingBeanList)) {
                    ToastUtil.showToastMsg(this, "暂无商圈");
                    return;
                }
                new CommonDialog(this, tradingBeanList, new CommonDialog.ItemSelectedClickListener() {
                    @Override
                    public void onSelected(List<String> items) {
                        if (CollectionUtils.isNotEmpty(items)) {
                            fillTradings(items);
                        }
                    }
                }).show();
                break;
        }
    }

    private List<TradingBean> tradingBeanList;

    private List<TradingBean> getTradingList(List<String> list) {
        List<TradingBean> tradingBeanList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            tradingBeanList.clear();
            for (String trading : list) {
                TradingBean tradingBean = new TradingBean(trading, false);
                tradingBeanList.add(tradingBean);
            }
        }
        return tradingBeanList;
    }

    private void toPayActivity() {
        YuYueActivityBean yuYueActivityBean = new YuYueActivityBean();

        yuYueActivityBean.setBeauticianDetailBean(beauticianDetailBean);
        yuYueActivityBean.setShopDetailBean(shopDetailBean);
        yuYueActivityBean.setTimeSelect(timeSelectPosition);
        yuYueActivityBean.setTimeSelectName(timeSelectName);
        yuYueActivityBean.setDateSelect(daySelectPosition);
        yuYueActivityBean.setOrderCount(1);
        yuYueActivityBean.setOrderName(detailBean.getName());
        yuYueActivityBean.setOrderIconUrl(detailBean.getImage());
        yuYueActivityBean.setOrderPrice(detailBean.getPrice_discount());
        yuYueActivityBean.setProject_id(detailBean.getId() + "");
        yuYueActivityBean.setFromDetail(true);
        yuYueActivityBean.setNearShopBean(detailBean.getNearshop());
        if (detailBean.getMyDenvelopes() != null) {
            yuYueActivityBean.setRed_id(detailBean.getMyDenvelopes().get(0).getRedenvelopes_id());
            yuYueActivityBean.setRed_text(detailBean.getMyDenvelopes().get(0).getRes().getPrice_reduction() + "");
        }
        if (detailBean.getMyCoupons() != null) {
            yuYueActivityBean.setCoupon_id(detailBean.getMyCoupons().get(0).getCoupon_id());
            yuYueActivityBean.setCoupon_text(detailBean.getMyCoupons().get(0).getCoupon().getPrice_reduction() + "");
        }

        PayActivity.start(ProjectDetailActivity.this, yuYueActivityBean);
    }

    /**
     * 领取红包
     *
     * @param redBaoId
     */
    private void receiveRedBao(int redBaoId) {
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.RED_ENVELOPES_RECEIVE)
                .params("id", redBaoId)
                .params("user_id", userId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
                        return;
                    }
                    ToastUtil.showToastMsg(ProjectDetailActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
            }
        });
    }

    /**
     * 领取满减券
     *
     * @param fullId
     */
    private void receiveFull(int fullId) {
        String projectId = detailBean.getId();
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.COUPON_RECEIVE).params("coupon_type", "1")
                .params("project_id", projectId).params("id", fullId)
                .params("shop_id", "").params("beautician_id", beauticianId)
                .params("user_id", userId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
                        return;
                    }
                    ToastUtil.showToastMsg(ProjectDetailActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
            }
        });
    }

    /**
     * 领取优惠券
     *
     * @param couponId
     */
    private void receiveCoupon(int couponId) {
        String projectId = detailBean.getId();
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.COUPON_RECEIVE).params("coupon_type", "1")
                .params("project_id", projectId).params("id", couponId)
                .params("shop_id", "").params("beautician_id", beauticianId)
                .params("user_id", userId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
                        return;
                    }
                    ToastUtil.showToastMsg(ProjectDetailActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.showToastMsg(ProjectDetailActivity.this, "领取失败");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTvCollectionLeft() {
        tvCollection.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        tvCollection.setText("已收藏");
        tvCollection.setClickable(false);
    }

    private void scrollTo(final int position) {
        final View view = contentView.getChildAt(position);
        if (view == null) {
            return;
        }
        scrollLayout.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                //mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int top = 0;
                if (position == 2) {
                    top = view.getTop() + shop_detail_icon.getHeight() + contentView.getChildAt(1).getHeight();
                    if (top < 0) {
                        top = 0;
                    }
                }
                scrollLayout.smoothScrollTo(0, top);
            }
        });
    }

    /**
     * 预约时间
     */
    public void timeData(long timedstartdate) {
        OkGo.<String>post(URLs.APPOINTMENT_TIME_DATA).params("beautician", beauticianId)
                .params("startdate", (timedstartdate+"").substring(0,10)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                YuYueDataBean yuYueDataBean = gson.fromJson(response.body(), YuYueDataBean.class);
                yuYueDataListAdapter.setNewData(yuYueDataBean.getData());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        multipleStatusView.showLoading();
        long userId = UserHelp.getUserId(getApplicationContext());
        OkGo.<String>post(URLs.PROJECT_DETAILS)
                .params("id", beauticianId)
                .params("user_id", userId == -1L ? "" : userId + "")
                .params("longitude", SPUtils.getString(getApplicationContext(), "longitude", ""))
                .params("latitude", SPUtils.getString(getApplicationContext(), "latitude", ""))
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @SuppressLint("StringFormatMatches")
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("ProjectDetailActivity", response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            String data = jsonObject.optString("data");
                            if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                                multipleStatusView.showEmpty();
                                return;
                            }
                            multipleStatusView.showOutContentView(scrollLayout);
                            Gson gson = new Gson();
                            detailBean = gson.fromJson(data, ProjectDetailBean.class);
                            ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + detailBean.getImage(), shop_detail_icon);
                            shopName.setText(detailBean.getName());
                            price.setText("￥" + detailBean.getPrice_discount());
                            marketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                            marketPrice.getPaint().setAntiAlias(true);// 抗锯齿
                            marketPrice.setText("￥" + detailBean.getPrice_market());
                            tvFollowNum.setText("已有" + detailBean.getCollection_count() + "人关注");
                            tvAddress.setText(shopDetailBean == null ? "" : shopDetailBean.getAddress());
                            if (detailBean.getIscollection() == 1) {
                                setTvCollectionLeft();
                            }
                            if (shopDetailBean != null && CollectionUtils.isNotEmpty(shopDetailBean.getRedenvelopes())) {
                                tvRedBao.setText(shopDetailBean.getRedenvelopes().get(0).getName());
                                latx = Double.parseDouble(shopDetailBean.getLatitude());
                                laty = Double.parseDouble(shopDetailBean.getLongitude());
                                mAddress = shopDetailBean.getAddress();
                            } else {
                                ivRedBao.setVisibility(View.GONE);
                                tvRedBao.setVisibility(View.GONE);
                            }
                            if (shopDetailBean != null && CollectionUtils.isNotEmpty(shopDetailBean.getFull())) {
                                tvFul.setText(shopDetailBean.getFull().get(0).getName());
                            } else {
                                ivFull.setVisibility(View.GONE);
                                tvFul.setVisibility(View.GONE);
                            }
                            tvDistance.setText(getString(R.string.shop_detail_distance, detailBean.getNearshop().getDistance()));
                            if (detailBean != null && CollectionUtils.isNotEmpty(detailBean.getRedenvloesdata())) {
                                for (ProjectDetailBean.RedenvloesDataBean redenvloesDataBean : detailBean.getRedenvloesdata()) {
                                    if (redenvloesDataBean.getType() == 1) {
                                        if (StringUtils.isNotEmpty(redenvloesDataBean.getName())) {
                                            redBao.setVisibility(View.VISIBLE);
                                            redBao.setTag(redenvloesDataBean.getId());
                                            redBao.setText(String.valueOf(redenvloesDataBean.getName()));
                                        }
                                    }
                                    if (redenvloesDataBean.getType() == 2) {
                                        if (StringUtils.isNotEmpty(redenvloesDataBean.getName())) {
                                            subtract.setTag(redenvloesDataBean.getId());
                                            subtract.setText(String.valueOf(redenvloesDataBean.getName()));
                                        }
                                    }
                                    if (redenvloesDataBean.getType() == 3) {
                                        if (StringUtils.isNotEmpty(redenvloesDataBean.getName())) {
                                            coupon.setVisibility(View.VISIBLE);
                                            coupon.setTag(redenvloesDataBean.getId());
                                            coupon.setText(String.valueOf(redenvloesDataBean.getName()));
                                        }
                                    }
                                }
                            }
                            if (detailBean != null && CollectionUtils.isNotEmpty(detailBean.getStrading())) {
                                tradingBeanList = getTradingList(detailBean.getStrading());
                                fillTradings(detailBean.getStrading());
                            }
                            if (detailBean != null && CollectionUtils.isNotEmpty(detailBean.getService())) {
                                List<String> serviceList = detailBean.getService();
                                if (serviceList.get(0) != null) {
                                    serviceOne.setVisibility(View.VISIBLE);
                                    serviceOne.setText(serviceList.get(0));
                                } else {
                                    serviceOne.setVisibility(View.GONE);
                                }
                                if (serviceList.get(1) != null) {
                                    serviceTwo.setVisibility(View.VISIBLE);
                                    serviceTwo.setText(serviceList.get(1));
                                } else {
                                    serviceTwo.setVisibility(View.GONE);
                                }
                                if (serviceList.get(2) != null) {
                                    serviceThree.setVisibility(View.VISIBLE);
                                    serviceThree.setText(serviceList.get(2));
                                } else {
                                    serviceThree.setVisibility(View.GONE);
                                }
                            }
                            displayForWebView(URLs.BASE_URL + "/mob/project/details?id=" + beauticianId, null);
                            if (detailBean != null && CollectionUtils.isNotEmpty(detailBean.getHotdata())) {
                                contentSectionAdapter.replaceData(detailBean.getHotdata());
                                contentSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        LogUtil.e("section", "click:" + position);
                                        BaseBean item = contentSectionAdapter.getItem(position);
                                        ProjectDetailActivity.start(ProjectDetailActivity.this, item.getId());
                                    }
                                });
                            }
                            if (detailBean != null && detailBean.getComment() != null && CollectionUtils.isNotEmpty(detailBean.getComment().getAll())) {
                                fillCommentData(detailBean.getComment());
                            } else {
                                commentSectionView.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        multipleStatusView.showError();
                    }
                });

        initDayTime("");

        initDatePicker(this);
    }

    private void fillCommentData(CommentdataBean commentData) {
        commentdata = commentData;
        commentSectionView.setVisibility(View.VISIBLE);
        starNum.setText(String.valueOf(commentData.getStart()));
        starBar.setStarMark(commentData.getStart());
        userCommentNum.setText(getString(R.string.home_comment_num_format, commentData.getCount()));
        tvCommentPeer.setText(getString(R.string.home_comment_peer_format, commentData.getPeer() + "%", commentData.getSatisfaction() + "%"));
        commentAdapter.replaceData(commentData.getAll());
        if (CollectionUtils.isNotEmpty(commentData.getTags())) {
            fillTags(commentData.getTags());
        }
    }

    private void fillTradings(List<String> strading) {
        StringBuilder tradingText = new StringBuilder();
        if (strading.size() >= 2) {
            tradingText.append(strading.get(0)).append("    ").append(strading.get(1)).append("等");
        } else {
            tradingText.append(strading.get(0));
        }
        tradingArea.setText(tradingText.toString());
    }

    /**
     * 填充评价标识
     *
     * @param result
     */
    private void fillTags(final List<CommentTagsBean> result) {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        commentTagFlowLayout.removeAllViews();
        for (int i = 0; i < result.size(); i++) {
            final TextView txt = (TextView) LayoutInflater.from(this).inflate(R.layout.item_comment_tag_layout, commentTagFlowLayout, false);
            if (!AppUtil.isEmptyString(result.get(i).getName())) {
                txt.setText(result.get(i).getName());
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = txt.getText().toString();
                    }
                });
                commentTagFlowLayout.addView(txt);
            }
        }
    }

    private ArrayList<Calendar> calList = new ArrayList<>();

    private void initDayTime(String time) {
        final CanlBean canlBean = new CanlBean();

        calList.clear();
        for (int i = 0; i < 3; i++) {
            cal = Calendar.getInstance();
            if (time != null && !time.isEmpty() && "" != null) {
                if ("" != time && time != null) {
                    String[] year = time.split(" ")[0].split("-");
                    int integer = Integer.parseInt(year[0]);
                    int integer1 = Integer.parseInt(year[1]);
                    int integer2 = Integer.parseInt(year[2]);

                    int month = integer1 == 1 ? 12 : integer1 - 1;
                    integer = integer1 == 1 ? integer - 1 : integer;
                    cal.set(integer, month, integer2);
                    cal.add(Calendar.DATE, i);
                    calList.add(cal);
                }
            } else {
                cal.add(Calendar.DATE, i);
                calList.add(cal);
            }
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
        id_recyclerview_horizontal.setLayoutManager(linearLayoutManager);
        dayAdapter = new DayCheckAdapter2(this, canlBean.getDataBean(), beauticianId);
        id_recyclerview_horizontal.setAdapter(dayAdapter);

        dayAdapter.setOnItemClickLitener(new DayCheckAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                long timeInMillis1 = canlBean.getDataBean().get(position).getTimeInMillis();
                String format = String.format("%010d", timeInMillis1);
                timeData(Long.valueOf(format));
                daySelectPosition = Long.valueOf(format);
            }
        });
    }

    private CustomDatePicker startTimePicker;

    /*
     *初始化时间选择器
     */
    protected void initDatePicker(final Context mContext) {
        startTimePicker = new CustomDatePicker(mContext, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                ToastUtil.showToastMsg(mContext, time);

                long currentTimeMillis = System.currentTimeMillis();
                String formatTime = AppUtil.getFormatTime(currentTimeMillis);

                String timers = formatTime.split(" ")[0];
                String timers1 = time.split(" ")[0];
                if (AppUtil.timeToStamp(timers1) < AppUtil.timeToStamp(timers)) {
                    ToastUtil.showToastMsg(mContext, "时间不可选");
                } else {
                    initDayTime(time);
                }
            }
        }, "1950-01-01 00:00", "2050-01-01 00:00", "请设置开始时间"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startTimePicker.showSpecificTime(false); // 显示时和分
        startTimePicker.setIsLoop(true); // 允许循环滚动
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.show_select_time, R.id.queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_select_time:
                startTimePicker.show(AppUtil.getFormatTime(System.currentTimeMillis()));
                break;
            case R.id.queding:
                llSelectTime.setVisibility(View.GONE);
                llDianpuTab.setVisibility(View.VISIBLE);
                layoutBottomBar.setVisibility(View.VISIBLE);
                break;
        }
    }
}
