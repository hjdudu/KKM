package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.MeiRongShiListAdapter;
import com.kekemei.kekemei.adapter.ProjectListAdapter;
import com.kekemei.kekemei.adapter.ShopListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.BeauticianBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.HotSearchBean;
import com.kekemei.kekemei.bean.SearchResultBean;
import com.kekemei.kekemei.bean.ShopBean;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * 搜索结果页
 */
public class SearchActivity extends BaseActivity implements TextWatcher {
    private static final String TAG = SearchActivity.class.getSimpleName();
    public static final String EXTRA_KEY_KEYWORD = "keyword";
    @BindView(R.id.fanhui)
    ImageView fanhui;
    private int mCurrentTab = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editTextSearch)
    XEditText editTextSearch;
    @BindView(R.id.txtSearch)
    TextView txtSearch;

    @BindView(R.id.tabAllText)
    TextView tabAllText;
    @BindView(R.id.indicatorTabAll)
    ImageView indicatorTabAll;

    @BindView(R.id.tabProjectText)
    TextView tabProjectText;
    @BindView(R.id.indicatorProject)
    ImageView indicatorProject;

    @BindView(R.id.tabShopNameText)
    TextView tabShopNameText;
    @BindView(R.id.indicatorShopName)
    ImageView indicatorShopName;

    @BindView(R.id.tabBeauticianText)
    TextView tabBeauticianText;
    @BindView(R.id.indicatorBeautician)
    ImageView indicatorBeautician;

    @BindView(R.id.searchAllLayout)
    LinearLayout searchAllLayout;
    @BindView(R.id.layoutHistoryFlowLayout)
    FlexboxLayout layoutHistoryFlowLayout;
    @BindView(R.id.historyEmpty)
    TextView historyEmpty;
    @BindView(R.id.layoutHotSearchFlowLayout)
    FlexboxLayout layoutHotSearchFlowLayout;

    @BindView(R.id.searchListLayout)
    LinearLayout searchListLayout;
    @BindView(R.id.project_rfresh_layout)
    SmartRefreshLayout project_rfresh_layout;
    @BindView(R.id.projectList)
    RecyclerView projectList;
    @BindView(R.id.shop_rfresh_layout)
    SmartRefreshLayout shop_rfresh_layout;
    @BindView(R.id.shopList)
    RecyclerView shopList;
    @BindView(R.id.beautician_rfresh_layout)
    SmartRefreshLayout beautician_rfresh_layout;
    @BindView(R.id.beauticianList)
    RecyclerView beauticianList;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private ProjectListAdapter projectAdapter;
    private ShopListAdapter shopAdapter;
    private MeiRongShiListAdapter beauticianAdapter;
    private int jPageNum = 1;

    private String keyWord = "";
    private SearchResultBean searchResultBean;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        editTextSearch.setFocusable(true);
        editTextSearch.setFocusableInTouchMode(true);
        editTextSearch.requestFocus();
        fanhui.setVisibility(View.VISIBLE);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        indicatorTabAll.setVisibility(View.VISIBLE);
        tabAllText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_selected_color));
        searchAllLayout.setVisibility(View.VISIBLE);
        searchListLayout.setVisibility(View.GONE);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(false);
            }
        });
        multipleStatusView.showOutContentView(project_rfresh_layout);

        project_rfresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData(true);
            }
        });
        project_rfresh_layout.setRefreshHeader(new ClassicsHeader(this));

        shop_rfresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData(true);
            }
        });
        shop_rfresh_layout.setRefreshHeader(new ClassicsHeader(this));

        beautician_rfresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData(true);
            }
        });
        beautician_rfresh_layout.setRefreshHeader(new ClassicsHeader(this));

        initRecyclerView();
    }

    private void initRecyclerView() {
        projectList.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectListAdapter(this);
        projectList.setAdapter(projectAdapter);
        projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = (BaseBean) adapter.getItem(position);
                ProjectDetailActivity.start(SearchActivity.this, data.getId());
            }
        });

        shopList.setLayoutManager(new LinearLayoutManager(this));
        shopAdapter = new ShopListAdapter(this, R.layout.list_shop);
        shopList.setAdapter(shopAdapter);
        shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopBean shopBean = (ShopBean) adapter.getItem(position);
                ShopActivity.start(SearchActivity.this, shopBean.getId(), DetailEnum.SHOP);
            }
        });

        beauticianList.setLayoutManager(new LinearLayoutManager(this));
        beauticianAdapter = new MeiRongShiListAdapter(this, R.layout.list_meirongshi);
        beauticianList.setAdapter(beauticianAdapter);

        beauticianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BeauticianBean beauticianBean = (BeauticianBean) adapter.getItem(position);
                ShopActivity.start(SearchActivity.this, beauticianBean.getId(), DetailEnum.BEAUTICIAN);
            }
        });
    }

    @Optional
    @OnClick({R.id.txtSearch, R.id.tabAll, R.id.tabProject, R.id.tabBeautician, R.id.tabShopName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtSearch:
                keyWord = editTextSearch.getText().toString();
                if (txtSearch.getText().toString().equals("搜索")) {
                    txtSearch.setText("取消");
                    onSearchBtnClick();
                    loadData(true);
                } else {
                    editTextSearch.setText("");
                    txtSearch.setText("搜索");
                    onSearchBtnClickCancel();
                    initSearchHistoryAndHot();
                }
                break;
            case R.id.tabAll:
                onSearchBtnClickCancel();
                break;
            case R.id.tabProject:
                onSearchBtnClick();
                if (searchResultBean != null) {
                    replaceData(searchResultBean);
                }
                break;
            case R.id.tabShopName:
                indicatorTabAll.setVisibility(View.GONE);
                indicatorProject.setVisibility(View.GONE);
                indicatorShopName.setVisibility(View.VISIBLE);
                indicatorBeautician.setVisibility(View.GONE);
                tabAllText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                tabProjectText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                tabShopNameText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_selected_color));
                tabBeauticianText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                searchAllLayout.setVisibility(View.GONE);
                searchListLayout.setVisibility(View.VISIBLE);
                mCurrentTab = 1;
                project_rfresh_layout.setVisibility(View.GONE);
                shop_rfresh_layout.setVisibility(View.VISIBLE);
                beautician_rfresh_layout.setVisibility(View.GONE);
                if (searchResultBean != null) {
                    replaceData(searchResultBean);
                }
                break;
            case R.id.tabBeautician:
                indicatorTabAll.setVisibility(View.GONE);
                indicatorProject.setVisibility(View.GONE);
                indicatorShopName.setVisibility(View.GONE);
                indicatorBeautician.setVisibility(View.VISIBLE);
                tabAllText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                tabProjectText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                tabShopNameText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
                tabBeauticianText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_selected_color));
                searchAllLayout.setVisibility(View.GONE);
                searchListLayout.setVisibility(View.VISIBLE);
                mCurrentTab = 2;
                project_rfresh_layout.setVisibility(View.GONE);
                shop_rfresh_layout.setVisibility(View.GONE);
                beautician_rfresh_layout.setVisibility(View.VISIBLE);
                if (searchResultBean != null) {
                    replaceData(searchResultBean);
                }
                break;
        }
    }

    private void onSearchBtnClickCancel() {
        indicatorTabAll.setVisibility(View.VISIBLE);
        indicatorProject.setVisibility(View.GONE);
        indicatorShopName.setVisibility(View.GONE);
        indicatorBeautician.setVisibility(View.GONE);
        tabAllText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_selected_color));
        tabProjectText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        tabShopNameText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        tabBeauticianText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        searchAllLayout.setVisibility(View.VISIBLE);
        searchListLayout.setVisibility(View.GONE);
    }

    private void onSearchBtnClick() {
        indicatorTabAll.setVisibility(View.GONE);
        indicatorProject.setVisibility(View.VISIBLE);
        indicatorShopName.setVisibility(View.GONE);
        indicatorBeautician.setVisibility(View.GONE);
        tabAllText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        tabProjectText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_selected_color));
        tabShopNameText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        tabBeauticianText.setTextColor(ContextCompat.getColor(this, R.color.search_tab_text_unselected_color));
        searchAllLayout.setVisibility(View.GONE);
        searchListLayout.setVisibility(View.VISIBLE);
        mCurrentTab = 0;
        project_rfresh_layout.setVisibility(View.VISIBLE);
        shop_rfresh_layout.setVisibility(View.GONE);
        beautician_rfresh_layout.setVisibility(View.GONE);
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

    public void getData(int pageNum) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        OkGo.<String>post(URLs.INDEX_SEARCH)
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
                                onResult(null);
                                return;
                            }
                            Gson gson = new Gson();
                            searchResultBean = gson.fromJson(data, SearchResultBean.class);
                            onResult(searchResultBean);
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
            if (mCurrentTab == 0) {
                project_rfresh_layout.refreshDrawableState();
            } else if (mCurrentTab == 1) {
                shop_rfresh_layout.refreshDrawableState();
            } else if (mCurrentTab == 2) {
                beautician_rfresh_layout.refreshDrawableState();
            }
        } else {
            if (mCurrentTab == 0) {
                project_rfresh_layout.finishRefresh();
            } else if (mCurrentTab == 1) {
                shop_rfresh_layout.finishRefresh();
            } else if (mCurrentTab == 2) {
                beautician_rfresh_layout.finishRefresh();
            }
        }
    }

    public void onResult(Object response) {
        if (!isLoadMore) {
            jPageNum++;

            SearchResultBean searchResultBean = (SearchResultBean) response;

            if (response == null) {
                showEmpty();
                return;
            }
            replaceData(searchResultBean);

            if (searchResultBean.getBeautician().size() < 10)
                showLoadMoreEnd();
            else
                showLoadMoreComplete();
        } else {
            jPageNum++;
            SearchResultBean searchResultBean = (SearchResultBean) response;
            if (mCurrentTab == 0) {
                project_rfresh_layout.finishLoadMore();
                projectAdapter.addData(searchResultBean.getProject());
            } else if (mCurrentTab == 1) {
                shop_rfresh_layout.finishLoadMore();
                shopAdapter.addData(searchResultBean.getShop());
            } else if (mCurrentTab == 2) {
                beautician_rfresh_layout.finishLoadMore();
                beauticianAdapter.addData(searchResultBean.getBeautician());
            }
            if (searchResultBean.getBeautician().size() < 10) {
                showLoadMoreEnd();
            } else {
                showLoadMoreComplete();
            }
        }
    }

    private void replaceData(SearchResultBean searchResultBean) {
        if (mCurrentTab == 0) {
            if (null == searchResultBean || searchResultBean.getProject().size() == 0) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                showProjectData(searchResultBean.getProject());
            }
        } else if (mCurrentTab == 1) {
            if (null == searchResultBean || searchResultBean.getShop().size() == 0) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                showShopNameData(searchResultBean.getShop());
            }
        } else if (mCurrentTab == 2) {
            if (null == searchResultBean || searchResultBean.getBeautician().size() == 0) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                showBeauticianData(searchResultBean.getBeautician());
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
        if (mCurrentTab == 0) {
            projectAdapter.loadMoreFail();
        } else if (mCurrentTab == 1) {
            shopAdapter.loadMoreFail();
        } else if (mCurrentTab == 2) {
            beauticianAdapter.loadMoreFail();
        }
    }

    public void showLoadMoreEnd() {
        if (mCurrentTab == 0) {
            project_rfresh_layout.setEnableLoadMore(false);
            projectAdapter.loadMoreEnd(false);
            addCantLoadMoreFooter(projectAdapter);
        } else if (mCurrentTab == 1) {
            shop_rfresh_layout.setEnableLoadMore(false);
            shopAdapter.loadMoreEnd(false);
            addCantLoadMoreFooter(shopAdapter);
        } else if (mCurrentTab == 2) {
            beautician_rfresh_layout.setEnableLoadMore(false);
            beauticianAdapter.loadMoreEnd(false);
            addCantLoadMoreFooter(beauticianAdapter);
        }
    }

    public void showLoadMoreComplete() {
        if (mCurrentTab == 0) {
            projectAdapter.loadMoreComplete();
        } else if (mCurrentTab == 1) {
            shopAdapter.loadMoreComplete();
        } else if (mCurrentTab == 2) {
            beauticianAdapter.loadMoreComplete();
        }
    }

    public void showProjectData(List<BaseBean> dataList) {
        if (isOnDestory)
            return;
        multipleStatusView.showOutContentView(project_rfresh_layout);
        projectAdapter.replaceData(dataList);
        projectList.scrollToPosition(0);
    }

    public void showShopNameData(List<ShopBean> dataList) {
        if (isOnDestory)
            return;
        multipleStatusView.showOutContentView(shop_rfresh_layout);
        shopAdapter.replaceData(dataList);
        shopList.scrollToPosition(0);
    }

    public void showBeauticianData(List<BeauticianBean> dataList) {
        if (isOnDestory)
            return;
        multipleStatusView.showOutContentView(beautician_rfresh_layout);
        beauticianAdapter.replaceData(dataList);
        beauticianList.scrollToPosition(0);
    }

    @Override
    protected void initData() {
        super.initData();
        initSearchHistoryAndHot();
    }

    private void initSearchHistoryAndHot() {
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
                            HotSearchBean hotSearchBean = gson.fromJson(data, HotSearchBean.class);
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
            historyEmpty.setVisibility(View.VISIBLE);
        } else {
            if (CollectionUtils.isNotEmpty(hotSearchBean.getHistory())) {
                fillHistoryWordArea(hotSearchBean.getHistory());
            } else {
                historyEmpty.setVisibility(View.VISIBLE);
            }
            if (CollectionUtils.isNotEmpty(hotSearchBean.getHost())) {
                fillHotWordArea(hotSearchBean.getHost());
            } else {
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
                        onSearchBtnClick();
                    }
                });
                layoutHistoryFlowLayout.addView(txt);
            }
        }
    }

    /**
     * 填充热门搜索区域
     */
    private void fillHotWordArea(final List<String> result) {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        layoutHotSearchFlowLayout.removeAllViews();
        for (int i = 0; i < result.size(); i++) {
            final TextView txt = (TextView) LayoutInflater.from(this).inflate(R.layout.item_search_history_item, layoutHotSearchFlowLayout, false);
            if (!AppUtil.isEmptyString(result.get(i))) {
                txt.setText(result.get(i));
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        keyWord = txt.getText().toString();
                        editTextSearch.setText(keyWord);
                        moveEditCursor();
                        loadData(true);
                        onSearchBtnClick();
                    }
                });
                layoutHotSearchFlowLayout.addView(txt);
            }
        }
    }

    private void moveEditCursor() {
        CharSequence s = editTextSearch.getText();
        if (StringUtils.isNotEmpty(s)) {
            Spannable spanText = (Spannable) s;
            Selection.setSelection(spanText, s.length());
        }
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

    private boolean isOnDestory = false;

    @Override
    protected void onDestroy() {
        isOnDestory = true;
        super.onDestroy();
    }

    private View footer;

    private void addCantLoadMoreFooter(BaseQuickAdapter adapter) {
        if (footer == null) {
            footer = LayoutInflater.from(this).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
