package com.online.youpinclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.online.youpinclient.R;
import com.online.youpinclient.databinding.FragmentMenuMyBinding;
import com.online.youpinclient.ui.base.BaseFragment;

/**
 * Created by permanent love on 2017/4/12.
 * 我的选项卡
 */

public class MyFragment extends BaseFragment<FragmentMenuMyBinding> {

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_menu_my;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

    }



}
