package com.www446.haveagoodday.network;

import com.www446.haveagoodday.model.ChannelModel;
import com.www446.haveagoodday.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "http://api.tanshuapi.com/api/toutiao/v1/";

    @GET("channel")
    Call<ChannelModel> getChannel(
            @Query("key") String apikey
    );

    @GET("index")
    Call<NewsModel> getNews(
            @Query("key") String key,
            @Query("type") String type,
            @Query("num") int num,
            @Query("start") int start
    );
}
