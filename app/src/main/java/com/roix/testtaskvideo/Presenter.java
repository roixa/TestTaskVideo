package com.roix.testtaskvideo;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    private String TAG="Presenter";
    public Presenter(MainView mainView, final File cacheDir){
        this.mainView=mainView;
        this.cacheDir=cacheDir;
        api= new Retrofit.Builder().baseUrl(Constants.baseUrl).client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create()).build().create(ApiModel.class);

        manager=new DownloadManager(this,api,cacheDir);
        startLoadList(Constants.clip);
        startLoadList(Constants.video);

        startLoadList(Constants.audio);
    }



    public void onLoadFile(Deque<Item> downloaded,Item downloading,Deque<Item> queue){
        Log.d(TAG,"onLoadFile presenter "+downloaded.size()+" "+downloading.getName()+" "+queue.size());
        List<Item> items=new ArrayList<>();
        items.addAll(downloaded);
        items.add(downloading);
        items.addAll(queue);
        mainView.showList(items,downloaded.size());
    }

    private void startLoadList(final String type){
        api.getClipList(type).enqueue(new Callback<SyncList>() {
            @Override
            public void onResponse(Call<SyncList> call, Response<SyncList> response) {
                if(response.isSuccessful()) {
                    List<Item> items=response.body().getList();
                    for(Item item:items){
                        item.setType(type);
                    }
                    manager.addList(items);
                    manager.startDownload();
                }
                else  Log.d(TAG,"!response.isSuccessful())");
            }

            @Override
            public void onFailure(Call<SyncList> call, Throwable t) {
                Log.d(TAG,"onFailure");
            }
        });

    }
}
