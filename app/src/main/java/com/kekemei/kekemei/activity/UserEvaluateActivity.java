package com.kekemei.kekemei.activity;

import android.content.Context;
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
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.EvaluateListAdapter;
import com.kekemei.kekemei.bean.EvaluateBean;
import com.kekemei.kekemei.bean.EvaluateListBean;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
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
import butterknife.OnClick;

/**
 * 用户评价
 */
public class UserEvaluateActivity extends BaseActivity {
    public static final int EVALUATE_STATUS_ALL = 0;     //全部
    public static final int EVALUATE_STATUS_QUITE_SATISFACTION = 1; //超出期待
    public static final int EVALUATE_STATUS_SATISFACTION = 2;     //满意
    public static final int EVALUATE_STATUS_BASICALLY_SATISFACTION = 3; //基本满意
    public static final int EVALUATE_STATUS_DISSATISFIED = 4;      //不满意
    private int mCurrentTab = EVALUATE_STATUS_ALL;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tabAll)
    TextView tabAll;
    @BindView(R.id.tabQuiteSatisfaction)
    TextView tabQuiteSatisfaction;
    @BindView(R.id.tabSatisfaction)
    TextView tabSatisfaction;
    @BindView(R.id.tabBasicallySatisfaction)
    TextView tabBasicallySatisfaction;
    @BindView(R.id.tabDissatisfied)
    TextView tabDissatisfied;

    @BindView(R.id.rv_list)
    RecyclerView jRecyclerView;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout jSwipeRefreshLayout;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private EvaluateListAdapter jAdapter;

    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    private int jPageNum = 1;

    private boolean isMyComment;//是否是我的评价
    public static final String KEY_SHOP_ID = "shopId";
    public static final String KEY_BEAUTICIAN_ID = "beauticianId";
    public static final String KEY_PROJECT_ID = "projectId";
    private String shopId, beauticianId, projectId;

    public static void start(Context context, boolean isMyComment, String shopId, String beauticianId, String projectId) {
        Intent intent = new Intent(context, UserEvaluateActivity.class);
        intent.putExtra("isMyComment", isMyComment);
        intent.putExtra(KEY_SHOP_ID, shopId);
        intent.putExtra(KEY_BEAUTICIAN_ID, beauticianId);
        intent.putExtra(KEY_PROJECT_ID, projectId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_evaluate;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        isMyComment = getIntent().getBooleanExtra("isMyComment", false);
        shopId = super.getStringExtraSecure(KEY_SHOP_ID);
        beauticianId = super.getStringExtraSecure(KEY_BEAUTICIAN_ID);
        projectId = super.getStringExtraSecure(KEY_PROJECT_ID);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText(isMyComment ? "我的评价" : "客户评价");

        tabAll.setSelected(true);
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

        jAdapter = new EvaluateListAdapter(this, isMyComment);

        jRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        jAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        jRecyclerView.setAdapter(jAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        multipleStatusView.showLoading();
        loadData(true);
    }

    @OnClick({R.id.tabAll, R.id.tabQuiteSatisfaction, R.id.tabSatisfaction,
            R.id.tabBasicallySatisfaction, R.id.tabDissatisfied})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tabAll:
                tabAll.setSelected(true);
                tabQuiteSatisfaction.setSelected(false);
                tabSatisfaction.setSelected(false);
                tabBasicallySatisfaction.setSelected(false);
                tabDissatisfied.setSelected(false);
                mCurrentTab = EVALUATE_STATUS_ALL;
                loadData(true);
                break;
            case R.id.tabQuiteSatisfaction:
                tabAll.setSelected(false);
                tabQuiteSatisfaction.setSelected(true);
                tabSatisfaction.setSelected(false);
                tabBasicallySatisfaction.setSelected(false);
                tabDissatisfied.setSelected(false);
                mCurrentTab = EVALUATE_STATUS_QUITE_SATISFACTION;
                loadData(true);
                break;
            case R.id.tabSatisfaction:
                tabAll.setSelected(false);
                tabQuiteSatisfaction.setSelected(false);
                tabSatisfaction.setSelected(true);
                tabBasicallySatisfaction.setSelected(false);
                tabDissatisfied.setSelected(false);
                mCurrentTab = EVALUATE_STATUS_SATISFACTION;
                loadData(true);
                break;
            case R.id.tabBasicallySatisfaction:
                tabAll.setSelected(false);
                tabQuiteSatisfaction.setSelected(false);
                tabSatisfaction.setSelected(false);
                tabBasicallySatisfaction.setSelected(true);
                tabDissatisfied.setSelected(false);
                mCurrentTab = EVALUATE_STATUS_BASICALLY_SATISFACTION;
                loadData(true);
                break;
            case R.id.tabDissatisfied:
                tabAll.setSelected(false);
                tabQuiteSatisfaction.setSelected(false);
                tabSatisfaction.setSelected(false);
                tabBasicallySatisfaction.setSelected(false);
                tabDissatisfied.setSelected(true);
                mCurrentTab = EVALUATE_STATUS_DISSATISFIED;
                loadData(true);
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

    private void loadMoreData() {
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
        OkGo.<String>get(URLs.COMMENT_LIST)
                .tag(this).params("shop_id", shopId).params("user_id", userId)
                .params("beautician_id", beauticianId).params("project_id", projectId)
                .params("page", pageNum).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    String data = jsonObject.optString("data");
                    if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                        jSwipeRefreshLayout.finishLoadMore();
                        return;
                    }
                    Gson gson = new Gson();
                    EvaluateListBean evaluateListBean = gson.fromJson(data, EvaluateListBean.class);
                    onResultSuccess(evaluateListBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                LogUtil.e("TAG", response.message());
                onResultError(response);
            }
        });
    }

    private void onResultSuccess(Object response) {
        if (!isLoadMore) {
            jPageNum++;
            EvaluateListBean evaluateListBean = (EvaluateListBean) response;

            if (null == response || null == evaluateListBean.getData()) {
                showEmpty();
            }
            fillTabText(evaluateListBean.getCount());
            fillData(evaluateListBean.getData().get(mCurrentTab));
        } else {
            jPageNum++;
            EvaluateListBean evaluateListBean = (EvaluateListBean) response;
            loadMoreSuccess(evaluateListBean.getData().get(mCurrentTab));
        }
    }

    private void fillTabText(List<Integer> count) {
        tabAll.setText("全部\n" + count.get(0));
        tabQuiteSatisfaction.setText("超出期待\n" + count.get(1));
        tabSatisfaction.setText("满意\n" + count.get(2));
        tabBasicallySatisfaction.setText("基本满意\n" + count.get(3));
        tabDissatisfied.setText("不满意\n" + count.get(4));
    }

    private void fillData(List<EvaluateBean> listData) {
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

    private void onResultError(Object response) {
        if (!isRefresh && !isLoadMore)
            showError();
        if (isLoadMore)
            showLoadMoreFailed();
        if (isRefresh)
            showRefreshLoading(false);
    }

    private void showRefreshLoading(boolean show) {
        if (show) {
            jSwipeRefreshLayout.refreshDrawableState();
        } else {
            jSwipeRefreshLayout.finishRefresh();
        }
    }

    private void showData(List<EvaluateBean> dataList) {
        if (isOnDestroy)
            return;
        multipleStatusView.showOutContentView(jSwipeRefreshLayout);
        jAdapter.replaceData(dataList);
        jRecyclerView.scrollToPosition(0);
    }

    private void showEmpty() {
        multipleStatusView.showEmpty();
    }

    private void showError() {
        multipleStatusView.showError();
    }

    private void showNoNetwork() {
        multipleStatusView.showNoNetwork();
    }

    private void loadMoreSuccess(List<EvaluateBean> dataList) {
        jSwipeRefreshLayout.finishLoadMore();
        jAdapter.addData(dataList);
        if (dataList.size() < 10) {
            showLoadMoreEnd();
        } else {
            showLoadMoreComplete();
        }
    }

    private void showLoadMoreFailed() {
        jAdapter.loadMoreFail();
    }

    private void showLoadMoreEnd() {
        jSwipeRefreshLayout.setEnableLoadMore(false);
        addCantLoadMoreFooter(jAdapter);
        jAdapter.loadMoreEnd(false);
    }

    private void showLoadMoreComplete() {
        jAdapter.loadMoreComplete();
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
