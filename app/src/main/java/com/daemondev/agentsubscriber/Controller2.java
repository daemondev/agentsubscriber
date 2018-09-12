package com.daemondev.agentsubscriber;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller2 implements Callback<Change> {

    //static final String BASE_URL = "http://ryr.progr.am";
    static final String BASE_URL = "https://dog.ceo";

    public void start(){
        Log.i("\n\n>>> STARTING\n\n",String.format("\n\n>>> HERE WE GO!!!\n\n"));
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        RyRAPI ryRAPI = retrofit.create(RyRAPI.class);
        //Call<List<Change>> call = ryRAPI.loadChanges("status:open");
        Call<Change> call = ryRAPI.loadChanges2();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Change> call, Response<Change> response) {
        if(response.isSuccessful()){

            Log.i("\n\n>>> ONRESPONSE\n\n",String.format("\n\n>>>DATA: %s!!!\n\n", response.toString()));
            Change changeList = response.body();
            //changeList.forEach(change -> System.out.print(change.status));


                System.out.println(changeList.message);
                System.out.println(changeList.status);

        }else{
            Log.i("\n\n>>> ONERROR\n\n",String.format("\n\n>>>DATA: %s!!!\n\n", response.errorBody()));
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Change> call, Throwable t) {
        Log.i("\n\n>>> ONFAILURE\n\n",String.format("\n\n>>>STACKTRACE: %s!!!\n\n", t.getMessage()));
        t.printStackTrace();
    }
}
