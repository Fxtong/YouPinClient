package com.online.youpinclient.ui.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by permanent love on 2017/4/12.
 * 显示还是隐藏toobar
 */

public class ShowToolbar {


    //toolbar会延伸到顶部
    public static void showToolbar(Activity activity,Toolbar toolbar, boolean b){
        if(toolbar==null){
            Log.e("showToobar：","Toobar为空");
        }else {
            int paddingTop=toolbar.getPaddingTop();
            int paddingBottom=toolbar.getPaddingBottom();
            int paddingLeft=toolbar.getPaddingLeft();
            int paddingRight=toolbar.getPaddingRight();
            int statusHeight=getStatuHeight(activity);
            ViewGroup.LayoutParams params=toolbar.getLayoutParams();
            int height=params.height;
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                setTranslucentStatus(activity,b);
                if (b){
                    paddingTop+=statusHeight;
                    height+=statusHeight;
                }else {
                    paddingTop-=statusHeight;
                    height-=statusHeight;
                }

            }
            params.height=height;
            toolbar.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
            toolbar.setVisibility(b? View.VISIBLE:View.GONE);

        }

    }

    //获取状态栏高度
    private static int getStatuHeight(Context context){
        int statuHeight=65;
        try {
            Class<?> chazz=Class.forName("com.android.internal.R$dimen");
            Object o=chazz.newInstance();
            int height=Integer.parseInt(chazz.getField("status_bar_height").get(o).toString());
            statuHeight=context.getResources().getDimensionPixelOffset(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statuHeight;
    }

    /**
     * 设置透明状态栏
     * @param b
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void setTranslucentStatus(Activity activity,boolean b){
        Window window=activity.getWindow();
        WindowManager.LayoutParams layoutParams=window.getAttributes();

        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (b) {
            layoutParams.flags |=bits;
        }else {
            layoutParams.flags &=bits;
        }
        window.setAttributes(layoutParams);
    }
}
