package com.roix.testtaskvideo;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by roix on 18.11.2016.
 */

public interface ApiModel {

    @GET("users/testobject_slon/{tag-name}/.sync.xml")
    Call<SyncList> getClipList(@Path("tag-name")String tagName);

    @GET("users/testobject_slon/{tag-name}/.sync.xml")
    Call<ResponseBody> getClipList2(@Path("tag-name")String tagName);

    @Streaming
    @GET("users/testobject_slon/{tag-name}/{path}")
    Call<ResponseBody> downloadFileAsync(@Path("tag-name")String tagName,@Path("path")String path);

}
