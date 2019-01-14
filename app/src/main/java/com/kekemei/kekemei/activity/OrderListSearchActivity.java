package com.kekemei.kekemei.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.OrderListAdapter;
import com.kekemei.kekemei.bean.HotSearchBean;
import com.kekemei.kekemei.bean.OrderListBean;
import com.kekemei.kekemei.bean.YuYueActivityBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.MultipleStatusView;
import com.kekemei.kekemei.view.XEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单搜索
 */
public class OrderListSearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    @BindView(R.id.editTextSearch)
    XEditText editTextSearch;
    @BindView(R.id.txtSearch)
    TextView txtSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_order_list_search)
    RecyclerView rvOrderListSearch;
    @BindView(R.id.layoutHistoryFlowLayout)
    FlexboxLayout layoutHistoryFlowLayout;
    @BindView(R.id.historyEmpty)
    TextView historyEmpty;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;
    private OrderListAdapter listAdapter;
    private String keyWord = "";

    private ArrayList<OrderListBean.DataBean> arrayList = new ArrayList<OrderListBean.DataBean>();

    @Override
    protected int setLayoutId() {
        return R.layout.order_list_search_activity;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(false);
            }
        });
        multipleStatusView.showOutContentView(refresh_layout);


        editTextSearch.setFocusable(true);
        editTextSearch.setFocusableInTouchMode(true);
        editTextSearch.requestFocus();
        moveEditCursor();
        editTextSearch.addTextChangedListener(this);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    editTextSearch.clearFocus();
                }
                return false;
            }
        });


        refresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData(true);
            }
        });
        refresh_layout.setRefreshHeader(new ClassicsHeader(this));

        listAdapter = new OrderListAdapter(this);
        rvOrderListSearch.setLayoutManager(new LinearLayoutManager(this));
        rvOrderListSearch.setAdapter(listAdapter);
        txtSearch.setOnClickListener(this);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderListBean.DataBean data = (OrderListBean.DataBean) adapter.getItem(position);
                ProjectDetailActivity.start(OrderListSearchActivity.this, data.getProject_project_id());
            }
        });
        listAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                final OrderListBean.DataBean item = (OrderListBean.DataBean) adapter.getItem(position);
                final List<OrderListBean.DataBean> data = listAdapter.getData();
                switch (view.getId()) {
                    case R.id.iv_del_order:
                        long userId = UserHelp.getUserId(OrderListSearchActivity.this);
                        if (userId == -1L) {
                            LoginActivity.start(OrderListSearchActivity.this);
                            return;
                        }
                        OkGo.<String>get(URLs.DEL_ORDER)
                                .params("user_id", userId)
                                .params("order_id", item.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        data.remove(position);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                });
                        break;
                    case R.id.lijifukuan:
//                        YuYueActivityBean yuYueActivityBean = new YuYueActivityBean();
//                        yuYueActivityBean.setDateSelect(-1L);
//                        yuYueActivityBean.setTimeSelect(-1);
////                        yuYueActivityBean.setOrderCreateTime(item.getCreatetime() + "");
//                        yuYueActivityBean.setOrderPrice(item.getPrice());
//                        yuYueActivityBean.setOrderCount(item.getCount());
//                        yuYueActivityBean.setOrderIconUrl(item.getImage());
//                        yuYueActivityBean.setOrderName(item.getName());
//                        yuYueActivityBean.setOrderId(item.getId());
//                        yuYueActivityBean.setProject_id(item.getProject_project_id());
//                        yuYueActivityBean.setOrderCreateTime(item.getCreatetime()+"");
//                        yuYueActivityBean.setFromDetail(false);
////                        yuYueActivityBean.setOrderId(item.getId() + "");
//                        PayActivity.start(OrderListSearchActivity.this, yuYueActivityBean);
                        ProjectDetailActivity.start(OrderListSearchActivity.this, item.getProject_project_id());
                        break;
                    case R.id.chakan:
                        OrderDetailActivity.start(OrderListSearchActivity.this, item.getId());
                        break;
                    case R.id.zaicigoumai:
//                        ProjectDetailActivity.start(OrderListSearchActivity.this, item.getProject_project_id());
                        OkGo.<String>post(URLs.ORDER_CONFIRM)
                                .params("order_id", item.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
//                                        onViewClicked(talPingjia);
                                    }
                                });
                        break;
                    case R.id.qupingjia:
//                        UserEvaluateActivity.start(OrderListSearchActivity.this, false, item.getShop_shop_id() + "",
                        //                                item.getBeautician_beautician_id() + "",
                        //                                item.getProject_project_id() + "");
                        AddCommentActivity.start(OrderListSearchActivity.this, item.getSource(), item.getId() + "");
                        break;
                    case R.id.yuyue:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        initSearchHistory();
    }

    private void initSearchHistory() {
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.HOT_SEARCH).tag(this).params("user_id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("Search", "body:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            String data = jsonObject.optString("data");
                            if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                                onHotSearchResult(null);
                                return;
                            }
                            Gson gson = new Gson();
                            HotSearchBean hotSearchBean = gson.fromJson(response.body(), HotSearchBean.class);
                            onHotSearchResult(hotSearchBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("TAG", response.message());
                        onHotSearchResult(null);
                    }
                });
    }

    private void onHotSearchResult(Object response) {
        HotSearchBean hotSearchBean = (HotSearchBean) response;
        if (null == response || null == hotSearchBean) {
            llHistory.setVisibility(View.VISIBLE);
        } else {
            if (CollectionUtils.isNotEmpty(hotSearchBean.getHistory())) {
                fillHistoryWordArea(hotSearchBean.getHistory());
            } else {
                llHistory.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 填充历史搜索区域
     */
    private void fillHistoryWordArea(final List<String> result) {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        layoutHistoryFlowLayout.removeAllViews();
        for (int i = 0; i < result.size(); i++) {
            final TextView txt = (TextView) LayoutInflater.from(this).inflate(R.layout.item_search_history_item, layoutHistoryFlowLayout, false);
            if (!AppUtil.isEmptyString(result.get(i))) {
                txt.setText(result.get(i));
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        keyWord = txt.getText().toString();
                        editTextSearch.setText(keyWord);
                        moveEditCursor();
                        loadData(true);
                    }
                });
                layoutHistoryFlowLayout.addView(txt);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSearch:
                if (txtSearch.getText().toString().equals("搜索")) {
                    txtSearch.setText("取消");
                    llHistory.setVisibility(View.GONE);
                    refresh_layout.setVisibility(View.VISIBLE);
                    keyWord = editTextSearch.getText().toString();
                    getData(1);
                } else {
                    txtSearch.setText("搜索");
                    editTextSearch.setText("");
                    llHistory.setVisibility(View.VISIBLE);
                    refresh_layout.setVisibility(View.GONE);
                    initSearchHistory();
                }
                break;
        }
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

    public void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        getData(jPageNum);
    }

    private void getData(int pageNum) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>get(URLs.ORDER_SEARCH)
                .tag(this)
                .params("keyword", keyWord)
                .params("page", pageNum)
                .params("user_id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("Search", "body:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            String data = jsonObject.optString("data");
                            if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                                if (isLoadMore) {
                                    refresh_layout.finishLoadMore();
                                    showLoadMoreComplete();
                                    return;
                                }
                                multipleStatusView.showEmpty();
                                return;
                            }
                            Gson gson = new Gson();
                            OrderListBean orderListBean = gson.fromJson(response.body(), OrderListBean.class);
                            onResult(orderListBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("TAGE", response.message());
                        onResultError(response);
                    }
                });
    }

    public void showRefreshLoading(boolean show) {
        if (show) {
            refresh_layout.refreshDrawableState();
        } else {
            refresh_layout.finishRefresh();
        }
    }

    public void onResult(Object response) {
        if (!isLoadMore) {
            jPageNum++;
            OrderListBean orderListBean = (OrderListBean) response;

            if (null == response || null == orderListBean.getData() || orderListBean.getData().size() == 0) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                showData(orderListBean.getData());
            }

            if (null != response && orderListBean.getData().size() < 10)
                showLoadMoreEnd();
            else
                showLoadMoreComplete();
        } else {
            jPageNum++;
            OrderListBean orderListBean = (OrderListBean) response;
            loadMoreSuccess(orderListBean.getData());
            if (orderListBean.getData().size() < 10) {
                showLoadMoreEnd();
            } else {
                showLoadMoreComplete();
            }
        }
    }

    public void onResultError(Object response) {
        if (!isRefresh && !isLoadMore)
            showError();
        if (isLoadMore)
            showLoadMoreFailed();
        if (isRefresh)
            showRefreshLoading(false);
    }

    public void showError() {
        multipleStatusView.showError();
    }

    public void showEmpty() {
        multipleStatusView.showEmpty();
    }

    public void showLoadMoreFailed() {
        listAdapter.loadMoreFail();
    }

    public void loadMoreSuccess(List<OrderListBean.DataBean> dataList) {
        listAdapter.addData(dataList);
    }

    public void showLoadMoreEnd() {
        refresh_layout.setEnableLoadMore(false);
        addCantLoadMoreFooter(listAdapter);
        listAdapter.loadMoreEnd(false);
    }

    public void showLoadMoreComplete() {
        listAdapter.loadMoreComplete();
    }

    public void showData(List<OrderListBean.DataBean> dataList) {
        if (isOnDestroy)
            return;
        multipleStatusView.showOutContentView(refresh_layout);
        listAdapter.replaceData(dataList);
        rvOrderListSearch.scrollToPosition(0);
    }

    private void moveEditCursor() {
        CharSequence s = editTextSearch.getText();
        if (StringUtils.isNotEmpty(s)) {
            Spannable spanText = (Spannable) s;
            Selection.setSelection(spanText, s.length());
        }
    }

    private boolean isOnDestroy = false;

    @Override
    protected void onDestroy() {
        isOnDestroy = true;
        super.onDestroy();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            initData();
        }
    }

    private View footer;

    private void addCantLoadMoreFooter(BaseQuickAdapter adapter) {
        if (footer == null) {
            footer = LayoutInflater.from(this).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }
}
