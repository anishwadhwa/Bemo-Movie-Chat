package bemo.moviechat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bemo.moviechat.models.Movie;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    Context context;
    ArrayList<Movie> moviesList;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList, MovieItemClickListener movieItemClickListener){
        this.context = context;
        this.moviesList = moviesList;
        this.movieItemClickListener = movieItemClickListener;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_element, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        final Movie movie = moviesList.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvUserVoteAvg.setText(movie.getVote_average());
        holder.tvLanguage.setText("English");
        holder.tvOverview.setText(movie.getOverview());
       /* Glide.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w154" + movie.getPoster_path())
                .into(holder.ivMovieThumb);*/

        Picasso.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w154" + movie.getPoster_path())
                .into(holder.ivMovieThumb);

        ViewCompat.setTransitionName(holder.ivMovieThumb, movie.getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieItemClickListener.onMovieClick(holder.getAdapterPosition(), movie, holder.ivMovieThumb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView tvMovieTitle;
        TextView tvUserVoteAvg;
        ImageView ivMovieThumb;
        RelativeLayout rlFullLayout;
        TextView tvLanguage;
        TextView tvOverview;

        MovieViewHolder(View itemView){
            super(itemView);
            tvMovieTitle = (TextView)itemView.findViewById(R.id.tv_movie_title);
            tvUserVoteAvg = (TextView)itemView.findViewById(R.id.tv_user_vote_avg);
            ivMovieThumb = (ImageView)itemView.findViewById(R.id.img_movie_thumb);
            rlFullLayout = (RelativeLayout)itemView.findViewById(R.id.rl_movie_full);
            tvLanguage = (TextView) itemView.findViewById(R.id.tv_language);
            tvOverview = (TextView) itemView.findViewById(R.id.tv_overview);

        }
    }

}
