package com.example.moivebuff.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {
    public static String BASE_URL="https://api.themoviedb.org";

    public static Retrofit getClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
    public static Retrofit getClientforImages(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }



    public static APIInterface apiInterface(){
        return getClient().create(APIInterface.class);
    }
}
