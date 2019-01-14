package com.kekemei.kekemei.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.ClassifyActivity;
import com.kekemei.kekemei.activity.LoginActivity;
import com.kekemei.kekemei.activity.MessageActivity;
import com.kekemei.kekemei.activity.MiaoshaActivity;
import com.kekemei.kekemei.activity.NewComerActivity;
import com.kekemei.kekemei.activity.ProjectDetailActivity;
import com.kekemei.kekemei.activity.ProjectListActivity;
import com.kekemei.kekemei.activity.SearchActivity;
import com.kekemei.kekemei.activity.ShopActivity;
import com.kekemei.kekemei.activity.ShopBeauticianListActivity;
import com.kekemei.kekemei.activity.UserEvaluateActivity;
import com.kekemei.kekemei.activity.WebActivity;
import com.kekemei.kekemei.adapter.DAVipAdapter;
import com.kekemei.kekemei.adapter.EvaluateListAdapter;
import com.kekemei.kekemei.adapter.MeiRongShiAdapter;
import com.kekemei.kekemei.adapter.MyGridAdapter;
import com.kekemei.kekemei.bean.BannerBean;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.CommentTagsBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.HomeBean;
import com.kekemei.kekemei.bean.ShopBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.SPUtils;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.MultipleStatusView;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements AMapLocationListener {
    @BindView(R.id.iv_place)
    ImageView ivPlace;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.text_msg)
    TextView textMsg;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.id_msg)
    LinearLayout idMsg;
    @BindView(R.id.xbanner)
    XBanner xbanner;

    @BindView(R.id.guideCoupon)
    ImageView guideCoupon;
    @BindView(R.id.couponOne)
    RelativeLayout couponOne;
    @BindView(R.id.nameOne)
    TextView nameOne;
    @BindView(R.id.priceOne)
    TextView priceOne;
    @BindView(R.id.prefixOne)
    TextView prefixOne;
    @BindView(R.id.receiveOne)
    TextView receiveOne;

    @BindView(R.id.couponTwo)
    RelativeLayout couponTwo;
    @BindView(R.id.nameTwo)
    TextView nameTwo;
    @BindView(R.id.priceTwo)
    TextView priceTwo;
    @BindView(R.id.prefixTwo)
    TextView prefixTwo;
    @BindView(R.id.receiveTwo)
    TextView receiveTwo;

    @BindView(R.id.couponThree)
    RelativeLayout couponThree;
    @BindView(R.id.nameThree)
    TextView nameThree;
    @BindView(R.id.priceThree)
    TextView priceThree;
    @BindView(R.id.prefixThree)
    TextView prefixThree;
    @BindView(R.id.receiveThree)
    TextView receiveThree;

    @BindView(R.id.rv_meirongshi)
    RecyclerView rvMeirongshi;
    @BindView(R.id.rv_davip_kkm)
    RecyclerView rvDavipKkm;
    @BindView(R.id.rv_xinren)
    RecyclerView rvXinren;
    @BindView(R.id.rv_huiyuan)
    RecyclerView rvHuiyuan;
    @BindView(R.id.rv_zuixinxiangmu)
    RecyclerView rvZuixinxiangmu;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.rv_remenxiangmu)
    RecyclerView rvRemenxiangmu;
    @BindView(R.id.fujin_meirongshi)
    TextView fujinMeirongshi;
    @BindView(R.id.fujin_dianpu)
    LinearLayout fujinDianpu;
    @BindView(R.id.ll_meirong)
    LinearLayout llMeirong;
    @BindView(R.id.ll_meiti)
    LinearLayout llMeiti;
    @BindView(R.id.ll_yangsheng)
    LinearLayout llYangsheng;
    @BindView(R.id.ll_qita)
    LinearLayout llQita;
    @BindView(R.id.commentLayout)
    LinearLayout commentLayout;
    @BindView(R.id.userCommentNum)
    TextView userCommentNum;
    @BindView(R.id.commentTabAll)
    TextView commentTabAll;
    @BindView(R.id.commentTabNew)
    TextView commentTabNew;
    @BindView(R.id.commentTabPhoto)
    TextView commentTabPhoto;
    @BindView(R.id.layoutUserComment)
    RelativeLayout layoutUserComment;
    @BindView(R.id.markLayout)
    LinearLayout markLayout;
    @BindView(R.id.lookMore)
    TextView lookMore;
    @BindView(R.id.ivNewComer)
    ImageView ivNewComer;
    @BindView(R.id.ivSecond)
    ImageView ivSecond;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.sc_all)
    ScrollView scAll;
    @BindView(R.id.tvCommentPeer)
    TextView tvCommentPeer;
    @BindView(R.id.main_srl)
    SwipeRefreshLayout swipeRefreshLayout;
    private HomeBean.CommentdataBean commentdata;
    private EvaluateListAdapter commentAdapter;

    @BindView(R.id.commentTagFlowLayout)
    FlexboxLayout commentTagFlowLayout;
    @BindView(R.id.rvCommentList)
    RecyclerView rvCommentList;
    private Unbinder unbinder;

    //申明对象
    CityPickerView mPicker = new CityPickerView();
    private MeiRongShiAdapter meiRongShiAdapter;
    private DAVipAdapter adapter_vip;
    private MyGridAdapter adapter1;
    private MyGridAdapter adapter4;
    private MyGridAdapter adapter2;
    private MyGridAdapter adapter3;
    private String latitude;
    private String longitude;
    private HomeBean homeBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);
        latitude = SPUtils.getString(getActivity(), "latitude", "");
        longitude = SPUtils.getString(getActivity(), "longitude", "");
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mPicker.init(getActivity());
        initView();
        initCityList();
        final String city = SPUtils.getString(getActivity(), "city", "");
        if (StringUtils.isNotEmpty(latitude) && StringUtils.isNotEmpty(longitude)) {
            initData(latitude, longitude);
            place.setText(city);
        } else {
            AppUtil.getUserPoint(getActivity(), this);
        }

        commentTabAll.setSelected(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (StringUtils.isNotEmpty(latitude) && StringUtils.isNotEmpty(longitude)) {
                    initData(latitude, longitude);
                    place.setText(city);
                }
            }
        });
        return view;
    }

    private void initView() {
        LinearLayoutManager layout_meirongshi = new LinearLayoutManager(getActivity().getBaseContext());
        layout_meirongshi.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMeirongshi.setHasFixedSize(true);
        rvMeirongshi.setNestedScrollingEnabled(false);
        rvMeirongshi.setLayoutManager(layout_meirongshi);
        meiRongShiAdapter = new MeiRongShiAdapter(getActivity());
        rvMeirongshi.setAdapter(meiRongShiAdapter);
        meiRongShiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BeauticianBean data = meiRongShiAdapter.getItem(position);
                ShopActivity.start(getActivity(), data.getId(), DetailEnum.BEAUTICIAN);
            }
        });


        LinearLayoutManager layout_vip = new LinearLayoutManager(getActivity().getBaseContext());
        layout_vip.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDavipKkm.setHasFixedSize(true);
        rvDavipKkm.setNestedScrollingEnabled(false);
        rvDavipKkm.setLayoutManager(layout_vip);
        adapter_vip = new DAVipAdapter(getActivity());
        rvDavipKkm.setAdapter(adapter_vip);
        adapter_vip.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopBean data = adapter_vip.getItem(position);
                ShopActivity.start(getActivity(), data.getId(), DetailEnum.SHOP);
            }
        });

        rvXinren.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        adapter1 = new MyGridAdapter(getActivity().getBaseContext(), MyGridAdapter.NewmemberdataBean);
        rvXinren.setHasFixedSize(true);
        rvXinren.setNestedScrollingEnabled(false);
        rvXinren.setAdapter(adapter1);

        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = adapter1.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());
            }
        });

        rvRemenxiangmu.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        adapter4 = new MyGridAdapter(getActivity().getBaseContext(), MyGridAdapter.HotdataBean);
        rvRemenxiangmu.setHasFixedSize(true);
        rvRemenxiangmu.setNestedScrollingEnabled(false);
        rvRemenxiangmu.setAdapter(adapter4);

        adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = adapter4.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());
            }
        });

        rvHuiyuan.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        adapter2 = new MyGridAdapter(getActivity().getBaseContext(), MyGridAdapter.MemberdataBean);
        rvHuiyuan.setHasFixedSize(true);
        rvHuiyuan.setNestedScrollingEnabled(false);
        rvHuiyuan.setAdapter(adapter2);

        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = adapter2.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());
            }
        });

        rvZuixinxiangmu.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        adapter3 = new MyGridAdapter(getActivity().getBaseContext(), MyGridAdapter.SpecialdataBean);
        rvZuixinxiangmu.setHasFixedSize(true);
        rvZuixinxiangmu.setNestedScrollingEnabled(false);
        rvZuixinxiangmu.setAdapter(adapter3);

        adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = adapter3.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());
            }
        });

        rvCommentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentAdapter = new EvaluateListAdapter(getActivity(), false);
        rvCommentList.setHasFixedSize(true);
        rvCommentList.setNestedScrollingEnabled(false);
        rvCommentList.setAdapter(commentAdapter);

    }

    private void initCityList() {

    }

    private void initData(String latitude, String longitude) {
        multipleStatusView.showLoading();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);//设置不刷新
        }

        long userId = UserHelp.getUserId(getActivity());
        OkGo.<String>post(URLs.INDEX).params("longitude", longitude).params("latitude", latitude)
                .params("user_id", userId == -1L ? "" : userId + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        LogUtil.d("APPLOCALTION", response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            String data = jsonObject.optString("data");
                            if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                                multipleStatusView.showEmpty();
                                return;
                            }
                            multipleStatusView.showOutContentView(scAll);
                            Gson gson = new Gson();
                            homeBean = gson.fromJson(data, HomeBean.class);
                            xbanner.setData(homeBean.getBanneradv(), null);
                            initBanner();
                            initCoupon(homeBean.getIndexcoupon());
                            meiRongShiAdapter.setNewData(homeBean.getBeautician());
                            adapter_vip.setNewData(homeBean.getShop());
                            adapter1.setNewData(homeBean.getNewmemberdata());
                            adapter2.setNewData(homeBean.getMemberdata());
                            adapter3.setNewData(homeBean.getSpecialdata());
                            adapter4.setNewData(homeBean.getHotdata());
                            if (homeBean.getCommentdata() != null && CollectionUtils.isNotEmpty(homeBean.getCommentdata().getAll())) {
                                commentLayout.setVisibility(View.VISIBLE);
                                userCommentNum.setText(getString(R.string.home_comment_num_format, homeBean.getCommentdata().getCount()));
                                commentdata = homeBean.getCommentdata();
                                commentAdapter.setNewData(homeBean.getCommentdata().getAll());
                                if (CollectionUtils.isNotEmpty(homeBean.getCommentdata().getTags())) {
                                    fillTags(homeBean.getCommentdata().getTags());
                                }
                            } else {
                                commentLayout.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        LogUtil.d("APPLOCALTION", response.toString());
                        multipleStatusView.showError();
                    }
                });
    }

    private boolean canReceiveCoupon = true;
    boolean oneCanUse = true, twoCanUse = true, threeCanUse = true;

    private void initCoupon(List<HomeBean.IndexCoupon> indexCouponList) {
        if (CollectionUtils.isNotEmpty(indexCouponList)) {
            for (int i = 0; i < indexCouponList.size(); i++) {
                if (i == 0) {
                    oneCanUse = !indexCouponList.get(i).getState().equals("1");
                    displayCoupon(indexCouponList.get(i), couponOne, nameOne, priceOne, prefixOne, receiveOne, oneCanUse);
                } else if (i == 1) {
                    twoCanUse = !indexCouponList.get(i).getState().equals("1");
                    displayCoupon(indexCouponList.get(i), couponTwo, nameTwo, priceTwo, prefixTwo, receiveTwo, twoCanUse);
                } else if (i == 2) {
                    threeCanUse = !indexCouponList.get(i).getState().equals("1");
                    displayCoupon(indexCouponList.get(i), couponThree, nameThree, priceThree, prefixThree, receiveThree, threeCanUse);
                }
            }
            canReceiveCoupon = (oneCanUse || twoCanUse || threeCanUse);
            guideCoupon.setImageResource(canReceiveCoupon ? R.mipmap.home_coupon_can_receive : R.mipmap.home_coupon_cant_receive);
        }
    }

    private void displayCoupon(HomeBean.IndexCoupon indexCoupon, View targetView, TextView targetName,
                               TextView targetPrice, TextView prefix, TextView receive, boolean canUse) {
        targetView.setTag(indexCoupon);
        targetPrice.setText(indexCoupon.getName());
        targetName.setText(String.valueOf(indexCoupon.getPrice_satisfy()));
        prefix.setTextColor(canUse ? Color.parseColor("#ffffff") : Color.parseColor("#7AD2D2"));
        targetName.setTextColor(canUse ? Color.parseColor("#ffffff") : Color.parseColor("#7AD2D2"));
        targetPrice.setTextColor(canUse ? Color.parseColor("#ffffff") : Color.parseColor("#7AD2D2"));
        receive.setVisibility(canUse ? View.GONE : View.VISIBLE);
        targetView.setBackgroundResource(canUse ? R.mipmap.home_coupon_can_receive_bg : R.drawable.shape_home_coupon_cant_receive);
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
            final TextView txt = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_tag_layout, commentTagFlowLayout, false);
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

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 初始化XBanner
     */
    private void initBanner() {
        //设置广告图片点击事件
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                //                Toast.makeText(MainActivity.this, "点击了第" + (position+1) + "图片", Toast.LENGTH_SHORT).show();
                BannerBean bannerBean = (BannerBean) model;
                switch (bannerBean.getJumbdata()) {
                    case "shop":
                        ShopActivity.start(getActivity(), bannerBean.getShop_shop_id() + "", DetailEnum.SHOP);
                        break;
                    case "project":
                        ProjectDetailActivity.start(getActivity(), bannerBean.getProject_project_id() + "");
                        break;
                    case "web":
                        WebActivity.start(getActivity(), bannerBean.getUrl());
                        break;
                    case "url":
                        break;
                }
            }
        });
        //加载广告图片
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
                //                Glide.with(MainActivity.this).load(((AdvertiseEntity.OthersBean) model).getThumbnail()).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
                ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + ((BannerBean) model).getImage(), (ImageView) view);
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                double latitude = amapLocation.getLatitude();//获取纬度
                double longitude = amapLocation.getLongitude();//获取经度
                SPUtils.putString(getActivity(), "latitude", String.valueOf(latitude));
                SPUtils.putString(getActivity(), "longitude", String.valueOf(longitude));
                String city = amapLocation.getCity();
                SPUtils.putString(getActivity(), "city", city);
                initData(String.valueOf(latitude), String.valueOf(longitude));
                place.setText(city);

                HttpParams commonParams = new HttpParams();
                commonParams.put("latitude", latitude);
                commonParams.put("longitude", longitude);
                OkGo.getInstance().init(getActivity().getApplication()).addCommonParams(commonParams);

                LogUtil.d("APPLOCALTION  HomeFragment", "LATITUDE : " + latitude + " --  LONGITUDE : " + longitude);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                LogUtil.d("APPLOCALTION  HomeFragment", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
            }
        }
    }

    Intent intent;

    @OnClick({R.id.ll_meirong, R.id.ll_meiti, R.id.ll_yangsheng, R.id.ll_qita, R.id.commentTabAll,
            R.id.commentTabNew, R.id.commentTabPhoto, R.id.fujin_meirongshi, R.id.fujin_dianpu, R.id.ll_search,
            R.id.iv_place, R.id.place, R.id.ivNewComer, R.id.ivSecond, R.id.couponOne, R.id.couponTwo,
            R.id.couponThree, R.id.id_msg, R.id.lookMore, R.id.layoutUserComment, R.id.iv_xinrenzhuanqu,
            R.id.iv_huiyuanzhuanqu, R.id.iv_zuixinxiangmu, R.id.iv_remenxiangmu})
    public void onViewClicked(View view) {
        intent = new Intent(getActivity(), ClassifyActivity.class);
        switch (view.getId()) {
            case R.id.iv_xinrenzhuanqu:
                NewComerActivity.start(getActivity(), true);
                break;
            case R.id.iv_huiyuanzhuanqu:
                NewComerActivity.start(getActivity(), false);
                break;
            case R.id.iv_zuixinxiangmu:
                ProjectListActivity.start(getActivity(), "3");
                break;
            case R.id.iv_remenxiangmu:
                ProjectListActivity.start(getActivity(), "2");
                break;
            case R.id.couponOne:
                receiveCoupon(couponOne);
                break;
            case R.id.couponTwo:
                receiveCoupon(couponTwo);
                break;
            case R.id.couponThree:
                receiveCoupon(couponThree);
                break;
            case R.id.ll_meirong:
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.ll_meiti:
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.ll_yangsheng:
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.ll_qita:
                intent.putExtra("type", 4);
                startActivity(intent);
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
            case R.id.ll_search:
                SearchActivity.start(getActivity());
                break;
            case R.id.fujin_dianpu:
                ShopBeauticianListActivity.start(getActivity(), true);
                break;
            case R.id.fujin_meirongshi:
                ShopBeauticianListActivity.start(getActivity(), false);
                break;
            case R.id.iv_place:
            case R.id.place:
                break;
            case R.id.ivNewComer:
                long userId = UserHelp.getUserId(getActivity());
                if (userId == -1L) {
                    LoginActivity.start(getActivity());
                    return;
                }
                NewComerActivity.start(getActivity(), true);
                break;
            case R.id.ivSecond:
                MiaoshaActivity.start(getActivity());
                break;
            case R.id.id_msg:
                MessageActivity.start(getActivity());
                break;
            case R.id.layoutUserComment:
            case R.id.lookMore:
                UserEvaluateActivity.start(getActivity(), false,
                        "", "", "");
                break;
        }
    }

    private void receiveCoupon(final View couponView) {

        long userId = UserHelp.getUserId(getActivity());
        if (userId == -1L) {
            LoginActivity.start(getActivity());
            return;
        }
        final HomeBean.IndexCoupon indexCoupon = (HomeBean.IndexCoupon) couponView.getTag();
        OkGo.<String>post(URLs.COUPON_RECEIVE).params("id", indexCoupon.getId())
                .params("coupon_type", "").params("project_id", "")
                .params("shop_id", "").params("beautician_id", "")
                .params("user_id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        LogUtil.d("APPLOCALTION", response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            if (msg.equals("暂无数据")) {
                                ToastUtil.showToastMsg(getActivity(), "您已经领取过");
                                return;
                            }
                            if (homeBean != null && CollectionUtils.isNotEmpty(homeBean.getIndexcoupon())) {
                                List<HomeBean.IndexCoupon> indexCouponList = homeBean.getIndexcoupon();
                                if (indexCouponList.contains(indexCoupon)) {
                                    int index = indexCouponList.indexOf(indexCoupon);
                                    indexCouponList.remove(indexCoupon);
                                    indexCoupon.setState("1");
                                    indexCouponList.add(index, indexCoupon);
                                    initCoupon(indexCouponList);
                                }
                            }
                            ToastUtil.showToastMsg(getActivity(), msg.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtil.showToastMsg(getActivity(), "领取失败");
                    }
                });
    }
}
