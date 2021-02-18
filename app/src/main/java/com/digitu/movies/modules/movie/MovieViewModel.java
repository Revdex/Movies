package com.digitu.movies.modules.movie;

import android.content.Intent;
import android.view.View;

import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.repository.MovieRepository;
import com.digitu.movies.modules.detail.DetailActivity;
import com.digitu.movies.utils.Logger;
import com.digitu.movies.utils.MovieUtils;
import com.digitu.movies.utils.TransformationsUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.CallSuper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    private final CompositeDisposable disposable;
    private final MovieRepository repository;
    private LiveData<List<Movie>> movies;
    private MutableLiveData<Integer> movieId = new MutableLiveData<>();

    @Inject
    public MovieViewModel(MovieRepository repository) {
        this.repository = repository;
        this.disposable = new CompositeDisposable();
    }

    @CallSuper
    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
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
        disposable.add(repository.loadMovies(Movie.POPULAR, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((movies, error) -> {
                    Logger.e("LoadMovies", "[" + movies + " - " + error + "]");

                }));

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
        Intent intent = new Intent(recyclerView.getContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(DetailActivity.MOVIE_ID, MovieUtils.getIdMovie(getMovies(), position));
        recyclerView.getContext().startActivity(intent);
    }


    public boolean onLongClick(RecyclerView recyclerView, int position, View child) {
        Logger.e("onLongClick", position);
        return true;
    }
}
