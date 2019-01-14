package com.kekemei.kekemei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.ChatActivity;
import com.kekemei.kekemei.activity.CreditActivity;
import com.kekemei.kekemei.activity.LoginActivity;
import com.kekemei.kekemei.activity.MainActivity;
import com.kekemei.kekemei.activity.MemberActivity;
import com.kekemei.kekemei.activity.MessageActivity;
import com.kekemei.kekemei.activity.MyCollectionActivity;
import com.kekemei.kekemei.activity.MyRedBaoActivity;
import com.kekemei.kekemei.activity.MyVoucherActivity;
import com.kekemei.kekemei.activity.NewComerActivity;
import com.kekemei.kekemei.activity.ProjectDetailActivity;
import com.kekemei.kekemei.activity.ServiceOrderListActivity;
import com.kekemei.kekemei.activity.SettingActivity;
import com.kekemei.kekemei.activity.UserInfoActivity;
import com.kekemei.kekemei.adapter.GridAdapter;
import com.kekemei.kekemei.adapter.MyGridAdapter;
import com.kekemei.kekemei.bean.BaseBean;
import com.kekemei.kekemei.bean.ForYouBean;
import com.kekemei.kekemei.bean.MyInfoBean;
import com.kekemei.kekemei.bean.UserBean;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CircleImageView;
import com.kekemei.kekemei.view.NoScrollGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的页面
 */
public class PersonFragment extends Fragment {

    @BindView(R.id.top_bg)
    View topBg;
    @BindView(R.id.icon)
    CircleImageView icon;

    Unbinder unbinder;
    @BindView(R.id.ncgv)
    NoScrollGridView ncgv;
    @BindView(R.id.tiyan)
    LinearLayout tiyan;
    @BindView(R.id.daijinquan)
    LinearLayout daijinquan;
    @BindView(R.id.hongbao)
    LinearLayout hongbao;
    @BindView(R.id.ll_foryou)
    LinearLayout llForyou;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;
    @BindView(R.id.user_set_btn)
    ImageView userSetBtn;
    @BindView(R.id.user_message_btn)
    ImageView userMessageBtn;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.tv_tiyannum)
    TextView tvTiyannum;
    @BindView(R.id.tv_daijinnum)
    TextView tvDaijinnum;
    @BindView(R.id.tv_hongbaonum)
    TextView tvHongbaonum;

    private String[] userForwardArray = {"服务订单", "关注的店铺", "我的美容师", "我的收藏",
            "我的订单", "我的积分", "会员优惠", "客户服务"};
    private int[] userForwardIconArray = {
            R.mipmap.user_haoyou_btn, R.mipmap.user_dianpu_btn,
            R.mipmap.user_meirongshi_btn, R.mipmap.user_soucang_btn,
            R.mipmap.user_dingdan_btn, R.mipmap.user_dizhi_btn,
            R.mipmap.user_qianbao_btn, R.mipmap.user_kefu_btn};
    private GridAdapter gridAdapter;

    private MyGridAdapter adapter;
    private MyInfoBean myInfoBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + UserHelp.getAvatar(getActivity()), icon);
        userName.setText(UserHelp.getUserName(getActivity()));

        ncgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!gridAdapter.getItem(0).getUserName().equals(userForwardArray[0])) {
                    i += 1;
                }
                switch (i) {
                    case 0:
                        ServiceOrderListActivity.start(getActivity());
                        break;
                    case 1:
                        MyCollectionActivity.start(getActivity(), "2", true);
                        break;
                    case 2:
                        MyCollectionActivity.start(getActivity(), "", false);
                        break;
                    case 3:
                        MyCollectionActivity.start(getActivity(), "1", true);
                        break;
                    case 4:
                        MainActivity.start(getActivity(), 2);
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), CreditActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), MemberActivity.class));
                        break;
                    case 7:
                        if (myInfoBean == null) return;
                        ChatActivity.start(getActivity(),myInfoBean.getData().getAdmin());
                        break;
                }
            }
        });

        rvTuijian.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvTuijian.setLayoutManager(gridLayoutManager);

        adapter = new MyGridAdapter(getActivity(), MyGridAdapter.PERSON_TUI_JIAN);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean data = (BaseBean) adapter.getItem(position);
                ProjectDetailActivity.start(getActivity(), data.getId());

            }
        });
        rvTuijian.setAdapter(adapter);
    }

    private void initData() {
        long userId = UserHelp.getUserId(getActivity());
        if (userId == -1L) {
            LoginActivity.start(getActivity());
            return;
        }

        final List<UserBean> list = new ArrayList<>();
        OkGo.<String>get(URLs.MY_INFO).params("user_id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        myInfoBean = gson.fromJson(response.body(), MyInfoBean.class);
                        if (myInfoBean != null && myInfoBean.getData() != null) {
                            tvHongbaonum.setText(String.valueOf(myInfoBean.getData().getRedenvelopes_count()));
                            tvDaijinnum.setText(String.valueOf(myInfoBean.getData().getCoupon_count()));
                            if (myInfoBean.getData().getIsbeautician() == 1) {
                                for (int i = 0; i < userForwardArray.length; i++) {
                                    UserBean model = new UserBean(userForwardArray[i], userForwardIconArray[i]);
                                    list.add(model);
                                }
                            } else {
                                for (int i = 1; i < userForwardArray.length; i++) {
                                    UserBean model = new UserBean(userForwardArray[i], userForwardIconArray[i]);
                                    list.add(model);
                                }
                            }
                            gridAdapter = new GridAdapter(getActivity(), list);
                            ncgv.setAdapter(gridAdapter);
                        }
                    }
                });

        geForYoutData();
    }

    private void geForYoutData() {
        OkGo.<String>get(URLs.FOR_YOU).params("page", 1).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                ForYouBean forYouBean = gson.fromJson(response.body(), ForYouBean.class);
                if (forYouBean.getCode() == 1 && forYouBean.getData().size() != 0) {
                    llForyou.setVisibility(View.VISIBLE);
                } else {
                    llForyou.setVisibility(View.GONE);
                    return;
                }
                adapter.setNewData(forYouBean.getData());
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @OnClick({R.id.user_set_btn, R.id.userName, R.id.icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_set_btn:
                SettingActivity.start(getActivity());
                break;
            case R.id.icon:
                LoginActivity.start(getActivity());
                break;
            case R.id.userName:
                long userId = UserHelp.getUserId(getActivity());
                if (userId == -1L) {
                    LoginActivity.start(getActivity());
                    return;
                }
                UserInfoActivity.start(getActivity(), userId + "");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tiyan, R.id.daijinquan, R.id.hongbao, R.id.user_message_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiyan:
                NewComerActivity.start(getActivity(), true);
                break;
            case R.id.daijinquan:
                MyVoucherActivity.start(getActivity(),true);
                break;
            case R.id.hongbao:
                MyRedBaoActivity.start(getActivity(),true);
                break;
            case R.id.user_message_btn:
                MessageActivity.start(getActivity());
                break;
        }
    }

}
