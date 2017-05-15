package com.online.youpinclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.online.youpinclient.R;
import com.online.youpinclient.ui.base.BaseFragment;

/**
 * Created by permanent love on 2017/4/23.
 * 社区推荐
 */

public class CommunityRecommendFragment extends BaseFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.community_recommend;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        showContentView();
    }

}
