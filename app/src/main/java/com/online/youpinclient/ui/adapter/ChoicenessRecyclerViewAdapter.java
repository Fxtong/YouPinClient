package com.online.youpinclient.ui.adapter;

import android.view.ViewGroup;

import com.online.youpinclient.R;
import com.online.youpinclient.bean.ChoicenessModel;
import com.online.youpinclient.ui.adapter.holder.ChoicenessViewHolder;
import com.online.youpinclient.ui.base.BaseRecyclerViewAdapter;
import com.online.youpinclient.ui.base.BaseTypeViewHolder;

/**
 * Created by permanent love on 2017/5/4.
 */

public class ChoicenessRecyclerViewAdapter extends BaseRecyclerViewAdapter<ChoicenessModel> {

    @Override
    public BaseTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ChoicenessViewHolder(parent, R.layout.choiceness_recycler);
    }


}
