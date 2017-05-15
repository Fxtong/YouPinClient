package com.online.youpinclient.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;

/**
 * Created by permanent love on 2017/4/12.
 * 获取系统资源工具类
 */

public class CommonUtils {
    //获取颜色
    public static final int getColor(Context context, @ColorRes int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
