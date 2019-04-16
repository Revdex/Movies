package com.digitu.movies.modules.detail;

import android.app.Application;

import com.digitu.movies.App;
import com.digitu.movies.base.ObservableViewModel;
import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.data.source.repository.MovieRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import io.reactivex.Single;

public class DetailMovieViewModel extends ObservableViewModel {
    @Inject
    MovieRepository repository;
    private MutableLiveData<Long> movieId = new MutableLiveData<>();

    public DetailMovieViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }

    public LiveData<DetailMovie> getMovie() {
        return Transformations.switchMap(movieId, id -> repository.getMovie(id));
    }

    public void loadMovie(long id) {
        movieId.setValue(id);
    }

    public Single<DetailMovie> getDetailMovie(long id) {
        return repository.getDetailMovie(id);
    }
}
