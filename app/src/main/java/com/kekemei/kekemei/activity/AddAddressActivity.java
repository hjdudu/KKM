package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.BaseActivity;

import butterknife.BindView;

/**
 * 地址详情页
 */
public class AddAddressActivity extends BaseActivity {
    public static final String TAG = AddAddressActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.txtLocSelector)
    TextView txtLocSelector;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editPhoneNum)
    EditText editPhoneNum;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("新增地址");
        tv_submit.setVisibility(View.VISIBLE);
    }
}
