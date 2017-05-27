package bemo.moviechat;

import android.widget.ImageView;

import bemo.moviechat.models.Movie;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public interface MovieItemClickListener {
    void onMovieClick(int position, Movie movie, ImageView sharedImageView);
}
