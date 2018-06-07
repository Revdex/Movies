package com.digitu.movies.data.source.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.digitu.movies.data.source.local.database.AppDatabase;
import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

@Singleton
public class MovieLocalDataSource {

    private final AppDatabase database;

    @Inject
    public MovieLocalDataSource(@NonNull AppDatabase database) {
        this.database = database;
    }

    public LiveData<List<Movie>> getMovies() {
        return database.getMovieDao().findAll();
    }

    public void addMovies(List<Movie> movies) {
        database.getMovieDao().insert(movies);
    }

    public Completable deleteAllMovies() {
        return Completable.fromCallable(() -> database.getMovieDao().delete());
    }

}
