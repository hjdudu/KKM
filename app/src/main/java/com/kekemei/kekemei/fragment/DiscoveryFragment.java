package com.kekemei.kekemei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.ClassifyActivity;
import com.kekemei.kekemei.activity.ProjectDetailActivity;
import com.kekemei.kekemei.activity.ShopActivity;
import com.kekemei.kekemei.activity.WebActivity;
import com.kekemei.kekemei.adapter.ProjectListAdapter;
import com.kekemei.kekemei.bean.BannerBean;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.ProjectListBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DiscoveryFragment extends Fragment {
    private XBanner xbanner;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;

    @BindView(R.id.text_msg)
    TextView textMsg;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.fenlei)
    ImageView fenlei;
    Unbinder unbinder;
    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.ll_fanhui)
    LinearLayout llFanhui;
    private ProjectListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_discovery, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        initData();
        return inflate;
    }

    private void initData() {
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        multipleStatusView.showOutContentView(refresh_layout);
        refresh_layout.setRefreshHeader(new ClassicsHeader(getActivity()));
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setHasFixedSize(true);
        rvList.setNestedScrollingEnabled(false);
        rvList.setLayoutManager(linearLayoutManager);
        listAdapter = new ProjectListAdapter(getActivity());
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = (BaseBean) adapter.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());
            }
        });
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_xbanner_header, null);
        xbanner = header.findViewById(R.id.xbanner);
        listAdapter.addHeaderView(header);
        rvList.setAdapter(listAdapter);
        getData(page);
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

    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    private void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        page++;
        getData(page);
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        isLoadMore = false;
        if (isRefresh) {
            page = 1;
            showRefreshLoading(isRefresh);
        }
        getData(page);
    }

    private int page = 1;

    private void getData(final int page) {
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        OkGo.<String>post(URLs.DISCOVE).params("page", page).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    String data = jsonObject.optString("data");
                    if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                        multipleStatusView.showEmpty(R.mipmap.default_dingdan);
                        return;
                    }
                    Gson gson = new Gson();
                    ProjectListBean projectListBean = gson.fromJson(response.body(), ProjectListBean.class);
                    multipleStatusView.showOutContentView(refresh_layout);
                    initBanner();
                    xbanner.setData(projectListBean.getData().getBanner(), null);
                    if (isRefresh) {
                        showRefreshLoading(false);
                        listAdapter.replaceData(projectListBean.getData().getData());
                    } else {
                        refresh_layout.finishLoadMore();
                        listAdapter.addData(projectListBean.getData().getData());
                    }
                    if (projectListBean.getData().getData().size() < 10) {
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
            footer = LayoutInflater.from(getActivity()).inflate(R.layout.layout_list_no_more_footer, null);
            adapter.addFooterView(footer);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fenlei)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ClassifyActivity.class));
    }
}
