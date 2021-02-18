package com.digitu.movies.modules.detail;

import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.data.source.repository.MovieRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;

public class DetailMovieViewModel extends ViewModel {
    private final MovieRepository repository;
    private MutableLiveData<Long> movieId = new MutableLiveData<>();

    @Inject
    public DetailMovieViewModel(MovieRepository repository) {
        this.repository = repository;
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
