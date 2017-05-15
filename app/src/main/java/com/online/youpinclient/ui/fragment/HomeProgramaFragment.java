package com.online.youpinclient.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.online.youpinclient.R;
import com.online.youpinclient.bean.ProgramaModel;
import com.online.youpinclient.bean.SlideshowBean;
import com.online.youpinclient.bean.SlideshowBean.DataBean;
import com.online.youpinclient.databinding.HomeTabProgramaBinding;
import com.online.youpinclient.network.RequestClient;
import com.online.youpinclient.ui.adapter.ProgramaRecyclerViewAdapter;
import com.online.youpinclient.ui.base.BaseFragment;
import com.online.youpinclient.ui.widget.GlideImageLoader;
import com.online.youpinclient.ui.widget.banner.OnBannerListener;
import com.online.youpinclient.ui.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by permanent love on 2017/4/19.
 * 首页栏目
 */

public class HomeProgramaFragment extends BaseFragment<HomeTabProgramaBinding> {

    //RecyclerView适配器
    private ProgramaRecyclerViewAdapter adapter;

    List<String> been=new ArrayList<>();
    List<DataBean> beanlist=new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initView();
        loadData();
        showContentView();

    }

    private void initData() {
        List<ProgramaModel> programaModels= new ArrayList<>();
        for (int i=0;i<20;i++){
            ProgramaModel model=new ProgramaModel();
            model.setU_background("http://60.205.179.49:8080/youpin/document/img/slideshow/1.png");
            model.setU_avatar("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg");
            model.setU_name("阿怪");
            model.setU_signature("生活里的那些事");
            model.setU_tag("-收纳-");
            model.setU_total("21篇");
            programaModels.add(model);
        }
        adapter.addList(programaModels);
        adapter.notifyDataSetChanged();


    }

    private void initView() {

        //初始化recyclersView,横向滑动
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        t.recyclers.setLayoutManager(layoutManager);
        adapter=new ProgramaRecyclerViewAdapter(getContext());
        t.recyclers.setAdapter(adapter);
        initData();



    }

    @Override
    protected int setLayoutId() {
        return R.layout.home_tab_programa;
    }

    @Override
    protected void onRefresh() {
        showContentView();
        loadData();
    }
    /**
     * 加载数据
     */
    @Override
    protected void loadData() {
        super.loadData();
        RequestClient.getInstance().getSlideshow()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SlideshowBean>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SlideshowBean value) {
                        if (value.code.equals("0")){
                            setBeanData(value.getData());
                        }else {
                            showError();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        //加载异常
                        showError();


                    }

                    @Override
                    public void onComplete() {

                        //加载完成

                    }
                });
    }

    /**
     * 初始化轮播图
     * @param list
     */
    public void initSlideshow(List<DataBean> list){
        List<String> stringList=new ArrayList<>();
        final List<String> stringList2=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            stringList.add(list.get(i).getSlideshow_path());
            stringList2.add(list.get(i).getDeta_path());

        }

        t.banners.setImages(stringList)
                .setImageLoader(new GlideImageLoader())
                .start();

        t.banners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                        Intent intent=new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("load",stringList2.get(position));
                        startActivity(intent);
            }
        });
    }

    /**
     * 存储变量
     * @param list
     */
    public void setBeanData(List<DataBean> list){
        beanlist=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            DataBean dataBean=new DataBean();
            dataBean.setSlideshow_path("http://60.205.179.49:8080/youpin/"+list.get(i).getSlideshow_path());
            dataBean.setDeta_path("https://"+list.get(i).getDeta_path());
            beanlist.add(dataBean);
        }


        initSlideshow(beanlist);
    }
}
