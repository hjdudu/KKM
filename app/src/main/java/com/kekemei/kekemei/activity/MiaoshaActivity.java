package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.MiaoshaPagerAdapter;
import com.kekemei.kekemei.bean.BannerBean;
import com.kekemei.kekemei.bean.DetailEnum;
import com.kekemei.kekemei.bean.MiaoshaBean;
import com.kekemei.kekemei.fragment.IPage;
import com.kekemei.kekemei.fragment.MiaoshaFragment;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.view.MultipleStatusView;
import com.kekemei.kekemei.view.PagerSlidingTabStrip;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

/**
 * 抢购页面
 */
public class MiaoshaActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    public static final String TAG = MiaoshaActivity.class.getSimpleName();
    public static final String KEY_SKU_ID = "item_id";
    public static final String KEY_GROUP_ID = "groupId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.miaoshaTabs)
    PagerSlidingTabStrip miaoshaTabs;
    @BindView(R.id.rushHallPager)
    ViewPager pagers;

    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.contentLayout)
    LinearLayout contentLayout;

    private MiaoshaPagerAdapter adapter;//抢购页面ViewPager的adapter

    private int curPosition = 0;

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MiaoshaActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, String skuId, String groupId) {
        Intent intent = new Intent(context, MiaoshaActivity.class);
        intent.putExtra(KEY_SKU_ID, skuId);
        intent.putExtra(KEY_GROUP_ID, groupId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_miaosha;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setBackgroundColor(Color.parseColor("#10000000"));
        tv_title.setText("秒杀专场");

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

        multipleStatusView.showOutContentView(contentLayout);

        adapter = new MiaoshaPagerAdapter(this, getSupportFragmentManager());
        pagers.addOnPageChangeListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(URLs.SECOND).params("id", "1").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                multipleStatusView.showOutContentView(contentLayout);
                LogUtil.e("miaosha", "body:" + response.body());
                Gson gson = new Gson();
                MiaoshaBean miaoShaBean = gson.fromJson(response.body(), MiaoshaBean.class);
                xbanner.setData(miaoShaBean.getData().getBanner(), null);
                initBanner();
                fillData(miaoShaBean.getData().getSecond());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                multipleStatusView.showError();
            }
        });
    }

    private void fillData(List<MiaoshaBean.DataBean.SecondBean> tabsBean) {
        adapter.clear();
        if (CollectionUtils.isEmpty(tabsBean)) {
            return;
        }

        for (int i = 0; i < tabsBean.size(); i++) {
            MiaoshaBean.DataBean.SecondBean tab = tabsBean.get(i);
            if (tab != null) {
//                String tabText = tab.getTimeTxt() + "\n" + tab.getStatusTxt();
                if (i == 0) {
                    String tabText = tab.getName() + "\n" + "抢购进行中";
                    adapter.addTitle(tabText);
                } else {
                    String tabText = tab.getName() + "\n" + "即将开始";
                    adapter.addTitle(tabText);
                }
                boolean lastGroup = (i == tabsBean.size() - 1);
                adapter.addFragment(MiaoshaFragment.newInstance(tabsBean.get(i).getProject_data()));
            }
        }

        // http://www.cnblogs.com/sklww/p/3361074.html
        // 如果adapter被set到ViewPager之后，数据有变化的话，需要在每次setAdapter之前调用notifyDataSetChanged
        adapter.notifyDataSetChanged();
        pagers.setAdapter(adapter);
        pagers.setOffscreenPageLimit(tabsBean.size() - 1);
        miaoshaTabs.setViewPager(pagers);

        pagers.setCurrentItem(curPosition, true);
        // 第一次进入秒杀列表的时候在onResume时还未初始化Fragment，需要在这里调用setOnEnter
        setOnEnter(curPosition);
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
                        ShopActivity.start(MiaoshaActivity.this, bannerBean.getShop_shop_id() + "", DetailEnum.SHOP);
                        break;
                    case "project":
                        ProjectDetailActivity.start(MiaoshaActivity.this, bannerBean.getProject_project_id() + "");
                        break;
                    case "web":
                        WebActivity.start(MiaoshaActivity.this,bannerBean.getUrl());
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setOnLeave(curPosition);
        curPosition = position;
        setOnEnter(curPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 通知指定页触发离开页面事件。页面要实现IPage接口
     *
     * @param position 页的索引
     */
    private void setOnLeave(int position) {
        LogUtil.d(TAG, "setOnLeave position is " + position);
        Fragment fragment = getCurrentFragment(position);
        if (fragment != null && fragment instanceof IPage) {
            IPage page = (IPage) fragment;
            page.onLeave();
        }
    }

    /**
     * 通知指定页触发进入事件。页面要实现IPage接口
     *
     * @param position 页的索引
     */
    private void setOnEnter(int position) {
        LogUtil.d(TAG, "setOnEnter position is " + position);
        Fragment fragment = getCurrentFragment(position);
        if (fragment != null && fragment instanceof IPage) {
            IPage page = (IPage) fragment;
            page.onEnter();
        }
    }

    private Fragment getCurrentFragment(int position) {
        try {
            if (pagers == null || pagers.getAdapter() == null) {
                return null;
            }
            MiaoshaPagerAdapter homeAdapter = (MiaoshaPagerAdapter) pagers.getAdapter();
            return homeAdapter.getItem(position);
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setOnEnter(curPosition);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setOnLeave(curPosition);
    }
}
