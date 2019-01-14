package com.kekemei.kekemei.activity.splash;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.BaseActivity;
import com.kekemei.kekemei.activity.MainActivity;
import com.kekemei.kekemei.manager.PrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity {
    private static final int[] imageList = {R.mipmap.guide_page_01, R.mipmap.guide_page_02, R.mipmap.guide_page_03};
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_home)
    ImageView btnHome;
    private List<ImageView> viewList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_guide;
    }

    public void initData() {
        initList();
        initViewPager();
    }

    private void initList() {
        viewList = new ArrayList<>();
        int size = imageList.length;
        ImageView imageView = null;
        for (int i = 0; i < size; i++) {
            imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageList[i]);
            viewList.add(imageView);
        }

    }

    @OnClick(R.id.btn_home)
    public void onViewClicked() {
        PrefManager.newInstance(this, PrefManager.FILE_SETTINGS).putBoolean(PrefManager.KEY_NEED_SHOW_GUIDE, false);
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }

    private void initViewPager() {
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(pageChangeListener);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageList.length - 1) {
                btnHome.setVisibility(View.VISIBLE);
            } else {
                btnHome.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    };
}
