package com.kekemei.kekemei.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.kekemei.kekemei.activity.AddCommentActivity;
import com.kekemei.kekemei.activity.LoginActivity;
import com.kekemei.kekemei.activity.OrderDetailActivity;
import com.kekemei.kekemei.activity.OrderListSearchActivity;
import com.kekemei.kekemei.activity.PayActivity;
import com.kekemei.kekemei.activity.ProjectDetailActivity;
import com.kekemei.kekemei.activity.PushOrderActivity;
import com.kekemei.kekemei.adapter.MyGridAdapter;
import com.kekemei.kekemei.adapter.OrderListAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.ForYouBean;
import com.kekemei.kekemei.bean.OrderListBean;
import com.kekemei.kekemei.bean.YuYueActivityBean;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.SPUtils;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * User:Shine
 * Date:2015-10-20
 * Description:
 */
public class OrderFragment extends Fragment {

    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.v_all)
    View vAll;
    @BindView(R.id.tal_all)
    LinearLayout talAll;
    @BindView(R.id.tv_wait_pay)
    TextView tvWaitPay;
    @BindView(R.id.v_wait_pay)
    View vWaitPay;
    @BindView(R.id.tal_wait_pay)
    LinearLayout talWaitPay;
    @BindView(R.id.tv_wait_yuyue)
    TextView tvWaitYuyue;
    @BindView(R.id.v_wait_yuyue)
    View vWaitYuyue;
    @BindView(R.id.tal_wait_yuyue)
    LinearLayout talWaitYuyue;
    @BindView(R.id.tv_wait_server)
    TextView tvWaitServer;
    @BindView(R.id.v_wait_server)
    View vWaitServer;
    @BindView(R.id.tal_wait_server)
    LinearLayout talWaitServer;
    @BindView(R.id.tv_pingjia)
    TextView tvPingjia;
    @BindView(R.id.v_pingjia)
    View vPingjia;
    @BindView(R.id.tal_pingjia)
    LinearLayout talPingjia;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.v_finish)
    View vFinish;
    @BindView(R.id.tal_finish)
    LinearLayout talFinish;

    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.v_quit)
    View vQuit;
    @BindView(R.id.tal_quit)
    LinearLayout talQuit;

    @BindView(R.id.swipe_rfresh_layout)
    SmartRefreshLayout refresh_layout;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rl_list)
    RelativeLayout rlList;
    Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OrderListAdapter jAdapter;
    private RecyclerView rvForYou;

    private ArrayList<OrderListBean.DataBean> arrayList = new ArrayList<OrderListBean.DataBean>();
    private MyGridAdapter forYouAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked(talAll);
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
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(linearLayoutManager);
        jAdapter = new OrderListAdapter(getActivity());
        rvList.setAdapter(jAdapter);
        jAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                final OrderListBean.DataBean item = (OrderListBean.DataBean) adapter.getItem(position);
                YuYueActivityBean yuYueActivityBean = new YuYueActivityBean();
                yuYueActivityBean.setDateSelect(-1L);
                yuYueActivityBean.setTimeSelect(-1);
                yuYueActivityBean.setOrderPrice(item.getPrice());
                yuYueActivityBean.setOrderCount(item.getCount());
                yuYueActivityBean.setOrderIconUrl(item.getImage());
                yuYueActivityBean.setOrderName(item.getName());
                yuYueActivityBean.setOrderId(item.getId());
                yuYueActivityBean.setProject_id(item.getProject_project_id());
                yuYueActivityBean.setOrderCreateTime(item.getCreatetime()+"");
                yuYueActivityBean.setFromDetail(false);
                switch (view.getId()) {
                    case R.id.iv_del_order:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示");
                        builder.setMessage("确定要删除订单?");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                long userId = UserHelp.getUserId(getActivity());
                                if (userId == -1L) {
                                    LoginActivity.start(getActivity());
                                    return;
                                }
                                OkGo.<String>get(URLs.DEL_ORDER)
                                        .params("user_id", userId)
                                        .params("order_id", item.getId())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
//                                                data.remove(position);
                                                jAdapter.remove(position);
//                                                LogUtil.d("AAA", arrayList.toString());
//                                                arrayList.remove(position);
//                                                LogUtil.d("AAA", arrayList.toString());
//                                                jAdapter.setNewData(arrayList);
//                                                jAdapter.notifyDataSetChanged();
                                                onViewClicked(talAll);

                                            }
                                        });
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        break;
                    case R.id.quxiaodingdan:
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("提示");
                        builder1.setMessage("确定要取消订单?");
                        builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                long userId = UserHelp.getUserId(getActivity());
                                if (userId == -1L) {
                                    LoginActivity.start(getActivity());
                                    return;
                                }
                                OkGo.<String>get(URLs.CANCEL_ORDER)
                                        .params("user_id", userId)
                                        .params("order_id", item.getId())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
                                                onViewClicked(talAll);
                                            }
                                        });
                                dialog.dismiss();
                            }
                        });
                        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog1 = builder1.create();
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.show();

                        break;
                    case R.id.lijifukuan:
//                        PayActivity.start(getActivity(), yuYueActivityBean);
                        ProjectDetailActivity.start(getActivity(), item.getProject_project_id());
                        break;
                    case R.id.chakan:
                        OrderDetailActivity.start(getActivity(), item.getId());
                        break;
                    case R.id.zaicigoumai:
//                        ProjectDetailActivity.start(getActivity(), item.getProject_project_id());
                        OkGo.<String>post(URLs.ORDER_CONFIRM)
                                .params("order_id", item.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        onViewClicked(talPingjia);
                                    }
                                });
                        break;
                    case R.id.qupingjia:
                        AddCommentActivity.start(getActivity(), item.getSource(), item.getId() + "");
                        break;
                    case R.id.yuyue:
                        PushOrderActivity.start(getActivity(), yuYueActivityBean);
                        break;
                    case R.id.querenfuwu:
//                        OkGo.<String>post(URLs.ORDER_CONFIRM)
//                                .params("order_id",item.getId())
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onSuccess(Response<String> response) {
//                                        onViewClicked(talPingjia);
//                                    }
//                                });
                        break;

                }
            }
        });
        jAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderListBean.DataBean item = (OrderListBean.DataBean) adapter.getItem(position);
                if (item.getName().equals("会员卡")) return;
                ProjectDetailActivity.start(getActivity(), item.getProject_project_id());
//                OrderDetailActivity.start(getActivity(),item.getId());
            }
        });
        if (!SPUtils.getBoolean(getActivity().getApplicationContext(), SPUtils.SELECT_YUYUE, false)) {
            onViewClicked(talAll);
        } else {
            onViewClicked(talWaitYuyue);
            SPUtils.putBoolean(getActivity().getApplicationContext(),SPUtils.SELECT_YUYUE,false);
        }
    }

    private void getForYouInfo() {
        OkGo.<String>get(URLs.FOR_YOU).params("page", 1).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                ForYouBean forYouBean = gson.fromJson(response.body(), ForYouBean.class);
                forYouAdapter.replaceData(forYouBean.getData());
                if (forYouAdapter.getData().size() > 0) {
                    footView.setVisibility(View.VISIBLE);
                } else {
                    footView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_search, R.id.tal_all, R.id.tal_wait_pay, R.id.tal_wait_yuyue,
            R.id.tal_wait_server, R.id.tal_finish, R.id.tal_pingjia, R.id.tal_quit})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_search) {
            Intent intent = new Intent(getActivity(), OrderListSearchActivity.class);
            startActivity(intent);
            return;
        }
        if (view.getId() == R.id.tal_all || view.getId() == R.id.tal_wait_pay || view.getId() == R.id.tal_quit
                || view.getId() == R.id.tal_wait_yuyue || view.getId() == R.id.tal_wait_server
                || view.getId() == R.id.tal_finish || view.getId() == R.id.tal_pingjia) {
            setSelect(view.getId());
            return;
        }
    }

    private int jOrderStatus;
    private int page = 1;

    private void setSelect(int id) {
        tvAll.setSelected(id == R.id.tal_all);
        vAll.setVisibility(id == R.id.tal_all ? View.VISIBLE : View.INVISIBLE);

        tvWaitPay.setSelected(id == R.id.tal_wait_pay);
        vWaitPay.setVisibility(id == R.id.tal_wait_pay ? View.VISIBLE : View.INVISIBLE);

        tvWaitYuyue.setSelected(id == R.id.tal_wait_yuyue);
        vWaitYuyue.setVisibility(id == R.id.tal_wait_yuyue ? View.VISIBLE : View.INVISIBLE);

        tvWaitServer.setSelected(id == R.id.tal_wait_server);
        vWaitServer.setVisibility(id == R.id.tal_wait_server ? View.VISIBLE : View.INVISIBLE);

        tvFinish.setSelected(id == R.id.tal_finish);
        vFinish.setVisibility(id == R.id.tal_finish ? View.VISIBLE : View.INVISIBLE);

        tvPingjia.setSelected(id == R.id.tal_pingjia);
        vPingjia.setVisibility(id == R.id.tal_pingjia ? View.VISIBLE : View.INVISIBLE);

        tvQuit.setSelected(id == R.id.tal_quit);
        vQuit.setVisibility(id == R.id.tal_quit ? View.VISIBLE : View.INVISIBLE);

        switch (id) {
            case R.id.tal_all:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_ALL;
                break;
            case R.id.tal_wait_pay:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_TO_BE_PAID;
                break;
            case R.id.tal_wait_yuyue:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_TO_BE_APPOINTMENT;
                break;
            case R.id.tal_wait_server:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_TO_WAIT_SERVER;
                break;
            case R.id.tal_finish:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_FINISHED;
                break;
            case R.id.tal_pingjia:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_COMMENT;
                break;
            case R.id.tal_quit:
                page = 1;
                jOrderStatus = OrderListBean.ORDER_STATUS_QUIT;
                break;
        }
        loadData(true);
    }

    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    private void loadMoreData() {
        isLoadMore = true;
        isRefresh = false;
        page++;
        getData(jOrderStatus, page);
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        isLoadMore = false;
        if (isRefresh) {
            page = 1;
            showRefreshLoading(isRefresh);
        }
        getData(jOrderStatus, page);
    }

    public void getData(final int orderStatus, int pageNum) {
        long userId = UserHelp.getUserId(getActivity());
        if (userId == -1L) {
            LoginActivity.start(getActivity());
            return;
        }
        if (!isRefresh && !isLoadMore)
            multipleStatusView.showLoading();
        OkGo.<String>get(URLs.MY_ORDER)
                .tag(this)
                .params("state", OrderListBean.ORDER_STATUS_ALL == orderStatus ? "" : orderStatus + "")
                .params("user_id", userId)
                .params("page", pageNum)
                .execute(new StringCallback() {
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
                            multipleStatusView.showOutContentView(refresh_layout);
                            Gson gson = new Gson();
                            OrderListBean orderListBean = gson.fromJson(response.body(), OrderListBean.class);
                            if (isRefresh) {
                                showRefreshLoading(false);
                                jAdapter.replaceData(orderListBean.getData());
                            } else {
                                refresh_layout.finishLoadMore();
                                jAdapter.addData(orderListBean.getData());
                            }
                            if (orderListBean.getData().size() < 10) {
                                refresh_layout.setEnableLoadMore(false);
                                jAdapter.loadMoreEnd();
                                addHotProject();
                                getForYouInfo();//请求推荐数据放在此处，否则没有订单也会请求到数据
                            } else {
                                jAdapter.loadMoreComplete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("TAGE", response.message());
                        multipleStatusView.showError();
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

    private View footView;

    private void addHotProject() {
        if (footView != null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            footView = getLayoutInflater().inflate(R.layout.foot_view, (ViewGroup) rvList.getParent(), false);
        } else {
            footView = LayoutInflater.from(getActivity()).inflate(R.layout.foot_view, (ViewGroup) rvList.getParent(), false);
        }
        footView.setVisibility(View.GONE);
        jAdapter.addFooterView(footView);
        rvForYou = footView.findViewById(R.id.rv_hot_huodong);
        rvForYou.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvForYou.setHasFixedSize(true);
        rvForYou.setNestedScrollingEnabled(false);
        forYouAdapter = new MyGridAdapter(getActivity(), MyGridAdapter.ORDER_HOT_BEAN);
        rvForYou.setAdapter(forYouAdapter);

        forYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean item = (BaseBean) adapter.getItem(position);
                ProjectDetailActivity.start(getActivity(), item.getProject_category_id());
            }
        });
    }

}
