package com.roix.testtaskvideo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by roix on 18.11.2016.
 */

public interface ApiModel {

    @GET("users/testobject_slon/{tag-name}/.sync.xml")
    Call<SyncList> getClipList(@Path("tag-name")String tagName);

    @GET("users/testobject_slon/{tag-name}/.sync.xml")
    Call<ResponseBody> getClipList2(@Path("tag-name")String tagName);

}
