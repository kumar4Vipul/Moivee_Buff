package com.example.moivebuff.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moivebuff.R;
import com.example.moivebuff.model.Movies;

import java.util.List;

import retrofit2.Callback;

public class RecyclerViewAdpapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Movies.ResultsBean> mData;
    private Context context;
    private RecycleViewItemClcikListner MovieitemClickListner;



    public RecyclerViewAdpapter(List<Movies.ResultsBean> mData, Context context, RecycleViewItemClcikListner listner) {
        this.mData = mData;
        this.context = context;
        this.MovieitemClickListner=listner;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_grid,parent,false);

        return new RowViewHolder(view,MovieitemClickListner);
    }


    @Override
    public void onBindViewHolder(  RecyclerView.ViewHolder holder,int position) {

        if(holder instanceof RowViewHolder) {

            RowViewHolder rowViewHolder = (RowViewHolder) holder;

            rowViewHolder.titletext.setText(mData.get(position).getTitle());

            Glide.with(context).load("http://image.tmdb.org/t/p/w300"+ mData.get(position).getBackdrop_path())
                                .centerCrop()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.non_existing_url)
                                .into(rowViewHolder.imgView);

        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public  class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgView;
        public TextView titletext;
        private RecycleViewItemClcikListner mListner;

        public RowViewHolder(@NonNull View itemView, RecycleViewItemClcikListner Listner) {
            super(itemView);
            mListner=Listner;
            itemView.setOnClickListener(this);
            imgView = itemView.findViewById(R.id.Card_view_movie);
            titletext = itemView.findViewById(R.id.Card_view_title_text);

        }

        @Override
        public void onClick(View view) {
            MovieitemClickListner.onMovieClick(mData,getAdapterPosition(),imgView);

        }
    }


}
