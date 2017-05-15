package com.online.youpinclient.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.online.youpinclient.R;
import com.online.youpinclient.bean.ChoicenessModel;
import com.online.youpinclient.bean.JxArticle;
import com.online.youpinclient.databinding.HomeTabChoicenessBinding;
import com.online.youpinclient.network.RequestClient;
import com.online.youpinclient.ui.adapter.ChoicenessRecyclerViewAdapter;
import com.online.youpinclient.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by permanent love on 2017/4/19.
 * 首页精选
 */

public class HomeChoicenessFragment extends BaseFragment<HomeTabChoicenessBinding> {


    private ChoicenessRecyclerViewAdapter adapter;
    List<JxArticle.DataBean> beanlist=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.home_tab_choiceness;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initView();
        showContentView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        t.selectRecycler.setLayoutManager(linearLayoutManager);
        adapter = new ChoicenessRecyclerViewAdapter();
        t.selectRecycler.setAdapter(adapter);


    }


    private void initData(final List<JxArticle.DataBean> beanlist) {


        List<ChoicenessModel> modelList=new ArrayList<>();

        for(int i=0;i<beanlist.size();i++) {
            ChoicenessModel model = new ChoicenessModel();
            model.setSelect_avatar(beanlist.get(i).getArticle_cover());
            model.setSelect_time(String.valueOf(beanlist.get(i).getArticle_time()));
            model.setSelect_state(beanlist.get(i).getArticle_title());
            model.setSelect_back(beanlist.get(i).getArticle_cover());
            model.setSelect_name(beanlist.get(i).getUser_name());
            modelList.add(model);
        }
        adapter.addAll(modelList);
        adapter.notifyDataSetChanged();



    }

    @Override
    protected void loadData() {
        super.loadData();
        RequestClient.getInstance().getJxArticle().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JxArticle>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JxArticle value) {

                        if(value.getData().size()!=0){
                            setBeanData(value.getData());
                        }
                        else {
                            showError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 存储变量
     * @param data
     */

    private void setBeanData(List<JxArticle.DataBean> data) {
        for(int i=0;i<data.size();i++){
            JxArticle.DataBean dataBean=new JxArticle.DataBean();
            dataBean.setArticle_cover("http://60.205.179.49:8080/youpin/document/img/scenario/"+data.get(i).getArticle_cover());
            dataBean.setArticle_path("https://"+data.get(i).getArticle_path());
            dataBean.setArticle_title(data.get(i).getArticle_title());
            dataBean.setArticle_time(data.get(i).getArticle_time());
            dataBean.setUser_name(data.get(i).getUser_name());
            dataBean.setArticle_path(data.get(i).getArticle_path());
            beanlist.add(dataBean);
        }
        initData(beanlist);

    }

    @Override
    protected void onRefresh() {
        showContentView();
        loadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
