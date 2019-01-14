package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 秒杀页面ViewPager的adapter
 */
public class MiaoshaPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<Fragment>();//存储fragment的集合
    private List<String> titles = new ArrayList<String>();//tab的标题内容

    public MiaoshaPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragments==null || fragments.size()==0? null: fragments.get(position);
    }

    @Override
    public int getCount() {

        return fragments==null? 0: fragments.size();
    }

    public void addTitle(String title) {
        titles.add(title);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    /**
     * 必须重写，否则PagerSlidingTabStrip报错
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        int index = fragments.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    public void clear() {
        if(fragments != null) {
            fragments.clear();
        }
        if(titles != null) {
            titles.clear();
        }
    }
}
