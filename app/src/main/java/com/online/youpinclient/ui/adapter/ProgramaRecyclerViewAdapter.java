package com.online.youpinclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.online.youpinclient.R;
import com.online.youpinclient.bean.ProgramaModel;
import com.online.youpinclient.ui.adapter.holder.ProgramaTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by permanent love on 2017/5/2.
 * 首页栏目RecyclerViewAdapter适配器
 */

public class ProgramaRecyclerViewAdapter extends RecyclerView.Adapter<ProgramaTypeViewHolder>{

    private LayoutInflater layoutInflater;
    private List<ProgramaModel> modelList=new  ArrayList<>();
    private Context context;

    public ProgramaRecyclerViewAdapter(Context context) {
        this.context=context;
        this.layoutInflater =LayoutInflater.from(context);
    }

    public void addList(List<ProgramaModel> list){
        modelList.addAll(list);
    }

    @Override
    public ProgramaTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.programa_recycler,parent,false);
        ProgramaTypeViewHolder programaTypeViewHolder=new ProgramaTypeViewHolder(view);
        return programaTypeViewHolder;
    }

    @Override
    public void onBindViewHolder(ProgramaTypeViewHolder holder, int position) {
        holder.bindHolder(modelList.get(position),context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
