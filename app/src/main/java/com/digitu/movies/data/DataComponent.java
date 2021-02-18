package com.digitu.movies.data;

import com.digitu.movies.AppModule;
import com.digitu.movies.base.BaseGlideModule;
import com.digitu.movies.data.source.local.database.StorageModule;
import com.digitu.movies.data.source.remote.service.NetworkModule;
import com.digitu.movies.modules.detail.DetailActivity;
import com.digitu.movies.modules.detail.DetailMovieViewModel;
import com.digitu.movies.modules.movie.MovieActivity;
import com.digitu.movies.modules.movie.MovieViewModel;
import com.digitu.movies.di.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class, ViewModelModule.class})
public interface DataComponent {

    void inject(DetailActivity detailActivity);

    void inject(BaseGlideModule baseGlideModule);


    void inject(MovieViewModel movieViewModel);

    void inject(DetailMovieViewModel detailMovieViewModel);

    void inject(MovieActivity movieActivity);


}
