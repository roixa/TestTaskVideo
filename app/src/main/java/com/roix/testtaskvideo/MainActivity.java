package com.roix.testtaskvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiModel api= new Retrofit.Builder().baseUrl(Constants.baseUrl).client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create()).build().create(ApiModel.class);
        /*
        api.getClipList2(Constants.clip).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) Log.d("@@@",response.raw().toString()+"");
                else  Log.d("@@@","fail");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        */
        api.getClipList(Constants.clip).enqueue(new Callback<SyncList>() {
            @Override
            public void onResponse(Call<SyncList> call, Response<SyncList> response) {
                if(response.isSuccessful()) Log.d("@@@",response.body().getList().size()+"");
                else  Log.d("@@@","!response.isSuccessful())");
            }

            @Override
            public void onFailure(Call<SyncList> call, Throwable t) {
                Log.d("@@@","onFailure");
            }
        });

        api.getClipList(Constants.video).enqueue(new Callback<SyncList>() {
            @Override
            public void onResponse(Call<SyncList> call, Response<SyncList> response) {
                if(response.isSuccessful()) {
                    for(Item item:response.body().getList()){
                        Log.d("@@@",item.getPath());
                    }
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
