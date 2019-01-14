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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.SelectBeauticianAdapter;
import com.kekemei.kekemei.adapter.SelectShopAdapter;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.ShopBean;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

public class ShopBeauticianActivity extends BaseActivity {
    public static final String TAG = ShopBeauticianActivity.class.getSimpleName();
    private static final String EXTRA_KEY_ID = "Id";
    private static final String EXTRA_KEY_SHOW_SHOP = "showShop";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout jSwipeRefreshLayout;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private SelectBeauticianAdapter beauticianAdapter;
    private SelectShopAdapter shopAdapter;

    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;

    private String id;
    private boolean showShop;
    private String latitude, longitude;

    public static void start(Activity activity, String id, boolean showShop, int code) {
        Intent intent = new Intent(activity, ShopBeauticianActivity.class);
        intent.putExtra(EXTRA_KEY_ID, id);
        intent.putExtra(EXTRA_KEY_SHOW_SHOP, showShop);
        activity.startActivityForResult(intent, code);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_beautician;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        id = super.getStringExtraSecure(EXTRA_KEY_ID);
        showShop = getIntent().getBooleanExtra(EXTRA_KEY_SHOW_SHOP, false);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText(showShop ? "选择店铺" : "选择美容师");
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

        if (!showShop && StringUtils.isEmpty(id)) {
            jSwipeRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
            jSwipeRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMoreData();
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    loadIdIsNullList(true);
                }
            });
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        rvList.setLayoutManager(linearLayoutManager);

        if (showShop) {
            shopAdapter = new SelectShopAdapter(getBaseContext());
            rvList.setAdapter(shopAdapter);
            shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopBean item = (ShopBean) adapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("ShopBean", item);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            beauticianAdapter = new SelectBeauticianAdapter(getBaseContext());
            rvList.setAdapter(beauticianAdapter);
            beauticianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    BeauticianBean item = (BeauticianBean) adapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("BeauticianBean", item);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    protected void initData() {
        super.initData();
        if (showShop && StringUtils.isNotEmpty(id)) {
            loadShopList();
        } else if (!showShop && StringUtils.isNotEmpty(id)) {
            loadBeauticianList();
        } else {
            loadIdIsNullList(true);
        }
    }

    private void loadIdIsNullList(boolean isRefresh) {
        this.isRefresh = isRefresh;
        isLoadMore = false;
        if (isRefresh) {
            jPageNum = 1;
            showRefreshLoading(isRefresh);
        }
        loadData(jPageNum);
    }

    private void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        loadData(jPageNum);
    }

    private void loadData(int pageNum) {
        OkGo.<String>get(URLs.COORDINATE_SHOP).params("longitude", longitude).params("page", pageNum)
                .params("latitude", latitude).execute(callback);
    }

    private void loadShopList() {
        OkGo.<String>get(URLs.BEAUTICIAN_SHOP).params("longitude", longitude).params("beautician_id", id)
                .params("latitude", latitude).execute(callback);
    }

    StringCallback callback = new StringCallback() {
        @Override
        public void onSuccess(Response<String> response) {
            LogUtil.e(TAG, "response:" + response.body());
            try {
                JSONObject jsonObject = new JSONObject(response.body());
                Object msg = jsonObject.opt("msg");
                if (msg.equals("暂无数据")) {
                    multipleStatusView.showEmpty();
                    return;
                }
                multipleStatusView.showOutContentView(rvList);
                Gson gson = new Gson();
                List<ShopBean> listResult = gson.fromJson(jsonObject.optString("data"), new TypeToken<List<ShopBean>>() {
                }.getType());
                if (!showShop && StringUtils.isEmpty(id)) {
                    onResultSuccess(listResult);
                } else {
                    if (CollectionUtils.isNotEmpty(listResult)) {
                        shopAdapter.replaceData(listResult);
                    } else {
                        multipleStatusView.showEmpty();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Response<String> response) {
            super.onError(response);
            if (!showShop && StringUtils.isEmpty(id)) {
                onResultError();
            } else {
                multipleStatusView.showError();
            }
        }
    };

    private void loadBeauticianList() {
        OkGo.<String>post(URLs.SHOP_BEAUTICIAN).params("shop_id", id).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        multipleStatusView.showEmpty(R.mipmap.no_beautician);
                        return;
                    }
                    multipleStatusView.showOutContentView(rvList);
                    Gson gson = new Gson();
                    List<BeauticianBean> listResult = gson.fromJson(jsonObject.optString("data"), new TypeToken<List<BeauticianBean>>() {
                    }.getType());
                    if (CollectionUtils.isNotEmpty(listResult)) {
                        beauticianAdapter.replaceData(listResult);
                    } else {
                        multipleStatusView.showEmpty();
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
    }


    private void onResultSuccess(List<ShopBean> response) {
        if (!isLoadMore) {
            jPageNum++;
            if (response.size() == 0) {
                showEmpty();
            }
            fillData(response);
        } else {
            jPageNum++;
            loadMoreSuccess(response);
        }
    }

    private void fillData(List<ShopBean> listData) {
        if (isRefresh)
            showRefreshLoading(false);
        showData(listData);
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
        shopAdapter.replaceData(dataList);
        rvList.scrollToPosition(0);
    }

    private void loadMoreSuccess(List<ShopBean> dataList) {
        jSwipeRefreshLayout.finishLoadMore();
        shopAdapter.addData(dataList);
        if (dataList.size() < 10) {
            showLoadMoreEnd();
        } else {
            showLoadMoreComplete();
        }
    }

    private void showRefreshLoading(boolean show) {
        if (show) {
            jSwipeRefreshLayout.refreshDrawableState();
        } else {
            jSwipeRefreshLayout.finishRefresh();
        }
    }

    private void showEmpty() {
        multipleStatusView.showEmpty();
    }

    private void showError() {
        multipleStatusView.showError();
    }

    private void showLoadMoreFailed() {
        shopAdapter.loadMoreFail();
    }

    private void showLoadMoreEnd() {
        jSwipeRefreshLayout.setEnableLoadMore(false);
        addCantLoadMoreFooter(shopAdapter);
        shopAdapter.loadMoreEnd(false);
    }

    private void showLoadMoreComplete() {
        shopAdapter.loadMoreComplete();
    }

    private boolean isOnDestroy = false;

    @Override
    public void onDestroy() {
        isOnDestroy = true;
        super.onDestroy();
    }

    private View footer;

    private void addCantLoadMoreFooter(BaseQuickAdapter adapter) {
        if (footer == null) {
            footer = LayoutInflater.from(this).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }
}
