package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

//makes class Parcelable
@Parcel
public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Double voteAverage;
//    int id;

    //no arg constructor
    public Movie(){

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        //TO DO: fix this maybe it should be movie.getDouble() according to website
        voteAverage = jsonObject.getDouble("vote_average");
        //parse its value from JSONObject using the key id
//        id = jsonObject.getInt("id");
    }
    public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getVoteAverage(){
        return voteAverage;
    }

//    public int getMovieId(){ return id; }

}
