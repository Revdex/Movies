package com.digitu.movies.di;

import com.digitu.movies.data.source.repository.MovieRepository;
import com.digitu.movies.modules.detail.DetailMovieViewModel;
import com.digitu.movies.modules.movie.MovieViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    ViewModel homeViewModel(MovieRepository repository) {
        return new MovieViewModel(repository);
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(DetailMovieViewModel.class)
    ViewModel detailMovieViewModel(MovieRepository repository) {
        return new DetailMovieViewModel(repository);
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}