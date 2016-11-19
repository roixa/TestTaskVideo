package com.roix.testtaskvideo;

import android.util.Log;

import java.io.File;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by roix on 18.11.2016.
 */

public class Presenter {

    private MainView mainView;;
    private ApiModel api;
    private DownloadManager  manager;
    private File cacheDir;
    public Presenter(MainView mainView, final File cacheDir){
        this.mainView=mainView;
        this.cacheDir=cacheDir;
        api= new Retrofit.Builder().baseUrl(Constants.baseUrl).client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create()).build().create(ApiModel.class);

        manager=new DownloadManager(this,api,cacheDir);

        api.getClipList(Constants.clip).enqueue(new Callback<SyncList>() {
            @Override
            public void onResponse(Call<SyncList> call, Response<SyncList> response) {
                if(response.isSuccessful()) {
                    for(Item item:response.body().getList()){
                        Log.d("@@@",item.getPath());

                    }
                    manager.addList(response.body().getList());
                    manager.startDownload();
                }
                else  Log.d("@@@","!response.isSuccessful())");
            }

            @Override
            public void onFailure(Call<SyncList> call, Throwable t) {
                Log.d("@@@","onFailure");
            }
        });
    }


}
