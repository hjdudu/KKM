package com.kekemei.kekemei.adapter.base;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kekemei.kekemei.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peiyangfan on 2018/10/24.
 */

public class BasePagerAdapter extends FragmentPagerAdapter{
    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();

    private String[] titles;
    public BasePagerAdapter(Context context, FragmentManager fm, int search_tabs) {
        super(fm);
        titles = context.getResources().getStringArray(search_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null || fragments.size() == 0
                ? null
                : fragments.get(position);
    }
    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }
    @Override
    public int getCount() {
        return fragments == null
                ? 0
                : fragments.size();
    }
    /**
     * 必须重写，否则PagerSlidingTabStrip报错
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
