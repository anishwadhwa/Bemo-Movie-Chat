package bemo.moviechat.network;

import bemo.moviechat.models.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public interface MovieInterface {

    @GET("/3/movie/now_playing")
    Call<MovieResponse> getMovies(
        @Query("api_key") String api_key
    );

    @GET("/3/movie/{movie-id}")
    Call<MovieResponse> getMovieDetails(
        @Query("api_key") String api_key
    );
}
