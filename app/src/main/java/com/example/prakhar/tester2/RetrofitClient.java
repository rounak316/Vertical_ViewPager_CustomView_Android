package com.example.prakhar.tester2;

import android.provider.ContactsContract;
import android.support.v7.util.SortedList;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by Prakhar on 9/27/2015.
 */
public interface RetrofitClient {

    @Headers("Content-Type: text/html")
@GET("/sngetLTFeed.php")
    void getLT_SL(@Query("id") int id, Callback<ArrayList< _news >> callback);


    @Headers("Content-Type: text/html")
    @GET("/register.php")
    void register(@Query("id") String id, Callback<String> callback);


    @Headers("Content-Type: text/html")
    @GET("/getGTFeed.php")
    void getGT_SL(@Query("id") int id, Callback<ArrayList< _news >> callback);

    @Headers("Content-Type: text/html")
    @GET("/headlines_lt.php")
    void getHeadlines_lt(@Query("id") String id, Callback<ArrayList< _news >> callback);


    @Headers("Content-Type: text/html")
    @GET("/headlines_gt.php")
    void getHeadlines_gt(@Query("id") int id, Callback<ArrayList< _news >> callback);


@Headers("Content-Type: text/html")
    @Multipart
    @POST("/image.php")
    void uploadImage(@Part("file") TypedFile file, Callback<String> callback);



    @Multipart
    @POST("/headlines_news.php")
    void headlines_news(

                      @Part("head") TypedString head
            ,
                      @Part("desc") TypedString desc
            ,
                      @Part("name") TypedString name

            ,
                      @Part("banner") TypedString banner

            ,Callback<String> callback);


    @Multipart
    @POST("/image.php")
    void special_news(@Part("file") TypedFile file,

                      @Part("head") TypedString head
                      ,
                      @Part("desc") TypedString desc
            ,
                      @Part("name") TypedString name

            ,
                      @Part("banner") TypedString banner

                      ,Callback<String> callback);





}
