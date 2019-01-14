package com.kekemei.kekemei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.ProjectListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.ProjectListBean;
import com.kekemei.kekemei.bean.ProjectShaiXuanListBean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassifyActivity extends BaseActivity {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.text_msg)
    TextView textMsg;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_meirong)
    TextView tvMeirong;
    @BindView(R.id.v_meirong)
    View vMeirong;
    @BindView(R.id.tal_meirong)
    LinearLayout talMeirong;
    @BindView(R.id.tv_meiti)
    TextView tvMeiti;
    @BindView(R.id.v_meiti)
    View vMeiti;
    @BindView(R.id.tal_meiti)
    LinearLayout talMeiti;
    @BindView(R.id.tv_yangsheng)
    TextView tvYangsheng;
    @BindView(R.id.v_yangsheng)
    View vYangsheng;
    @BindView(R.id.tal_yangsheng)
    LinearLayout talYangsheng;
    @BindView(R.id.tv_qita)
    TextView tvQita;
    @BindView(R.id.v_qita)
    View vQita;
    @BindView(R.id.tal_qita)
    LinearLayout talQita;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.iv_shaixuan)
    ImageView ivShaixuan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.ll_fanhui)
    LinearLayout llFanhui;
    @BindView(R.id.rv_pinglunbiaoqian)
    RecyclerView rvPinglunbiaoqian;
    @BindView(R.id.ll_pop)
    LinearLayout llPop;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rl_list)
    RelativeLayout rlList;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;
    private PingLunBiaoQianGridViewAdapter pingLunBiaoQianGridViewAdapter;
    private ArrayList<SelectBean> objects;
    private ProjectListAdapter listAdapter;
    @SuppressWarnings("unchecked")
    private int type = R.id.tal_meirong;
    private int isCheck = -1;

    @Override
    protected int setLayoutId() {
        return R.layout.classify_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        llFanhui.setVisibility(View.VISIBLE);
        initCommentTag();
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
        rvList.setLayoutManager(linearLayoutManager);
        listAdapter = new ProjectListAdapter(getBaseContext());
        rvList.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = listAdapter.getItem(position);
                ProjectDetailActivity.start(ClassifyActivity.this, data.getId());
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.tal_meirong, R.id.tal_meiti, R.id.tal_yangsheng, R.id.tal_qita, R.id.tv_shaixuan,
            R.id.iv_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.tal_meirong:
            case R.id.tal_meiti:
            case R.id.tal_yangsheng:
            case R.id.tal_qita:
                setSelect(view.getId());
                tvShaixuan.setSelected(false);
                llPop.setVisibility(View.GONE);
                break;
            case R.id.tv_shaixuan:
            case R.id.iv_shaixuan:
                tvShaixuan.setSelected(tvShaixuan.isSelected() ? false : true);
                llPop.setVisibility(llPop.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
        }
    }

    private void initCommentTag() {
        objects = new ArrayList<>();
        objects.add(new SelectBean("收藏最多", false));
        objects.add(new SelectBean("评论最多", false));
        objects.add(new SelectBean("销量最多", false));
    }

    private void setSelect(int id) {

        tvMeirong.setSelected(id == R.id.tal_meirong);
        vMeirong.setVisibility(id == R.id.tal_meirong ? View.VISIBLE : View.INVISIBLE);

        tvMeiti.setSelected(id == R.id.tal_meiti);
        vMeiti.setVisibility(id == R.id.tal_meiti ? View.VISIBLE : View.INVISIBLE);

        tvYangsheng.setSelected(id == R.id.tal_yangsheng);
        vYangsheng.setVisibility(id == R.id.tal_yangsheng ? View.VISIBLE : View.INVISIBLE);

        tvQita.setSelected(id == R.id.tal_qita);
        vQita.setVisibility(id == R.id.tal_qita ? View.VISIBLE : View.INVISIBLE);
        switch (id) {
            case R.id.tal_meirong:
                page = 1;
                category = 1;
                getData(page, category, isCheck);
                break;
            case R.id.tal_meiti:
                category = 2;
                page = 1;
                getData(page, category, isCheck);
                break;
            case R.id.tal_yangsheng:
                category = 3;
                page = 1;
                getData(page, category, isCheck);
                break;
            case R.id.tal_qita:
                category = 4;
                page = 1;
                getData(page, category, isCheck);
                break;
        }
    }


    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        switch (type) {
            case 1:
                onViewClicked(talMeirong);
                break;
            case 2:
                onViewClicked(talMeiti);
                break;
            case 3:
                onViewClicked(talYangsheng);
                break;
            case 4:
                onViewClicked(talQita);
                break;
        }
        rvPinglunbiaoqian.setLayoutManager(new GridLayoutManager(ClassifyActivity.this, 3));
        pingLunBiaoQianGridViewAdapter = new PingLunBiaoQianGridViewAdapter();
        rvPinglunbiaoqian.setAdapter(pingLunBiaoQianGridViewAdapter);
    }

    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    private void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        page++;
        getData(page, category, isCheck);
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        isLoadMore = false;
        if (isRefresh) {
            page = 1;
            showRefreshLoading(isRefresh);
        }
        getData(page, category, isCheck);
    }

    private int page = 1;
    private int category = 1;

    private void getData(final int page, int category, final int isCheck) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        String url = "";
        switch (isCheck) {
            case -1:
                url = URLs.DISCOVE;
                break;
            case 0:
                url = URLs.PROJECT_SORT_COLLECTION;
                break;
            case 1:
                url = URLs.PROJECT_SORT;
                break;
            case 2:
                url = URLs.PROJECT_SORT_ORDER;
                break;
        }
        OkGo.<String>post(url).params("page", page).params("category", category)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject obj = new JSONObject(response.body());
                            Gson gson = new Gson();
                            List<BaseBean> baseBeans = null;
                            if (isCheck == -1) {
                                ProjectListBean.DataBeanX data = gson.fromJson(response.body(), ProjectListBean.class).getData();
                                if (data.getData().size() == 0 || data.getData().isEmpty()) {
                                    multipleStatusView.showEmpty(R.mipmap.no_more_project);
                                    return;
                                }
                                baseBeans = data.getData();
                            } else {
                                if (obj.getString("data").equals("null") || obj.getString("data") == null
                                        || obj.getString("data").isEmpty()) {
                                    multipleStatusView.showEmpty(R.mipmap.no_more_project);
                                    return;
                                }
                                baseBeans = gson.fromJson(response.body(), ProjectShaiXuanListBean.class).getData();
                            }
                            multipleStatusView.showOutContentView(refresh_layout);
                            if (isRefresh) {
                                showRefreshLoading(false);
                                listAdapter.replaceData(baseBeans);
                            } else {
                                refresh_layout.finishLoadMore();
                                listAdapter.addData(baseBeans);
                            }
                            if (baseBeans.size() < 10) {
                                refresh_layout.setEnableLoadMore(false);
                                addCantLoadMoreFooter(listAdapter);
                                listAdapter.loadMoreEnd();
                            } else {
                                listAdapter.loadMoreComplete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private View footer;

    private void addCantLoadMoreFooter(BaseQuickAdapter adapter) {
        if (footer == null) {
            footer = LayoutInflater.from(this).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }


    private class PingLunBiaoQianGridViewAdapter extends RecyclerView.Adapter<PingLunBiaoQianGridViewAdapter.MyViewHolder> {
        private boolean isClick = false;

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ClassifyActivity.this).inflate(R.layout.item_pinglun, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            holder.tv.setText(objects.get(position).getName());
            if (objects.get(position).isSelected()) {
                holder.tv.setBackgroundResource(R.mipmap.classification_shaixuan_xuanze_btn_s);
                holder.tv.setTextColor(0xFF7AD2D2);
            } else {
                holder.tv.setBackgroundResource(R.drawable.btn_line_background);
                holder.tv.setTextColor(0xFF999999);
            }
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (SelectBean object : objects) {
                        object.setSelected(false);
                        holder.tv.setBackgroundResource(R.drawable.btn_line_background);
                        holder.tv.setTextColor(0xFF999999);
                    }
                    page = 1;
                    objects.get(position).setSelected(true);
                    if (isClick) {
                        isCheck = -1;
                    } else {
                        isCheck = position;
                    }
                    notifyDataSetChanged();
                    isClick = !isClick;
                    getData(page, category, isCheck);

                    tvShaixuan.setSelected(tvShaixuan.isSelected() ? false : true);
                    llPop.setVisibility(llPop.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
        }


        @Override
        public int getItemCount() {
            return objects.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.btn_pingjia);
            }

        }
    }

    class SelectBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private boolean isSelected;

        public SelectBean(String name, boolean isSelected) {
            this.name = name;
            this.isSelected = isSelected;
        }
    }

}
