package bemo.moviechat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import bemo.moviechat.models.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {


    @BindView(R.id.iv_movie_backdrop)
    ImageView ivMovieBackdrop;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.iv_movie_detail_image)
    ImageView ivMovieDetailImage;
    @BindView(R.id.tv_detail_user_vote_avg)
    TextView tvUserVoteAvg;
    @BindView(R.id.tv_detail_user_vote_count)
    TextView tvUserVoteCount;
    @BindView(R.id.tv_detail_overview)
    TextView tvDetailOverview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);
        supportPostponeEnterTransition();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        Movie movie = extras.getParcelable(MainActivity.MOVIE_ITEM);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(MainActivity.MOVIE_TRANSITION_NAME);
            ivMovieDetailImage.setTransitionName(imageTransitionName);
        }

//        toolbar.setTitle(movie.getTitle());
        toolbarLayout.setTitle(movie.getTitle());

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w300" + movie.getPoster_path())
                .noFade()
                .into(ivMovieDetailImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w780" + movie.getBackdrop_path())
                .into(ivMovieBackdrop);
        tvUserVoteAvg.setText(movie.getVote_average());
        tvUserVoteCount.setText(movie.getVote_count());
        tvDetailOverview.setText(movie.getOverview());
    }



    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent intent = new Intent(this,DummyChatActivity.class);
        startActivity(intent);
    }
}
