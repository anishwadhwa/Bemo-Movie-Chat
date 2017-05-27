package bemo.moviechat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bemo.moviechat.models.Movie;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    Context context;
    ArrayList<Movie> moviesList;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList){
        this.context = context;
        this.moviesList = moviesList;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_element, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.tvMovieTitle.setText(moviesList.get(position).getTitle());
        holder.tvUserVoteAvg.setText(moviesList.get(position).getVote_average());
//        holder.ivMovieThumb.setImageURI("http://image.tmdb.org/t/p/w92" + moviesList.get(position).getPoster_path());    //http://image.tmdb.org/t/p/
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w154" + moviesList.get(position).getPoster_path())
                .into(holder.ivMovieThumb);
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

        MovieViewHolder(View itemView){
            super(itemView);
            tvMovieTitle = (TextView)itemView.findViewById(R.id.tv_movie_title);
            tvUserVoteAvg = (TextView)itemView.findViewById(R.id.tv_user_vote_avg);
            ivMovieThumb = (ImageView)itemView.findViewById(R.id.img_movie_thumb);
            rlFullLayout = (RelativeLayout)itemView.findViewById(R.id.rl_movie_full);

            rlFullLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,DummyChatActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }

}
