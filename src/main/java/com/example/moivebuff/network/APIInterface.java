package com.example.moivebuff.network;

import com.example.moivebuff.model.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/3/movie/{category}")
   Call<Movies> getMovies(
            @Path("category")String category,
            @Query("api_key")String Api_key,
            @Query("language")String language,
            @Query("page")int page
    );

}
