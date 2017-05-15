package com.online.youpinclient.ui.adapter.holder;

import android.content.Context;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.online.youpinclient.bean.ChoicenessModel;
import com.online.youpinclient.databinding.ChoicenessRecyclerBinding;
import com.online.youpinclient.ui.base.BaseTypeViewHolder;

/**
 * Created by permanent love on 2017/5/4.
 */

public class ChoicenessViewHolder extends BaseTypeViewHolder<ChoicenessModel,ChoicenessRecyclerBinding> {

    private Context context;
    public ChoicenessViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
        context=viewGroup.getContext();
    }

    @Override
    public void onBindViewHolder(ChoicenessModel object, int position) {
        Glide.with(context.getApplicationContext()).load(object.getSelect_back()).into(binding.selectBack);
        Glide.with(context.getApplicationContext()).load(object.getSelect_avatar()).into(binding.selectAvatar);
        binding.selectName.setText(object.getSelect_name());
        binding.selectTime.setText(object.getSelect_time());
        binding.selectState.setText(object.getSelect_state());
    }

}
