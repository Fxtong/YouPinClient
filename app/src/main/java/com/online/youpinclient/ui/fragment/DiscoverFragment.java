package com.online.youpinclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.online.youpinclient.R;
import com.online.youpinclient.ui.base.BaseFragment;
import com.online.youpinclient.databinding.FragmentMenuDiscoverBinding;

/**
 * Created by permanent love on 2017/4/12.
 * 发现选项卡
 */

public class DiscoverFragment extends BaseFragment<FragmentMenuDiscoverBinding> {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_menu_discover;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        showContentView();
    }
}
