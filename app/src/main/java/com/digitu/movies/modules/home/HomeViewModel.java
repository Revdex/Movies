package com.digitu.movies.modules.home;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.digitu.movies.App;
import com.digitu.movies.base.ObservableViewModel;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.repository.MovieRepository;
import com.digitu.movies.modules.detail.DetailActivity;
import com.digitu.movies.utils.Logger;
import com.digitu.movies.utils.MovieUtils;
import com.digitu.movies.utils.TransformationsUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ObservableViewModel {

    @Inject
    MovieRepository repository;
    private final Context context;
    private LiveData<List<Movie>> movies;
    private MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        App.getDataComponent().inject(this);
    }

    public MutableLiveData<Integer> getMovieId() {
        if (movieId == null) {
            movieId = new MutableLiveData<>();
        }
        return movieId;
    }

    public void loadMovie(int id) {
        getMovieId().setValue(id);
    }

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = repository.getMoviesByCategory(Movie.POPULAR);
            loadMovies(1);
        }
        return movies;
    }

    public void loadMovies(int page) {
        mDisposable.add(repository.loadMovies(Movie.POPULAR, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public LiveData<Movie> getMovie() {
        return TransformationsUtils.merge(getMovies(), getMovieId(), (movies, index) -> movies.get(index));
    }


    public void onLoadMore(RecyclerView recyclerView, int page, int totalItemsCount) {
        Logger.e("onLoadMore", "[" + page + " - " + totalItemsCount + "]");
        loadMovies(page);
    }

    public void onItemClick(RecyclerView recyclerView, int position, View child) {
        Logger.e("onItemClick", position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.MOVIE_ID, MovieUtils.getIdMovie(getMovies(), position));
        context.startActivity(intent);
    }


    public boolean onLongClick(RecyclerView recyclerView, int position, View child) {
        Logger.e("onLongClick", position);
        return true;
    }
}
