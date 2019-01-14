package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.fragment.DiscoveryFragment;
import com.kekemei.kekemei.fragment.HomeFragment;
import com.kekemei.kekemei.fragment.OrderFragment;
import com.kekemei.kekemei.fragment.PersonFragment;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.startsmake.mainnavigatetabbar.widget.MainNavigateTabBar;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    public static final int TAB_HOME = 0;
    private static final String KEY_TAB = "tab";

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_CITY = "发现";
    //    private static final String TAG_PAGE_PUBLISH = "预约";
    private static final String TAG_PAGE_MESSAGE = "订单";
    private static final String TAG_PAGE_PERSON = "我的";

    @BindView(R.id.mainTabBar)
    MainNavigateTabBar mNavigateTabBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private int mCurrentTab = TAB_HOME;
    private boolean hasTitle = false;
    private boolean hasCoupons = false;
    private Button iv_btn_lingqu;
    private AlertDialog dlg;

    private LinearLayout llOneRed;
    private LinearLayout llTowRed;
    private LinearLayout llThrRed;
    private TextView tvOneName, tvOneNeirong, tvNameOne2, tvNeirongOne2, tvNeirongTow2, tvNameTow2, tvNameOne3, tvNeirongOne3, tvNameTow3, tvNeirongTow3, tvNameThr3, tvNeirongThr3;
    private long isNew;
    private ImageView iv_close;

    public static void start(Context context, int tab) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_TAB, tab);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getIntentData(intent);
    }

    private void getIntentData(Intent intent) {
        if (intent != null) {
            mCurrentTab = intent.getIntExtra(KEY_TAB, mCurrentTab);
            mNavigateTabBar.setCurrentSelectedTab(mCurrentTab);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isNew = UserHelp.getIsNew(getBaseContext());
        if (isNew != 1) {
            showDIYDialog(3);
        }
    }

    public void showDIYDialog(int a) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog);
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.activity_coupons, null);//获取自定义布局
        builder.setView(layout);

        initDialogViiew(layout, a);
        iv_btn_lingqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long userId = UserHelp.getUserId(MainActivity.this);
                if (isNew == -1 || userId == -1L) {
                    LoginActivity.start(MainActivity.this);
                    dlg.dismiss();
                    return;
                }
                OkGo.<String>get(URLs.COUPON_ONE_RECEIVE)
                        .params("user_id", userId)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response.body());
                                    Object msg = jsonObject.opt("msg");
                                    if (msg.equals("你已领取了红包")) {
                                        UserHelp.setIsNew(MainActivity.this, 1);
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                dlg.dismiss();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        dlg = builder.create();
        dlg.setCanceledOnTouchOutside(false);
        dlg.show();
    }

    private void initDialogViiew(View layout, int type) {
        llOneRed = layout.findViewById(R.id.ll_one_red);
        llTowRed = layout.findViewById(R.id.ll_tow_red);
        llThrRed = layout.findViewById(R.id.ll_thr_red);
        tvOneName = layout.findViewById(R.id.tv_one_name);
        tvNameTow2 = layout.findViewById(R.id.tv_name_tow_2);
        tvNameOne2 = layout.findViewById(R.id.tv_name_one_2);
        tvNameOne3 = layout.findViewById(R.id.tv_name_one_3);
        tvNameTow3 = layout.findViewById(R.id.tv_name_tow_3);
        tvNameThr3 = layout.findViewById(R.id.tv_name_thr_3);

        tvOneNeirong = layout.findViewById(R.id.tv_one_neirong);
        tvNeirongOne2 = layout.findViewById(R.id.tv_neirong_one_2);
        tvNeirongTow2 = layout.findViewById(R.id.tv_neirong_tow_2);
        tvNeirongOne3 = layout.findViewById(R.id.tv_neirong_one_3);
        tvNeirongTow3 = layout.findViewById(R.id.tv_neirong_tow_3);
        tvNeirongThr3 = layout.findViewById(R.id.tv_neirong_thr_3);
        iv_btn_lingqu = (Button)layout.findViewById(R.id.iv_btn_lingqu);
        iv_close = (ImageView) layout.findViewById(R.id.iv_close);


        switch (type) {
            case 1:
                llOneRed.setVisibility(View.VISIBLE);
                llTowRed.setVisibility(View.GONE);
                llThrRed.setVisibility(View.GONE);
                break;
            case 2:
                llOneRed.setVisibility(View.GONE);
                llTowRed.setVisibility(View.VISIBLE);
                llThrRed.setVisibility(View.GONE);
                break;
            case 3:
                llOneRed.setVisibility(View.GONE);
                llTowRed.setVisibility(View.GONE);
                llThrRed.setVisibility(View.VISIBLE);
                break;
        }

        setText();
    }

    private void setText() {

//        tvOneName.setText(strOneName);
//        tvOneNeirong.setText(strOneNeirong);
//        tvNameOne2.setText(strNameOne2);
//        tvNeirongOne2.setText(strNeirongOne2);
//        tvNameTow2.setText(strNameTow2);
//        tvNeirongTow2.setText(strNeirongTow2);
//
//        tvNameOne3.setText(strNameOne3);
//        tvNeirongOne3.setText(strNeirongOne3);
//        tvNameTow3.setText(strNameTow3);
//        tvNeirongTow3.setText(strNeirongTow3);
//        tvNameThr3.setText(strNameThr3);
//        tvNeirongThr3.setText(strNeirongThr3);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        mNavigateTabBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mNavigateTabBarAddTab();
    }

    private void mNavigateTabBarAddTab() {
        mNavigateTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.home_shouye_btn_n, R.mipmap.home_shouye_btn_d, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(DiscoveryFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.home_faxian_btn_n, R.mipmap.home_faxian_btn_d, TAG_PAGE_CITY));
        //        mNavigateTabBar.addTab(null, new MainNavigateTabBar.TabParam(0, 0, TAG_PAGE_PUBLISH));
        mNavigateTabBar.addTab(OrderFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.home_dingdan_btn_n, R.mipmap.home_dingdan_btn_d, TAG_PAGE_MESSAGE));
        mNavigateTabBar.addTab(PersonFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.home_wode_btn_n, R.mipmap.home_wode_btn_d, TAG_PAGE_PERSON));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long lastTime;

    @Override
    public void onBackPressed() {//@TODO 此方法无效
        if (mCurrentTab != TAB_HOME) {
            mCurrentTab = TAB_HOME;
            mNavigateTabBar.setCurrentSelectedTab(mCurrentTab);
            return;
        }
        if (dlg != null && dlg.isShowing()) {
            dlg.dismiss();
        }
        long currentTime = System.currentTimeMillis();
        long period = currentTime - lastTime;
        if (period < 2 * 1000) {
            super.onBackPressed();
            this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            ToastUtil.showToastMsg(this, "再按一次退出");
            lastTime = currentTime;
        }
    }
}
