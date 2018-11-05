package com.digitu.movies.data.source.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.digitu.movies.data.source.local.MovieLocalDataSource;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.remote.MovieRemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class MovieRepository {

    private final MovieRemoteDataSource remoteDataSource;
    private final MovieLocalDataSource localDataSource;

    @Inject
    public MovieRepository(@NonNull MovieLocalDataSource localDataSource, @NonNull MovieRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    /*public Flowable<List<Movie>> getMovies() {
        Flowable<List<Movie>> diskWithCache = localDataSource.getMovies();
        Flowable<List<Movie>> networkWithSave = remoteDataSource.getMovies(1)
                .doOnNext(movies -> localDataSource.addMovies(movies));
        return Flowable.concat(diskWithCache, networkWithSave)
                .takeUntil(movies -> movies != null && !movies.isEmpty())
                .doOnNext(genres -> Logger.i("Movies", genres))
                .doOnError(error -> Logger.e("Movies", error));
    }*/

    public Single<List<Movie>> getMovies(int page) {
        return remoteDataSource.getMovies(page).doOnSuccess(localDataSource::addMovies);
    }

    public LiveData<List<Movie>> getMovies() {
        return localDataSource.getMovies();
    }

    public Completable deleteAllMovies() {
        return localDataSource.deleteAllMovies();
    }

}

