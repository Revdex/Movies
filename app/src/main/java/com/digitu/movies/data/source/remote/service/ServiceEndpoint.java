package com.digitu.movies.data.source.remote.service;

import com.digitu.movies.data.source.remote.response.MoviesResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceEndpoint {

    /*Url*/
    String BASE_URL = "https://api.themoviedb.org/3/";
    String CONFIGURATION = "configuration";
    String UPCOMING_MOVIE_URL = "movie/upcoming";
    String TOP_RATED_MOVIE_URL = "movie/top_rated";
    String DETAILS_MOVIE_URL = "movie/{movie_id}";
    String GENRES_MOVIE_URL = "genre/movie/list";
    /*Params*/
    String PAGE = "page";
    String API_KEY = "api_key";
    String LANGUAGE = "language";

    /*Value*/
    @GET(UPCOMING_MOVIE_URL)
    Call<MoviesResponse> upcoming(@Query(PAGE) long page);

    @GET(UPCOMING_MOVIE_URL)
    Call<MoviesResponse> upcoming();

    @GET(UPCOMING_MOVIE_URL)
    Single<MoviesResponse> getTopRatedMovies(@Query(PAGE) long page);

    @GET(TOP_RATED_MOVIE_URL)
    Flowable<Response<MoviesResponse>> getTopRatedResponseObservable(@Query(PAGE) long page);

    @GET(TOP_RATED_MOVIE_URL)
    Flowable<Result<MoviesResponse>> getTopRatedResultObservable(@Query(PAGE) long page);
}
