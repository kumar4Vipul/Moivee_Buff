package com.example.moivebuff.Adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.moivebuff.model.Movies;

import java.util.List;

public interface RecycleViewItemClcikListner {
    void onMovieClick(List<Movies.ResultsBean> movie, int position, ImageView imageView);
}
