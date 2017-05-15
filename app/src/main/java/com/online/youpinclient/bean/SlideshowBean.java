package com.online.youpinclient.bean;

import java.util.List;

/**
 * Created by permanent love on 2017/4/29.
 * 轮播图实体类
 */

public class SlideshowBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private Object id;
        private int page;
        private int rows;
        //图片路径
        private String slideshow_path;
        private Object createdate;
        //详情路径
        private String deta_path;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public String getSlideshow_path() {
            return slideshow_path;
        }

        public void setSlideshow_path(String slideshow_path) {
            this.slideshow_path = slideshow_path;
        }

        public Object getCreatedate() {
            return createdate;
        }

        public void setCreatedate(Object createdate) {
            this.createdate = createdate;
        }

        public String getDeta_path() {
            return deta_path;
        }

        public void setDeta_path(String deta_path) {
            this.deta_path = deta_path;
        }
    }
}
