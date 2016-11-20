package com.roix.testtaskvideo;

import android.util.Log;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.security.auth.callback.Callback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by roix on 19.11.2016.
 */

public class DownloadManager implements DownloadFileTask.DownloadCallback {

    private Deque<Item> downloadQueue;
    private Deque<Item> resultQueue;
    private Presenter presenter;
    private ApiModel apiModel;
    private File cacheDir;

    public DownloadManager(Presenter presenter,ApiModel apiModel,File cacheDir){
        this.presenter=presenter;
        downloadQueue=new ArrayDeque<>();
        resultQueue=new ArrayDeque<>();
        this.apiModel=apiModel;
        this.cacheDir=cacheDir;
    }

    public void addList(List<Item> items){
        downloadQueue.addAll(items);
    }

    public void startDownload( ){
        if(downloadQueue.size()!=0)
            startLoadFile(downloadQueue.poll());
    }



    @Override
    public void onLoadResult(Item item) {
        Log.d("@@@","onLoadResult");
        resultQueue.add(item);
        Item downloading=downloadQueue.poll();
        presenter.onLoadFile(resultQueue,downloading,downloadQueue);
        startLoadFile(downloading);
    }

    private void startLoadFile(final Item item){
        if (item==null)return;
        final DownloadFileTask.DownloadCallback callback=this;
        apiModel.downloadFileAsync(item.getType(),item.getPath()).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null)
                    new DownloadFileTask(cacheDir,callback,response.body()).execute(item);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



}
