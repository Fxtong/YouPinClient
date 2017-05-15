package com.online.youpinclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.online.youpinclient.R;
import com.online.youpinclient.ui.base.BaseFragment;

/**
 * Created by permanent love on 2017/4/23.
 * 社区最新
 */

public class CommunityNewestFragment extends BaseFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.community_newest;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        showContentView();
    }
}
