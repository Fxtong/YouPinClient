package com.online.youpinclient.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by permanent love on 2017/4/29.
 * 初始化Retrofit
 */

public class RequestClient {


    private static HttpServer getRetrofit(){

        return new Retrofit.Builder().baseUrl(APIConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(HttpServer.class);
    }


    public static HttpServer getInstance(){
        return getRetrofit();
    }




}
