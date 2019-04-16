package com.digitu.movies.modules.movies;

import android.app.Application;

import com.digitu.movies.App;
import com.digitu.movies.base.BaseViewModel;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.data.source.repository.MovieRepository;
import com.digitu.movies.utils.Logger;
import com.digitu.movies.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.digitu.movies.data.source.local.entity.Movie.NOW_PLAYING;
import static com.digitu.movies.data.source.local.entity.Movie.POPULAR;
import static com.digitu.movies.data.source.local.entity.Movie.TOP_RATED;
import static com.digitu.movies.data.source.local.entity.Movie.UPCOMING;

public class MovieListViewModel extends BaseViewModel {

    @Inject
    MovieRepository repository;
    private int page;
    private int pagePopular;
    private int pageTopRated;
    private int pageUpcoming;
    private int pageNowPlaying;


    public MovieListViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
        this.pagePopular = 0;
        this.pageTopRated = 0;
        this.pageUpcoming = 0;
        this.pageNowPlaying = 0;
        this.page = pagePopular;
    }


    public LiveData<List<Movie>> getMovies(@Movie.Category String category) {
        return repository.getMoviesByCategory(category);
    }

    public void loadMovies(@Movie.Category String category) {
        if (StringUtils.isEmpty(category)) return;
        switch (category) {
            default:
            case POPULAR:
                pagePopular++;
                page = pagePopular;
                break;
            case TOP_RATED:
                pageTopRated++;
                page = pageTopRated;
                break;
            case UPCOMING:
                pageUpcoming++;
                page = pageUpcoming;
                break;
            case NOW_PLAYING:
                pageNowPlaying++;
                page = pageNowPlaying;
                break;
        }
        Logger.d("LoadMovies()", "[" + category + "] (" + page + ")");
        mDisposable.add(repository.loadMovies(category, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
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
