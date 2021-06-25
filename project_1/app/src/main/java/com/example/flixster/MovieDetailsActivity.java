package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Rating;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    //the movie to display
    Movie movie;

    //view objects
    TextView tvAMDTitle;
    TextView tvAMDOverview;
    RatingBar rbVoteAverage;
    ImageView ivAMDPoster;

    public MovieDetailsActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //resolve the view objects
        tvAMDTitle = (TextView) findViewById(R.id.tvAMDTitle);
        tvAMDOverview = (TextView) findViewById(R.id.tvAMDOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        //same movie poster
        ivAMDPoster = (ImageView) findViewById(R.id.ivAMDPoster);

        //unwrap the movie passed in via intent, using its simple name as key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s", movie.getTitle()));

        //set the title and overview
        tvAMDTitle.setText(movie.getTitle());
        tvAMDOverview.setText(movie.getOverview());

        String imageUrl;
        /*if phone is in landscape mode
         * then imageUrl = backdrop image
         * else imageUrl = poster image */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getBackdropPath();
        } else {
            imageUrl = movie.getPosterPath();
        }

        int radius = 30;
        int margin = 10;

        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .transform(new RoundedCornersTransformation(radius,margin))
                .into(ivAMDPoster);

        //convert the vote average
        float voteAverage = (float) movie.getVoteAverage();
        rbVoteAverage.setRating(voteAverage / 2.0f);

    }
}