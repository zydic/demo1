package com.example.foldergallery.data;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    

    public String BASE_URL = "http://h/master/";



    @FormUrlEncoded
    @POST("h")
    Call<CatDataListModel> getFrameList(@Field("Application_Id") int value);

    @FormUrlEncoded
    @POST("h")
    Call<SubCategoryDataListModel> getFrameListSub(@Field("Application_Id") int value, @Field("Category_Id") int catid);

    @FormUrlEncoded
    @POST("h")
    Call<SubCategoryDataListModel> getFrameListSubUpdateCounter(@Field("Theme_Id") int value);

}
