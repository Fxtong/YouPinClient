package com.online.youpinclient.ui.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.online.youpinclient.R;
import com.online.youpinclient.databinding.FragmentMenuHomeBinding;
import com.online.youpinclient.ui.adapter.TabFragmentPagerAdapter;
import com.online.youpinclient.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by permanent love on 2017/4/12.
 * 首页选项卡
 */

public class HomeFragment extends BaseFragment<FragmentMenuHomeBinding> {


    //tab标题
    private ArrayList<String> mTitleList=new ArrayList<>(2);
    //tab对应的fragment
    private ArrayList<Fragment> tabFragmentList=new ArrayList<>(2);



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initData();
        initView();
        showContentView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initData() {
        mTitleList.add("栏目");
        mTitleList.add("精选");
        tabFragmentList.add(new HomeProgramaFragment());
        tabFragmentList.add(new HomeChoicenessFragment());

    }

    private void initView() {
        TabFragmentPagerAdapter tabFragmentPagerAdapter=new TabFragmentPagerAdapter(getChildFragmentManager(),mTitleList,tabFragmentList);
        t.homeTabViewpager.setAdapter(tabFragmentPagerAdapter);
        t.homeTab.setupWithViewPager(t.homeTabViewpager);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_menu_home;
    }


}
