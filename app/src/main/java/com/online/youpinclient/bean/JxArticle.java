package com.online.youpinclient.bean;

import java.util.List;

/**
 * Created by permanent love on 2017/5/6.
 */

public class JxArticle extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int id;
        private int page;
        private int rows;
        private int article_id;
        private long article_time;
        private String article_path;
        private String user_name;
        private String article_cover;
        private String article_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public long getArticle_time() {
            return article_time;
        }

        public void setArticle_time(long article_time) {
            this.article_time = article_time;
        }

        public String getArticle_path() {
            return article_path;
        }

        public void setArticle_path(String article_path) {
            this.article_path = article_path;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getArticle_cover() {
            return article_cover;
        }

        public void setArticle_cover(String article_cover) {
            this.article_cover = article_cover;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }
    }
}
