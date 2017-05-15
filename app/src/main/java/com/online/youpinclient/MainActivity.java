package com.online.youpinclient;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.online.youpinclient.databinding.ActivityMainBinding;
import com.online.youpinclient.model.ToolbarViewModel;
import com.online.youpinclient.ui.fragment.CommunityFragment;
import com.online.youpinclient.ui.fragment.DiscoverFragment;
import com.online.youpinclient.ui.fragment.HomeFragment;
import com.online.youpinclient.ui.fragment.MyFragment;
import com.online.youpinclient.ui.helper.ShowToolbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    private ActivityMainBinding mainBinding;
    BadgeItem badgeItem;
    private ArrayList<Fragment> fragmentArrayList;
    private ToolbarViewModel toolbarViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initFragment();
        initView();
        initShowFragment();
        initListener();
    }

    /**
     * 第一次加载
     */
    private void initShowFragment() {
        hideAllFragment();
        Fragment fragment=fragmentArrayList.get(0);
        if(fragment.isAdded()){

            getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }else {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,fragment).show(fragment).commitAllowingStateLoss();

        }

    }


    private void initListener() {
        //bottomNavigationBar点击事件
        mainBinding.bottomNavigationBar.setTabSelectedListener(this);

    }

    private void initView() {
        //初始化标题栏
        setSupportActionBar(mainBinding.mainToolbar);
        //状态栏颜色
        ShowToolbar.showToolbar(this, mainBinding.mainToolbar, true);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbarViewModel = new ToolbarViewModel();
        if (toolbarViewModel.getMenu_title() == null) {
            toolbarViewModel.setMenu_title(getTabTitle(0));
            mainBinding.setMainBean(toolbarViewModel);
        }
        //导航栏角标
        badgeItem = new BadgeItem().setBorderWidth(1).setText("").setBackgroundColorResource(R.color.colorBadge).setHideOnSelect(false);
        //固定图标大小
        mainBinding.bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mainBinding.bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home, "首页").setActiveColorResource(R.color.colorApp))
                .addItem(new BottomNavigationItem(R.drawable.ic_found, "发现").setActiveColorResource(R.color.colorApp))
                .addItem(new BottomNavigationItem(R.drawable.ic_communit, "社区").setActiveColorResource(R.color.colorApp))
                .addItem(new BottomNavigationItem(R.drawable.ic_my, "我的").setActiveColorResource(R.color.colorApp))
                .setFirstSelectedPosition(0).initialise();

        showIcon(0);


    }


    /**
     * tab被选中
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        toolbarViewModel.setMenu_title(getTabTitle(position));
        mainBinding.setMainBean(toolbarViewModel);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment =fragmentArrayList.get(position);
        hideAllFragment();
        if(fragment.isAdded()){
            fragmentTransaction.show(fragment).commitAllowingStateLoss();
        }else {
            fragmentTransaction.add(R.id.fragment_content,fragment).show(fragment).commitAllowingStateLoss();
        }
        showIcon(position);

    }

    /**
     * 下一个tab
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {

            /*if (fragmentArrayList != null) {
                if (position < fragmentArrayList.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = fragmentArrayList.get(position);
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }*/
    }

    /**
     * tab重新被选中
     *
     * @param position
     */
    @Override
    public void onTabReselected(int position) {

    }

    /**
     * tab的Title
     */
    private String getTabTitle(int position) {
        String titel = "";
        switch (position) {
            case 0:
                titel = "首页";
                break;
            case 1:
                titel = "发现";
                break;
            case 2:
                titel = "社区";
                break;
            case 3:
                titel = "我的";
                break;
            default:
                titel = "未知领域";
        }
        return titel;

    }


    /**
     * 顶部导航栏图标显示
     *
     * @param p
     */
    private void showIcon(int p) {
        switch (p) {
            case 0:
                mainBinding.include.toolbarTitle.setVisibility(View.VISIBLE);
                mainBinding.include.menuMail.setVisibility(View.GONE);
                mainBinding.include.menuHeadset.setVisibility(View.GONE);
                mainBinding.include.menuSettings.setVisibility(View.GONE);
                mainBinding.include.menuCard.setVisibility(View.GONE);
                break;
            case 1:
                mainBinding.include.toolbarTitle.setVisibility(View.GONE);
                mainBinding.include.menuMail.setVisibility(View.GONE);
                mainBinding.include.menuHeadset.setVisibility(View.GONE);
                mainBinding.include.menuSettings.setVisibility(View.GONE);
                mainBinding.include.menuCard.setVisibility(View.VISIBLE);

                break;
            case 2:
                mainBinding.include.toolbarTitle.setVisibility(View.VISIBLE);
                mainBinding.include.menuMail.setVisibility(View.GONE);
                mainBinding.include.menuHeadset.setVisibility(View.GONE);
                mainBinding.include.menuSettings.setVisibility(View.GONE);
                mainBinding.include.menuCard.setVisibility(View.GONE);

                break;
            case 3:
                mainBinding.include.toolbarTitle.setVisibility(View.VISIBLE);
                mainBinding.include.menuMail.setVisibility(View.VISIBLE);
                mainBinding.include.menuHeadset.setVisibility(View.VISIBLE);
                mainBinding.include.menuSettings.setVisibility(View.VISIBLE);
                mainBinding.include.menuCard.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new DiscoverFragment());
        fragmentArrayList.add(new CommunityFragment());
        fragmentArrayList.add(new MyFragment());

    }
    /**
     * 隐藏所有fragment
     */
    private void hideAllFragment(){
        for(Fragment f:fragmentArrayList){
            getSupportFragmentManager().beginTransaction().hide(f).commitAllowingStateLoss();
        }
    }

}
