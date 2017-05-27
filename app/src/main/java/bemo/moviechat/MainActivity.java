package bemo.moviechat;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import bemo.moviechat.models.Movie;
import bemo.moviechat.models.MovieResponse;
import bemo.moviechat.network.MovieInterface;
import bemo.moviechat.network.RetrofitErrorEvent;
import bemo.moviechat.network.RetrofitNetworkCalls;
import bemo.moviechat.network.RetrofitService;
import bemo.moviechat.network.RetrofitSuccessEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    private MovieInterface movieInterface;
    private ArrayList<Movie> moviesList;
    @BindView(R.id.main_progress)
    ProgressBar mainProgress;

    public static final String MOVIE_ITEM = "movie_item";
    public static final String MOVIE_TRANSITION_NAME = "movie_transition_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        movieInterface = RetrofitService.getRetrofitClient();
        initializeStuff();
//        loadRecyclerView();
        startFetchingContent();
    }

    private void initializeStuff() {
        moviesList = new ArrayList<>();
    }

    private void loadRecyclerView() {
        if(moviesList != null) {
            rvMovies.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            MovieAdapter adapter = new MovieAdapter(MainActivity.this, moviesList,this);
            rvMovies.setLayoutManager(layoutManager);
            rvMovies.setAdapter(adapter);
            rvMovies.setNestedScrollingEnabled(false);
        }else{
            rvMovies.setVisibility(View.GONE);
            mainProgress.setVisibility(View.VISIBLE);
        }
    }

    private void startFetchingContent() {
        RetrofitNetworkCalls.makeRetrofitCall(MainActivity.this, movieInterface.getMovies(Const.API_KEY),
                Const.GET_MOVIES);
    }

    @Subscribe
    public void onRetrofitSuccess(RetrofitSuccessEvent retrofitSuccessEvent) {
        try {
            Response response = retrofitSuccessEvent.getResponse();
            Log.d(Const.TAG, response.body().toString());
            String tag = retrofitSuccessEvent.getTag();
            if (!tag.equalsIgnoreCase(Const.GET_MOVIES))
                return;

            switch (tag) {
                case Const.GET_MOVIES:
                    try {
                        final MovieResponse movieResponse = (MovieResponse) response.body();
                        moviesList = movieResponse.getResults();
                        loadRecyclerView();
                    } catch (Exception e) {
                        Log.d(Const.TAG, "Exception");
                        e.printStackTrace();
                    }

                    break;

            }
        } catch (Exception e) {
            Log.d(Const.TAG, "Exception");
            e.printStackTrace();
        }

    }

    public void onRetrofitError(RetrofitErrorEvent errorEvent){
        try{
            rvMovies.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            EventBus.getDefault().register(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        try {
            EventBus.getDefault().unregister(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void onMovieClick(int position, Movie movie, ImageView sharedImageView) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ITEM, movie);
        intent.putExtra(MOVIE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }
}
