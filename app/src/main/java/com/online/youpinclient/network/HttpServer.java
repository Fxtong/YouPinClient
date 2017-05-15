package com.online.youpinclient.network;

import com.online.youpinclient.bean.JxArticle;
import com.online.youpinclient.bean.LoginBean;
import com.online.youpinclient.bean.RegisterBean;
import com.online.youpinclient.bean.SlideshowBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by permanent love on 2017/4/24.
 * 网络请求接口
 */

public interface HttpServer {

    /**
     * 获取轮播图
     * @return
     */
    @GET(APIConstant.SLIDESHOW_URL)
    Observable<SlideshowBean> getSlideshow();

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(APIConstant.REGISTER_URL)
    Observable<RegisterBean> postRegister( @Field("uphone")  String uphone,@Field("upassword") String upassword);


    /**
     * 登录
     * @param pe
     * @param upassword
     * @return
     */
    @FormUrlEncoded
    @POST(APIConstant.LOGIN_URL)
    Observable<LoginBean> postLogin(@Field("pe") String pe,@Field("upassword") String upassword);


    @GET(APIConstant.JXARTICLE_URL)
    Observable<JxArticle> getJxArticle();
}
