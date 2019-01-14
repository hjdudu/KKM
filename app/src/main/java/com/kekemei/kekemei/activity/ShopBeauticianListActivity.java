package com.kekemei.kekemei.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.MeiRongShiListAdapter;
import com.kekemei.kekemei.adapter.ShopListAdapter;
import com.kekemei.kekemei.bean.BannerBean;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.MeiRongShiListBean;
import com.kekemei.kekemei.bean.ShopBean;
import com.kekemei.kekemei.bean.ShopListBean;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.SPUtils;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.view.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

public class ShopBeauticianListActivity extends BaseActivity {
    private static final String EXTRA_KEY_SHOW_SHOP = "showShop";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private XBanner xbanner;
    @BindView(R.id.rv_list)
    RecyclerView jRecyclerView;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout jSwipeRefreshLayout;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private ShopListAdapter shopListAdapter;
    private MeiRongShiListAdapter beauticianListAdapter;

    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;

    private String latitude, longitude;
    private boolean showShopList;

    public static void start(Activity context, boolean showShopList) {
        Intent intent = new Intent(context, ShopBeauticianListActivity.class);
        intent.putExtra(EXTRA_KEY_SHOW_SHOP, showShopList);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_beautician_list;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        showShopList = getIntent().getBooleanExtra(EXTRA_KEY_SHOW_SHOP, false);
        tvTitle.setText(showShopList ? "店铺" : "美容师");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        latitude = SPUtils.getString(this, "latitude", "");
        longitude = SPUtils.getString(this, "longitude", "");

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
        multipleStatusView.showOutContentView(jSwipeRefreshLayout);

        jSwipeRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        jSwipeRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData(true);
            }
        });

        jRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        jRecyclerView.setHasFixedSize(true);
        jRecyclerView.setNestedScrollingEnabled(false);

        View header = LayoutInflater.from(this).inflate(R.layout.layout_xbanner_header, null);
        xbanner = header.findViewById(R.id.xbanner);

        if (showShopList) {
            shopListAdapter = new ShopListAdapter(ShopBeauticianListActivity.this, R.layout.list_shop);
            jRecyclerView.setAdapter(shopListAdapter);
            shopListAdapter.addHeaderView(header);
            shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopBean item = shopListAdapter.getItem(position);
                    ShopActivity.start(ShopBeauticianListActivity.this, item.getId(), DetailEnum.SHOP);
                }
            });
            shopListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopBean item = shopListAdapter.getItem(position);
                    ShopActivity.start(ShopBeauticianListActivity.this, item.getId(), DetailEnum.SHOP);
                }
            });
        } else {
            beauticianListAdapter = new MeiRongShiListAdapter(ShopBeauticianListActivity.this, R.layout.list_meirongshi);
            jRecyclerView.setAdapter(beauticianListAdapter);
            beauticianListAdapter.addHeaderView(header);
            beauticianListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    BeauticianBean beauticianBean = beauticianListAdapter.getItem(position);
                    ShopActivity.start(ShopBeauticianListActivity.this, beauticianBean.getId(), DetailEnum.BEAUTICIAN);
                }
            });
        }

    }

    @Override
    protected void initData() {
        multipleStatusView.showLoading();
        loadData(true);
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        isLoadMore = false;
        if (isRefresh) {
            jPageNum = 1;
            showRefreshLoading(isRefresh);
        }
        getData(jPageNum);
    }

    private void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        getData(jPageNum);
    }

    public void getData(int pageNum) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        OkGo.<String>get(showShopList ? URLs.SHOP_NEAR : URLs.BEAUTICIAN_NEAR).params("longitude", longitude)
                .params("latitude", latitude).params("page", pageNum).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.d("ShopListActivity", response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    String data = jsonObject.optString("data");
                    if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                        jSwipeRefreshLayout.finishLoadMore();
                        if (showShopList) {
                            multipleStatusView.showEmpty();
                        } else {
                            multipleStatusView.showEmpty(R.mipmap.no_beautician);
                        }
                        return;
                    }
                    Gson gson = new Gson();
                    if (showShopList) {
                        ShopListBean shopListBean = gson.fromJson(data, ShopListBean.class);
                        if (isRefresh) {
                            xbanner.setData(shopListBean.getBanner(), null);
                            initBanner();
                        }
                        onResultSuccess(shopListBean);
                    } else {
                        MeiRongShiListBean meiRongShiListBean = gson.fromJson(data, MeiRongShiListBean.class);
                        if (isRefresh) {
                            xbanner.setData(meiRongShiListBean.getBanner(), null);
                            initBanner();
                        }
                        onResultSuccess(meiRongShiListBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                onResultError();
            }
        });
    }

    private void onResultSuccess(Object response) {
        if (!isLoadMore) {
            jPageNum++;
            if (showShopList) {
                ShopListBean shopListBean = (ShopListBean) response;
                if (null == response || null == shopListBean.getData()) {
                    showEmpty();
                }
                fillData(shopListBean.getData());
            } else {
                MeiRongShiListBean meiRongShiListBean = (MeiRongShiListBean) response;
                if (null == response || null == meiRongShiListBean.getData()) {
                    showEmpty();
                }
                fillBeauticianData(meiRongShiListBean.getData());
            }
        } else {
            jPageNum++;
            if (showShopList) {
                ShopListBean shopListBean = (ShopListBean) response;
                if (null == response || null == shopListBean.getData()) {
                    jSwipeRefreshLayout.finishLoadMore();
                    return;
                }
                loadMoreSuccess(shopListBean.getData());
            } else {
                MeiRongShiListBean meiRongShiListBean = (MeiRongShiListBean) response;
                if (null == response || null == meiRongShiListBean.getData()) {
                    jSwipeRefreshLayout.finishLoadMore();
                    return;
                }
                loadMoreBeauticianSuccess(meiRongShiListBean.getData());
            }
        }
    }

    private void loadMoreBeauticianSuccess(List<BeauticianBean> dataList) {
        jSwipeRefreshLayout.finishLoadMore();
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        beauticianListAdapter.addData(dataList);
        if (dataList.size() < 10) {
            showLoadMoreEnd();
        } else {
            showLoadMoreComplete();
        }
    }

    private void fillBeauticianData(List<BeauticianBean> listData) {
        if (listData.size() == 0) {
            showEmpty();
        } else {
            if (isRefresh)
                showRefreshLoading(false);
            showBeauticianData(listData);
        }
        if (listData.size() < 10)
            showLoadMoreEnd();
        else
            showLoadMoreComplete();
    }

    private void showBeauticianData(List<BeauticianBean> listData) {
        if (isOnDestroy)
            return;
        multipleStatusView.showOutContentView(jSwipeRefreshLayout);
        beauticianListAdapter.replaceData(listData);
        jRecyclerView.scrollToPosition(0);
    }

    private void fillData(List<ShopBean> listData) {
        if (listData.size() == 0) {
            showEmpty();
        } else {
            if (isRefresh)
                showRefreshLoading(false);
            showData(listData);
        }
        if (listData.size() < 10)
            showLoadMoreEnd();
        else
            showLoadMoreComplete();
    }

    private void onResultError() {
        if (!isRefresh && !isLoadMore)
            showError();
        if (isLoadMore)
            showLoadMoreFailed();
        if (isRefresh)
            showRefreshLoading(false);
    }

    private void showData(List<ShopBean> dataList) {
        if (isOnDestroy)
            return;
        multipleStatusView.showOutContentView(jSwipeRefreshLayout);
        shopListAdapter.replaceData(dataList);
        jRecyclerView.scrollToPosition(0);
    }

    private void loadMoreSuccess(List<ShopBean> dataList) {
        jSwipeRefreshLayout.finishLoadMore();
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        shopListAdapter.addData(dataList);
        if (dataList.size() < 10) {
            showLoadMoreEnd();
        } else {
            showLoadMoreComplete();
        }
    }

    private void showEmpty() {
        multipleStatusView.showEmpty();
    }

    private void showError() {
        multipleStatusView.showError();
    }

    private void showLoadMoreFailed() {
        if (showShopList) {
            shopListAdapter.loadMoreFail();
        } else {
            beauticianListAdapter.loadMoreFail();
        }
    }

    private void showLoadMoreEnd() {
        jSwipeRefreshLayout.setEnableLoadMore(false);
        if (showShopList) {
            addCantLoadMoreFooter(shopListAdapter);
            shopListAdapter.loadMoreEnd(false);
        } else {
            addCantLoadMoreFooter(beauticianListAdapter);
            beauticianListAdapter.loadMoreEnd(false);
        }
    }

    private void showLoadMoreComplete() {
        if (showShopList) {
            shopListAdapter.loadMoreComplete();
        } else {
            beauticianListAdapter.loadMoreComplete();
        }
    }

    private void showRefreshLoading(boolean show) {
        if (show) {
            jSwipeRefreshLayout.refreshDrawableState();
        } else {
            jSwipeRefreshLayout.finishRefresh();
        }
    }

    private boolean isOnDestroy = false;

    @Override
    public void onDestroy() {
        isOnDestroy = true;
        super.onDestroy();
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
                        ShopActivity.start(ShopBeauticianListActivity.this, bannerBean.getShop_shop_id() + "", DetailEnum.SHOP);
                        break;
                    case "project":
                        ProjectDetailActivity.start(ShopBeauticianListActivity.this, bannerBean.getProject_project_id() + "");
                        break;
                    case "web":
                        WebActivity.start(ShopBeauticianListActivity.this, bannerBean.getUrl());
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

    private View footer;

    private void addCantLoadMoreFooter(BaseQuickAdapter adapter) {
        if (footer == null) {
            footer = LayoutInflater.from(this).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }
}
