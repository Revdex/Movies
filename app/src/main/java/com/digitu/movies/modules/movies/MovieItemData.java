package com.digitu.movies.modules.movies;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.digitu.movies.BR;
import com.digitu.movies.Config;
import com.digitu.movies.data.source.local.entity.Movie;

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
        this.movie.setVoteAverage(rate);
        notifyPropertyChanged(BR.rate);
    }

    public long getId() {
        return this.movie.getId();
    }

    public String getTitle() {
        return this.movie.getTitle();
    }

    public String getDate() {
        return this.movie.getReleaseDate();
    }

    public String getGenre() {
        return this.movie.getGenreIds().toString();
    }

    public String getOverview() {
        return this.movie.getOverview();
    }

    //TODO handle onLongClick
    public boolean onLongClick(View view) {
        return true;
    }

    //TODO handle onClick
    public void onClick(View view) {
    }
}
