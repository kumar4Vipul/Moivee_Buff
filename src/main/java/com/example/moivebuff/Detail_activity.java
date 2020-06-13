package com.example.moivebuff;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moivebuff.model.Movies;

import com.example.moivebuff.network.APIclient;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_activity extends AppCompatActivity {


    public ImageView DA_Big_Poster_iv;
    public ImageView DA_small_poster;

    public ShimmerFrameLayout shimmerForActivity;

    public static int PAGE=1;
    public static String API_KEY="fb0bdccb18c2775c1b5462f5a62dd005";
    public static String LANGUAGE="en-US";
    public static String CATEGORY="popular";

    public TextView DA_Big_title_text;
    public TextView DA_Small_title_text;
    public TextView DA_Rating_tv;
    public TextView DA_Release_data_tv;
    public TextView DA_movie_desc_tv;
    public TextView DA_video_tv;

    private String title;
    private String vote_average;
    private String release_date;
    private String overview;
    private String poster_path;




    private int pos;
    private Context context;
    public int itemposition  ;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.row_data);

       //shimmerForActivity=findViewById(R.id.shimmer_For_Activity);

        DA_Big_Poster_iv=findViewById(R.id.Big_Movie_poster);
        DA_small_poster=findViewById(R.id.Small_movie_poster);
        DA_Big_title_text=findViewById(R.id.Big_title_text_view);
        DA_Small_title_text=findViewById(R.id.Small_title_movie_tv);
        DA_Rating_tv=findViewById(R.id.rating_tv);
        DA_Release_data_tv=findViewById(R.id.release_date);
        DA_movie_desc_tv=findViewById(R.id.DA_OverView_desc_tv);
        DA_video_tv=findViewById(R.id.DA_Video);



       Intent i=getIntent();
        itemposition = i.getIntExtra("position",0);

        overview = i.getStringExtra("overview");

        vote_average = String.valueOf(i.getDoubleExtra("rating",0.0));

        release_date = i.getStringExtra("release_date");

        title = i.getStringExtra("title");

        poster_path=i.getStringExtra("PosterPath");


        DA_Big_title_text.setText(title);
        DA_Small_title_text.setText(title);
        DA_movie_desc_tv.setText(overview);
        DA_Release_data_tv.setText(release_date);
        DA_Rating_tv.setText(vote_average);


        Glide.with(this).load("http://image.tmdb.org/t/p/w780"+ poster_path )
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.non_existing_url)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(DA_Big_Poster_iv);


        Glide.with(this).load("http://image.tmdb.org/t/p/w300"+ poster_path )
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.non_existing_url)
                            .into(DA_small_poster);






    }

}

