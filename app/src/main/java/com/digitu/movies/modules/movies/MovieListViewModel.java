package com.digitu.movies.modules.movies;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.digitu.movies.App;
import com.digitu.movies.base.BaseViewModel;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.repository.MovieRepository;
import com.digitu.movies.utils.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends BaseViewModel {

    @Inject MovieRepository repository;
    private int page;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
        this.page = 0;
    }

    public LiveData<List<Movie>> getMovies() {
        return repository.getMovies();
    }

    public void onLoadMore() {
        page++;
        mDisposable.add(repository.getMovies(page)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> Logger.i("onSuccess()", movies),
                        error -> Logger.e("onError()", error.getMessage())));
    }

    public void deleteAll() {
        mDisposable.add(repository.deleteAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Logger.i(" onComplete()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(" onError()", e.getMessage());
                    }
                }));
    }
}
