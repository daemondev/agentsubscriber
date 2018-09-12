package com.daemondev.agentsubscriber;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RyRAPI {
    @GET("get")
    Call<List<Change>> loadChanges(@Query("ws") String status);

    @GET("api/breeds/image/random")
    Call<Change> loadChanges2();

    @GET("api/breeds/image/random")
    Call<List<Change>> loadChanges3();

}
