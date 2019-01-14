package com.kekemei.kekemei.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.CreditAdapter;
import com.kekemei.kekemei.bean.CreditBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 积分页面
 */

public class CreditActivity extends BaseActivity {
    @BindView(R.id.rv_jifen_list)
    RecyclerView rvJifenList;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CreditAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_credit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText("我的积分");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();


        CreditBean jiFenBean = new CreditBean();
        jiFenBean.setZongfen("192929");
        ArrayList<CreditBean.JIFEN> jifens = new ArrayList<>();
        CreditBean.JIFEN e = new CreditBean.JIFEN();
        for (int i = 0; i < 10; i++) {

            e.setData("2019-12-12   12:30");
            e.setPrice("+  10.00");
            jifens.add(e);
        }
        jiFenBean.setJifens(jifens);
        adapter = new CreditAdapter();
        rvJifenList.setLayoutManager(new LinearLayoutManager(this));
        rvJifenList.setAdapter(adapter);
        rvJifenList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setNewData(jiFenBean.getJifens());
        View header_view = getLayoutInflater().inflate(R.layout.hear_jifen, (ViewGroup) rvJifenList.getParent(), false);
        adapter.addHeaderView(header_view);
        TextView tv_jifennum = header_view.findViewById(R.id.tv_jifennum);
        tv_jifennum.setText(jiFenBean.getZongfen());
    }
}
