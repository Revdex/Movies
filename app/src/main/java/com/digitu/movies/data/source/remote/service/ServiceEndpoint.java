package com.digitu.movies.data.source.remote.service;

import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.data.source.remote.response.MoviesResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceEndpoint {

    /*Url*/
    String BASE_URL = "https://api.themoviedb.org/3/";
    String CONFIGURATION = "configuration";
    String POPULAR_MOVIE_URL = "movie/popular";
    String TOP_RATED_MOVIE_URL = "movie/top_rated";
    String UPCOMING_MOVIE_URL = "movie/upcoming";
    String NOW_PLAYING = "movie/now_playing";
    String DETAILS_MOVIE_URL = "movie/{movie_id}";
    String GENRES_MOVIE_URL = "genre/movie/list";
    /*Params*/
    String PAGE = "page";
    String API_KEY = "api_key";
    String LANGUAGE = "language";

    @GET(UPCOMING_MOVIE_URL)
    Call<MoviesResponse> upcoming(@Query(PAGE) long page);

    @GET(UPCOMING_MOVIE_URL)
    Call<MoviesResponse> upcoming();

    @GET(TOP_RATED_MOVIE_URL)
    Flowable<Response<MoviesResponse>> getTopRatedResponseObservable(@Query(PAGE) long page);

    @GET(TOP_RATED_MOVIE_URL)
    Flowable<Result<MoviesResponse>> getTopRatedResultObservable(@Query(PAGE) long page);

    @GET(POPULAR_MOVIE_URL)
    Flowable<MoviesResponse> getPopular(@Query(PAGE) long page);

    @GET(TOP_RATED_MOVIE_URL)
    Flowable<MoviesResponse> getTopRated(@Query(PAGE) long page);

    @GET(UPCOMING_MOVIE_URL)
    Flowable<MoviesResponse> getUpcoming(@Query(PAGE) long page);

    @GET(NOW_PLAYING)
    Flowable<MoviesResponse> getNowPlaying(@Query(PAGE) long page);

    /* Movie */
    @GET(DETAILS_MOVIE_URL)
    Flowable<DetailMovie> getDetail(@Path("movie_id") long id);


    /* Movie */
    @GET(DETAILS_MOVIE_URL)
    Single<DetailMovie> getMovie(@Path("movie_id") long id);
}
