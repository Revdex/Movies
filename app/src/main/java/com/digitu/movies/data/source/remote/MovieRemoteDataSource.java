package com.digitu.movies.data.source.remote;

import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.remote.response.MoviesResponse;
import com.digitu.movies.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static com.digitu.movies.data.source.local.entity.Movie.NOW_PLAYING;
import static com.digitu.movies.data.source.local.entity.Movie.POPULAR;
import static com.digitu.movies.data.source.local.entity.Movie.TOP_RATED;
import static com.digitu.movies.data.source.local.entity.Movie.UPCOMING;

@Singleton
public class MovieRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public MovieRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public Flowable<List<Movie>> getMovies(int page) {
        return serviceEndpoint.getTopRated(page).map(MoviesResponse::getMovies);
    }

    public Flowable<List<Movie>> getMoviesByCategory(String category, int page) {
        switch (category) {
            default:
            case POPULAR:
                return serviceEndpoint.getPopular(page).map(MoviesResponse::getMovies);
            case TOP_RATED:
                return serviceEndpoint.getTopRated(page).map(MoviesResponse::getMovies);
            case UPCOMING:
                return serviceEndpoint.getUpcoming(page).map(MoviesResponse::getMovies);
            case NOW_PLAYING:
                return serviceEndpoint.getNowPlaying(page).map(MoviesResponse::getMovies);
        }
    }


    public Flowable<List<Movie>> getPopular(int page) {
        return serviceEndpoint.getPopular(page).map(MoviesResponse::getMovies);
    }

    public Flowable<List<Movie>> getTopRated(int page) {
        return serviceEndpoint.getTopRated(page).map(MoviesResponse::getMovies);
    }

    public Flowable<List<Movie>> getUpcoming(int page) {
        return serviceEndpoint.getUpcoming(page).map(MoviesResponse::getMovies);
    }

    public Flowable<List<Movie>> getNowPlaying(int page) {
        return serviceEndpoint.getNowPlaying(page).map(MoviesResponse::getMovies);
    }


    public Flowable<DetailMovie> getDetail(long id) {
        return serviceEndpoint.getDetail(id);
    }

    public Single<DetailMovie> getMovie(long id) {
        return serviceEndpoint.getMovie(id);
    }

}
