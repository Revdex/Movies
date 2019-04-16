package com.digitu.movies.data.source.repository;

import com.digitu.movies.data.source.local.MovieLocalDataSource;
import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.remote.MovieRemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import io.reactivex.Completable;
import io.reactivex.Flowable;
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

    public LiveData<DetailMovie> getMovie(long id) {
        return LiveDataReactiveStreams.fromPublisher(remoteDataSource.getDetail(id));
    }

    public LiveData<List<Movie>> getMovies() {
        return localDataSource.getMovies();
    }

    public Completable deleteAllMovies() {
        return localDataSource.deleteAll();
    }

    public LiveData<List<Movie>> getMoviesByCategory(@Movie.Category String category) {
        return localDataSource.findAllByCategory(category);
    }

    public Single<List<Movie>> loadMovies(@Movie.Category String category, int page) {
        return remoteDataSource.getMoviesByCategory(category, page)
                .flatMap(movies -> Flowable.fromIterable(movies))
                .doOnNext(movie -> localDataSource.add(movie, category))
                .toList();
    }

    public Single<DetailMovie> getDetailMovie(long id) {
        return remoteDataSource.getMovie(id);
    }
}

