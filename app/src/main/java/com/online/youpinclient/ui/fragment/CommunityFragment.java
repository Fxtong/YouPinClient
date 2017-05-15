package com.online.youpinclient.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.online.youpinclient.MainActivity;
import com.online.youpinclient.R;
import com.online.youpinclient.databinding.FragmentMenuCommunityBinding;
import com.online.youpinclient.ui.activity.ArticleActivity;
import com.online.youpinclient.ui.activity.PostActivity;
import com.online.youpinclient.ui.adapter.TabFragmentPagerAdapter;
import com.online.youpinclient.ui.base.BaseFragment;

import java.util.ArrayList;


/**
 * Created by permanent love on 2017/4/12.
 * 社区选项卡
 */

public class CommunityFragment extends BaseFragment<FragmentMenuCommunityBinding> {

    //tab标题
    private ArrayList<String> mTitleList=new ArrayList<>(2);
    //tab对应的fragment
    private ArrayList<Fragment> tabFragmentList=new ArrayList<>(2);

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_menu_community;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initData();
        initView();
        showContentView();
    }

    private void initView() {
        TabFragmentPagerAdapter tabFragmentPagerAdapter=new TabFragmentPagerAdapter(getChildFragmentManager(),mTitleList,tabFragmentList);
        t.communityTabViewpager.setAdapter(tabFragmentPagerAdapter);
        t.communityTab.setupWithViewPager(t.communityTabViewpager);
        t.setFab(new FabPresenter(getActivity()));

    }

    private void initData() {
        mTitleList.add("最新");
        mTitleList.add("推荐");
        tabFragmentList.add(new CommunityNewestFragment());
        tabFragmentList.add(new CommunityRecommendFragment());
    }

    public class FabPresenter{

        private FragmentActivity activity;

        public FabPresenter(FragmentActivity activity) {
            this.activity = activity;
        }

        /**
         * fab1点击事件
         */
        public void onClickFabPost(){
            Intent intent=new Intent(getContext(), ArticleActivity.class);
            startActivity(intent);
            activity.overridePendingTransition(R.anim.appearance,R.anim.stealth);

        }
        public void onClickFabArticle(){
            Intent intent=new Intent(getContext(), PostActivity.class);
            startActivity(intent);
            activity.overridePendingTransition(R.anim.appearance,R.anim.stealth);
        }
    }
}
