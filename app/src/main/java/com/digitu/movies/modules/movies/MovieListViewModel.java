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

    @Inject MovieRepository mRepository;
    private int mPage;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
        this.mPage = 0;
    }

    LiveData<List<Movie>> getPopularMovies() {
        return mRepository.getMovies();
    }

    public void onLoadMore() {
        mPage++;
        mDisposable.add(mRepository.getMovies(mPage)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> Logger.i("onSuccess()", movies),
                        error -> Logger.e("onError()", error.getMessage())));
    }

    public void deleteAll() {
        mDisposable.add(mRepository.deleteAllMovies()
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
