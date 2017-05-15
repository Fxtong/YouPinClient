package com.online.youpinclient.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by permanent love on 2017/4/12.
 */

public class ToolbarViewModel extends BaseObservable {

    private String menu_title;
    private String badge_item_code;

    @Bindable
    public String getBadge_item_code() {
        return badge_item_code;
    }
    @Bindable
    public void setBadge_item_code(String badge_item_code) {
        this.badge_item_code = badge_item_code;
    }
    @Bindable
    public String getMenu_title() {
        return menu_title;
    }
    @Bindable
    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }
}
