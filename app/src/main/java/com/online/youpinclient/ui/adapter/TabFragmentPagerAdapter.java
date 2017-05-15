package com.online.youpinclient.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by permanent love on 2017/4/19.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> tabTitleList;
    private ArrayList<Fragment> tabFragment;

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> tabFragment) {
        super(fm);
        this.tabFragment = tabFragment;
    }

    public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabTitleList, ArrayList<Fragment> tabFragment) {
        super(fm);
        this.tabTitleList = tabTitleList;
        this.tabFragment = tabFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragment.get(position);
    }

    @Override
    public int getCount() {
        return tabFragment.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabTitleList != null) {
            return tabTitleList.get(position);
        } else {
            return null;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
