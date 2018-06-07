package com.digitu.movies.data.source.remote;

import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MovieRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public MovieRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public Single<List<Movie>> getMovies(int page) {
        return serviceEndpoint.getTopRatedMovies(page).map(response -> response.getMovies());
    }
}
