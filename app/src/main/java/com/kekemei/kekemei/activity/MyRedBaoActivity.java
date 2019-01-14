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
import com.kekemei.kekemei.adapter.HongBaoDataAdapter;
import com.kekemei.kekemei.bean.HongBaoDataBean;
import com.kekemei.kekemei.utils.LogUtil;
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

public class MyRedBaoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout jSwipeRefreshLayout;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private HongBaoDataAdapter jAdapter;

    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;
    private boolean is_person;

    public static void start(Context context, boolean flag) {
        Intent intent = new Intent(context, MyRedBaoActivity.class);
        intent.putExtra("IS_PERSON", flag);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_voucher;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        is_person = getIntent().getBooleanExtra("IS_PERSON", false);
        tvTitle.setText("红包");
        toolbar.setNavigationIcon(R.mipmap.back);
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

        rvList.setLayoutManager(new LinearLayoutManager(this));
        jAdapter = new HongBaoDataAdapter();
        rvList.setAdapter(jAdapter);
        jAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HongBaoDataBean.DataBean item = jAdapter.getItem(position);
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("price", item.getPrice_reduction());
                intent.putExtra("id", item.getRedenvelopes_id());
                if (is_person) {
                    ProjectListActivity.start(MyRedBaoActivity.this, "3");
                } else {
                    setResult(RESULT_OK, intent);
                }
                finish();
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
        OkGo.<String>get(URLs.MY_RED_ENVELOPES).tag(this).params("user_id", userId)
                .params("page", pageNum).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        if (isRefresh) {
                            multipleStatusView.showEmpty(R.mipmap.default_youhuijuan);
                        }
                        jSwipeRefreshLayout.finishLoadMore();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                HongBaoDataBean hongBaoDataBean = gson.fromJson(response.body(), HongBaoDataBean.class);
                onResultSuccess(hongBaoDataBean);
            }

            @Override
            public void onError(Response<String> response) {
                LogUtil.e("TAG", response.message());
                onResultError();
            }
        });
    }

    private void onResultSuccess(Object response) {
        if (!isLoadMore) {
            jPageNum++;
            HongBaoDataBean hongBaoDataBean = (HongBaoDataBean) response;

            if (null == response || null == hongBaoDataBean.getData()) {
                showEmpty();
            }
            fillData(hongBaoDataBean.getData());
        } else {
            jPageNum++;
            HongBaoDataBean hongBaoDataBean = (HongBaoDataBean) response;
            loadMoreSuccess(hongBaoDataBean.getData());
        }
    }

    private void fillData(List<HongBaoDataBean.DataBean> listData) {
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

    private void showRefreshLoading(boolean show) {
        if (show) {
            jSwipeRefreshLayout.refreshDrawableState();
        } else {
            jSwipeRefreshLayout.finishRefresh();
        }
    }

    private void showData(List<HongBaoDataBean.DataBean> dataList) {
        if (isOnDestroy)
            return;
        multipleStatusView.showOutContentView(jSwipeRefreshLayout);
        jAdapter.replaceData(dataList);
        rvList.scrollToPosition(0);
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

    private void loadMoreSuccess(List<HongBaoDataBean.DataBean> dataList) {
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
