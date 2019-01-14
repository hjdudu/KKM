package com.kekemei.kekemei.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.ProjectDetailActivity;
import com.kekemei.kekemei.adapter.MiaoshaListAdapter;
import com.kekemei.kekemei.bean.BaseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 秒杀页面的Fragment
 */
public class MiaoshaFragment extends Fragment {
    public static final String TAG = MiaoshaFragment.class.getSimpleName();

    @BindView(R.id.miaoshaRv)
    RecyclerView miaoshaRv;

    private Unbinder unbinder;
    private List<BaseBean> projectData;

    public void setProjectData(List<BaseBean> projectData) {
        this.projectData = projectData;
    }

    public static MiaoshaFragment newInstance(List<BaseBean> project_data) {
        MiaoshaFragment fragment = new MiaoshaFragment();
        fragment.setProjectData(project_data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_miaosha, container, false);
        unbinder = ButterKnife.bind(this, view);

        miaoshaRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MiaoshaListAdapter adapter = new MiaoshaListAdapter(getActivity(), R.layout.item_rush_hall_list_layout, projectData);
        miaoshaRv.setHasFixedSize(true);
        miaoshaRv.setNestedScrollingEnabled(false);
        miaoshaRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseBean item = (BaseBean) adapter.getItem(position);
                ProjectDetailActivity.start(getActivity(), item.getId());
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
