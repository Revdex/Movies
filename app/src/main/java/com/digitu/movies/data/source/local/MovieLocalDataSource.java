package com.digitu.movies.data.source.local;

import com.digitu.movies.data.source.local.database.AppDatabase;
import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;

@Singleton
public class MovieLocalDataSource {

    private final AppDatabase database;

    @Inject
    public MovieLocalDataSource(@NonNull AppDatabase database) {
        this.database = database;
    }

    public LiveData<List<Movie>> getMoviesByCategory(String category) {
        return database.getMovieDao().findAll();
    }

    public LiveData<List<Movie>> findAllByCategory(final String category) {
        return database.getMovieDao().findByCategory("%" + category + "%");
    }

    public LiveData<List<Movie>> getMovies() {
        return database.getMovieDao().findAll();
    }

    public void add(List<Movie> movies) {
        database.getMovieDao().insert(movies);
    }

    public void add(Movie movie, @Movie.Category String category) {
        movie.setCategory(category);
        database.getMovieDao().insert(movie);
    }

    public Completable deleteAll() {
        return Completable.fromCallable(() -> database.getMovieDao().delete());
    }

}
