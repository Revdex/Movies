package com.digitu.movies.modules.movies;

import android.content.Context;
import android.content.Intent;

import com.digitu.movies.BR;
import com.digitu.movies.Config;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.modules.detail.DetailActivity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MovieItemData extends BaseObservable {

    private final Context context;
    private Movie movie;

    public MovieItemData(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }

    public String getBackdrop() {
        return Config.BASE_URL + Config.BACKDROP_SIZES[2] + movie.getBackdropPath();
    }

    public String getPoster() {
        return Config.BASE_URL + Config.POSTER_SIZES[3] + movie.getPosterPath();
    }

    @Bindable
    public String getRate() {
        return String.valueOf(this.movie.getVoteAverage());
    }

    public void setRate(double rate) {
        movie.setVoteAverage(rate);
        notifyPropertyChanged(BR.rate);
    }

    public long getId() {
        return movie.getId();
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getDate() {
        return movie.getReleaseDate();
    }

    public String getGenre() {
        return movie.getGenreIds().toString();
    }

    public String getOverview() {
        return movie.getOverview();
    }

    public void onClick(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }
}
