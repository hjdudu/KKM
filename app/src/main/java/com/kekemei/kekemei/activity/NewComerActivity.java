package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.MyGridAdapter;
import com.kekemei.kekemei.adapter.ProjectListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.NewComerBean;
import com.kekemei.kekemei.bean.NewMemberBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
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
 * 新人体验
 */
public class NewComerActivity extends BaseActivity {
    public static final String TAG = NewComerActivity.class.getSimpleName();
    private static final String EXTRA_KEY_NEW_USER = "newUser";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_share)
    ImageView iv_share;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.scrollContent)
    ScrollView scrollContent;
    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;
    @BindView(R.id.newPeopleList)
    RecyclerView newPeopleList;
    private ProjectListAdapter listAdapter;

    @BindView(R.id.topBanner)
    ImageView topBanner;

    @BindView(R.id.allProjectLayout)
    LinearLayout allProjectLayout;
    @BindView(R.id.sectionAllRv)
    RecyclerView sectionAllRv;
    @BindView(R.id.lookMoreAll)
    TextView lookMoreAll;

    @BindView(R.id.forYouLayout)
    LinearLayout forYouLayout;
    @BindView(R.id.sectionForYouRv)
    RecyclerView sectionForYouRv;

    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;

    private MyGridAdapter allAdapter, forYouAdapter;

    private boolean isNewComer;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int jPageNum = 1;

    private NewComerBean newComerBean;
    private NewMemberBean newMemberBean;

    public static void start(Context context, boolean isNewComer) {
        Intent intent = new Intent(context, NewComerActivity.class);
        intent.putExtra(EXTRA_KEY_NEW_USER, isNewComer);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_new_comer;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        isNewComer = getIntent().getBooleanExtra(EXTRA_KEY_NEW_USER, false);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setBackgroundColor(Color.parseColor("#00000000"));
        tv_title.setText(isNewComer ? "超值体验" : "会员专区");
        iv_share.setVisibility(View.VISIBLE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNewComer) {
                    if (newComerBean != null && newComerBean.getBanner() != null) {
                        AppUtil.shareUm(NewComerActivity.this,
                                newComerBean.getBanner().getTitle(),
                                newComerBean.getBanner().getContent(),
                                URLs.BASE_URL + newComerBean.getBanner().getImage(),
                                URLs.SHARE_BEA_URL + newComerBean.getBanner().getId());
                    }
                } else {
                    if (newMemberBean != null && newMemberBean.getBanner() != null) {
                        AppUtil.shareUm(NewComerActivity.this,
                                newMemberBean.getBanner().getTitle(),
                                newMemberBean.getBanner().getContent(),
                                URLs.BASE_URL + newMemberBean.getBanner().getImage(),
                                URLs.SHARE_BEA_URL + newMemberBean.getBanner().getId());
                    }
                }
            }
        });

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        multipleStatusView.showOutContentView(scrollContent);

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

        sectionAllRv.setLayoutManager(new GridLayoutManager(this, 2));
        sectionAllRv.setHasFixedSize(true);
        sectionAllRv.setNestedScrollingEnabled(false);

        sectionForYouRv.setLayoutManager(new GridLayoutManager(this, 2));
        sectionForYouRv.setHasFixedSize(true);
        sectionForYouRv.setNestedScrollingEnabled(false);

        allAdapter = new MyGridAdapter(this, MyGridAdapter.HotdataBean);
        sectionAllRv.setAdapter(allAdapter);
        allAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("section", "click:" + position);
                BaseBean item = allAdapter.getItem(position);
                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
            }
        });

        forYouAdapter = new MyGridAdapter(this, MyGridAdapter.HotdataBean);
        sectionForYouRv.setAdapter(forYouAdapter);
        forYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("section", "click:" + position);
                BaseBean item = forYouAdapter.getItem(position);
                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        multipleStatusView.showLoading();
        loadData(true);
    }

    @OnClick({R.id.lookMoreAll, R.id.tvAllProject, R.id.onLineService, R.id.buyNow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.onLineService:
                if (newComerBean != null && newComerBean.getAdmin() != null) {
                    ChatActivity.start(this, newComerBean.getAdmin());
                }
                break;
            case R.id.buyNow:
                OkGo.<String>get(URLs.ADD_NEW_PEOPLE).params("user_id", UserHelp.getUserId(NewComerActivity.this))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("暂无数据")) {
                                        ToastUtil.showToastMsg(NewComerActivity.this, "领取失败");
                                        return;
                                    }
                                    ToastUtil.showToastMsg(NewComerActivity.this, msg.toString());
                                    initData();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                ToastUtil.showToastMsg(NewComerActivity.this, "领取失败");
                            }
                        });
                break;
            case R.id.tvAllProject:
            case R.id.lookMoreAll:
                ProjectListActivity.start(this, "");
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
        long userId = UserHelp.getUserId(this);
        if (userId == -1L) {
            LoginActivity.start(this);
            return;
        }
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        OkGo.<String>post(isNewComer ? URLs.PROJECT_NEW_PEOPLE : URLs.NEW_MEMBER_PEOPLE).params("user_id", UserHelp.getUserId(this)).params("page", pageNum).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.e(TAG, "response:" + response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    String data = jsonObject.optString("data");
                    if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                        if (isNewComer) {
                            onComerResult(null);
                        } else {
                            onMemberResult(null);
                        }
                        return;
                    }
                    Gson gson = new Gson();
                    if (isNewComer) {
                        newComerBean = gson.fromJson(data, NewComerBean.class);
                        onComerResult(newComerBean);
                    } else {
                        newMemberBean = gson.fromJson(data, NewMemberBean.class);
                        onMemberResult(newMemberBean);
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

    public void onComerResult(Object response) {
        if (!isLoadMore) {
            jPageNum++;

            NewComerBean newComerBean = (NewComerBean) response;

            if (null == response || null == newComerBean) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                if (newComerBean.getIsnew() == 0) {
                    scrollContent.setVisibility(View.VISIBLE);
                    bottomBar.setVisibility(View.VISIBLE);
                    multipleStatusView.showOutContentView(scrollContent);
                    ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + newComerBean.getBanner().getImage(), topBanner);
                    if (CollectionUtils.isNotEmpty(newComerBean.getProjectall())) {
                        allAdapter.replaceData(newComerBean.getProjectall());
                        allAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                LogUtil.e("section", "click:" + position);
                                BaseBean item = allAdapter.getItem(position);
                                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
                            }
                        });
                    }
                    if (CollectionUtils.isNotEmpty(newComerBean.getForyou())) {
                        forYouAdapter.replaceData(newComerBean.getForyou());
                        forYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                LogUtil.e("section", "click:" + position);
                                BaseBean item = forYouAdapter.getItem(position);
                                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
                            }
                        });
                    }
                } else {
                    toolbar.setBackgroundColor(Color.parseColor("#7AD2D2"));
                    scrollContent.setVisibility(View.GONE);
                    bottomBar.setVisibility(View.GONE);
                    multipleStatusView.showOutContentView(refresh_layout);
                    if (CollectionUtils.isNotEmpty(newComerBean.getNewpopledata())) {
                        listAdapter.replaceData(newComerBean.getNewpopledata());
                        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                LogUtil.e("section", "click:" + position);
                                BaseBean item = listAdapter.getItem(position);
                                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
                            }
                        });
                    }
                }
            }

            if (null != response && newComerBean.getNewpopledata().size() < 10)
                showLoadMoreEnd();
            else
                showLoadMoreComplete();
        } else {
            jPageNum++;
            NewComerBean newComerBean = (NewComerBean) response;
            loadMoreSuccess(newComerBean.getNewpopledata());
            if (newComerBean.getNewpopledata().size() < 10) {
                showLoadMoreEnd();
            } else {
                showLoadMoreComplete();
            }
        }
    }

    public void onMemberResult(Object response) {
        if (!isLoadMore) {
            jPageNum++;

            NewMemberBean newMemberBean = (NewMemberBean) response;

            if (null == response || null == newMemberBean) {
                showEmpty();
            } else {
                if (isRefresh)
                    showRefreshLoading(false);
                if (newMemberBean.getIsmember() == 0) {
                    scrollContent.setVisibility(View.VISIBLE);
                    multipleStatusView.showOutContentView(scrollContent);
                    ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + newMemberBean.getBanner().getImage(), topBanner);
                    if (CollectionUtils.isNotEmpty(newMemberBean.getProjectall())) {
                        allAdapter.replaceData(newMemberBean.getProjectall());
                    }
                    if (CollectionUtils.isNotEmpty(newMemberBean.getForyou())) {
                        forYouAdapter.replaceData(newMemberBean.getForyou());
                    }
                } else {
                    toolbar.setBackgroundColor(Color.parseColor("#7AD2D2"));
                    scrollContent.setVisibility(View.GONE);
                    multipleStatusView.showOutContentView(refresh_layout);
                    if (CollectionUtils.isNotEmpty(newMemberBean.getMember())) {
                        listAdapter.replaceData(newMemberBean.getMember());
                        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                LogUtil.e("section", "click:" + position);
                                BaseBean item = listAdapter.getItem(position);
                                ProjectDetailActivity.start(NewComerActivity.this, item.getId());
                            }
                        });
                    }
                }
            }

            if (null != response && newMemberBean.getMember().size() < 10)
                showLoadMoreEnd();
            else
                showLoadMoreComplete();
        } else {
            jPageNum++;
            NewMemberBean newMemberBean = (NewMemberBean) response;
            loadMoreSuccess(newMemberBean.getMember());
            if (newMemberBean.getMember().size() < 10) {
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
