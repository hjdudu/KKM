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
import com.google.gson.reflect.TypeToken;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.ProjectListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
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

/**
 * 新人体验
 */
public class ProjectListActivity extends BaseActivity {
    public static final String TAG = ProjectListActivity.class.getSimpleName();
    private static final String EXTRA_KEY_STATE = "state";//2=热门,3=最新,不传代表所有
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;
    @BindView(R.id.newPeopleList)
    RecyclerView newPeopleList;
    private ProjectListAdapter listAdapter;

    private String state;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;

    public static void start(Context context, String state) {
        Intent intent = new Intent(context, ProjectListActivity.class);
        intent.putExtra(EXTRA_KEY_STATE, state);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_project_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        state = super.getStringExtraSecure(EXTRA_KEY_STATE);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText(StringUtils.isEmpty(state) ? "所有项目" : state.equals("2") ? "热门项目" : "最新项目");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        multipleStatusView.showOutContentView(refresh_layout);

        refresh_layout.setRefreshHeader(new ClassicsHeader(this));
        refresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                loadData(true);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        newPeopleList.setLayoutManager(linearLayoutManager);
        listAdapter = new ProjectListAdapter(getBaseContext());
        newPeopleList.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("section", "click:" + position);
                BaseBean item = listAdapter.getItem(position);
                ProjectDetailActivity.start(ProjectListActivity.this, item.getId());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
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

    public void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        getData(jPageNum);
    }

    private void getData(int pageNum) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        OkGo.<String>post(URLs.PROJECT_LIST).params("category", "").params("page", pageNum)
                .params("state", state).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.e(TAG, "response:" + response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    String data = jsonObject.optString("data");
                    if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                        if (!isLoadMore) {
                            multipleStatusView.showEmpty(R.mipmap.no_more_project);
                        }
                        return;
                    }
                    multipleStatusView.showOutContentView(refresh_layout);
                    Gson gson = new Gson();
                    List<BaseBean> listBean = gson.fromJson(data, new TypeToken<List<BaseBean>>() {
                    }.getType());
                    onComerResult(listBean);
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

    public void onComerResult(List<BaseBean> response) {
        if (!isLoadMore) {
            jPageNum++;

            if (CollectionUtils.isEmpty(response)) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                listAdapter.replaceData(response);
            }

            if (response.size() < 10)
                showLoadMoreEnd();
            else
                showLoadMoreComplete();
        } else {
            jPageNum++;
            loadMoreSuccess(response);
            if (response.size() < 10) {
                showLoadMoreEnd();
            } else {
                showLoadMoreComplete();
            }
        }
    }

    public void onResultError() {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showError();
        if (isLoadMore)
            listAdapter.loadMoreFail();
        if (isRefresh)
            showRefreshLoading(false);
    }

    public void showEmpty() {
        multipleStatusView.showEmpty();
    }

    public void loadMoreSuccess(List<BaseBean> dataList) {
        refresh_layout.finishLoadMore();
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

    public void showRefreshLoading(boolean show) {
        if (show) {
            refresh_layout.refreshDrawableState();
        } else {
            refresh_layout.finishRefresh();
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
