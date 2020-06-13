package com.example.moivebuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moivebuff.Adapter.RecycleViewItemClcikListner;
import com.example.moivebuff.Adapter.RecyclerViewAdpapter;
import com.example.moivebuff.model.Movies;
import com.example.moivebuff.network.APIclient;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecycleViewItemClcikListner {


    private RecyclerViewAdpapter recyclerViewAdpapter;
    private RecyclerView recyclerView;

    public static int PAGE = 1;
    public static String API_KEY = "fb0bdccb18c2775c1b5462f5a62dd005";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    public TextView movie_title_cv;
    public TextView titletext;

    public ShimmerFrameLayout shimmerFrameLayout;

    public ImageView poster;

    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerFrameLayout = findViewById(R.id.shimmer_Frame_layout);

        poster = findViewById(R.id.Big_Movie_poster);
        movie_title_cv = findViewById(R.id.Small_title_movie_tv);
        titletext = findViewById(R.id.Big_title_text_view);

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view_all_movies);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //GridLayoutManager
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        getResponse();

    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        shimmerFrameLayout.startShimmer();
        getResponse();
    }

    public void getResponse() {
        Call<Movies> Call = APIclient.apiInterface().getMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);
        Call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(retrofit2.Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful() && response != null) {
                    Movies result = response.body();
                    List<Movies.ResultsBean> list = result.getResults();

                    recyclerViewAdpapter=new RecyclerViewAdpapter(list,getApplicationContext(),MainActivity.this::onMovieClick);
                    //recyclerViewAdpapter = new RecyclerViewAdpapter(list, getApplicationContext(),);

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);


                    recyclerView.setAdapter(recyclerViewAdpapter);

                    Log.d("THIS IS THE RESPONSE", response.body().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "An Error has occured", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Movies> call, Throwable t) {
                Log.d("Error message", "this is error :" + t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "An Error has occured" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onMovieClick(List<Movies.ResultsBean> movie, int position, ImageView imageView) {
        Intent intent=new Intent(this,Detail_activity.class);

        intent.putExtra("position",position);

        intent.putExtra("overview",movie.get(position).getOverview());

        intent.putExtra("rating",movie.get(position).getVote_average());

        intent.putExtra("release_date",movie.get(position).getRelease_date());



        intent.putExtra("title",movie.get(position).getTitle());

        intent.putExtra("PosterPath",movie.get(position).getPoster_path());

        Toast.makeText(this,"item Clicked"+position,Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}





//    View viewer= LayoutInflater.from(context).inflate(R.layout.single_cardview,null);
//
//    TextView title=findViewById(R.id.title_below_cv);
//    ImageView imageView=findViewById(R.id.Rv_cardview);
//
//            title.setText(listofMovies.get(i).getTitle());
//
//                    Glide.with(context).load("http://image.tmdb.org/t/p/w780"+ listofMovies.get(i).getBackdrop_path() )
//                    .centerCrop()
//                    .placeholder(R.drawable.placeholder)
//                    .error(R.drawable.non_existing_url)
//                    .into(imageView);