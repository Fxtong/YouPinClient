package com.online.youpinclient.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.online.youpinclient.R;
import com.online.youpinclient.utils.PerfectClickListener;

/**
 * Created by permanent love on 2017/4/12.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    protected T t;
    // 是否让fragment显示了
    protected boolean mIsVisible = false;
    // 加载中
    private ConstraintLayout mLlProgressBar;
    // 加载失败
    private ConstraintLayout mRefresh;
    // 内容布局
    protected FrameLayout mContainer;
    protected View inflateView;
    // 动画
    private AnimationDrawable mAnimationDrawable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflateView= inflater.inflate(R.layout.fragment_base,container,false);
        t= DataBindingUtil.inflate(getActivity().getLayoutInflater(),setLayoutId(),container, false);
        ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
        t.getRoot().setLayoutParams(layoutParams);
        mContainer= (FrameLayout) inflateView.findViewById(R.id.base_container);
        mContainer.addView(t.getRoot(),layoutParams);

        return inflateView;
    }



    /**
     * @return Layout ID
     */
    protected abstract int setLayoutId();

    /**
     * 通过id查找控件
     * @param id
     * @param <V>
     * @return
     */
    protected <V extends View> V getView(@IdRes int id) {
        return (V) inflateView.findViewById(id);

    }
    /**
     * 在这里实现Fragment数据的缓加载.
     */
   @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }

    }
    protected void onInvisible() {

    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {
    }

    protected void onVisible() {
        loadData();
    }
    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(R.id.img_progress);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        mRefresh = getView(R.id.ll_error_refresh);
        // 点击加载失败布局
        mRefresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });

        t.getRoot().setVisibility(View.GONE);
    }
    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
            mLlProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (t.getRoot().getVisibility() != View.GONE) {
            t.getRoot().setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (t.getRoot().getVisibility() != View.VISIBLE) {
            t.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (t.getRoot().getVisibility() != View.GONE) {
           t.getRoot().setVisibility(View.GONE);
        }
    }




}
