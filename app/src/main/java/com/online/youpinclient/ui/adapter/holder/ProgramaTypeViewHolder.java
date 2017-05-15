package com.online.youpinclient.ui.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.online.youpinclient.R;
import com.online.youpinclient.bean.ProgramaModel;
import com.online.youpinclient.ui.widget.CircleImageView;


/**
 * Created by permanent love on 2017/5/2.
 * 首页recyclerView布局holder
 */

public class ProgramaTypeViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private ImageView background;
    private CircleImageView avatar;
    private TextView tag;
    private TextView name;
    private TextView signature;
    private TextView total;


    public ProgramaTypeViewHolder(View itemView) {
        super(itemView);
        background= (ImageView) itemView.findViewById(R.id.u_background);
        avatar= (CircleImageView ) itemView.findViewById(R.id.u_avatar);
        name= (TextView) itemView.findViewById(R.id.u_name);
        signature= (TextView) itemView.findViewById(R.id.u_signature);
        tag= (TextView) itemView.findViewById(R.id.u_tags);
        total= (TextView) itemView.findViewById(R.id.u_total);
    }



    public void bindHolder(ProgramaModel programaModel,Context context){

        Glide.with(context.getApplicationContext()).load(programaModel.getU_background().trim()).into(background);
        Glide.with(context.getApplicationContext()).load(programaModel.getU_avatar().trim()).into(avatar);
        name.setText(programaModel.getU_name());
        total.setText(programaModel.getU_total());
        tag.setText(programaModel.getU_tag());
        signature.setText(programaModel.getU_signature());

    }

}
