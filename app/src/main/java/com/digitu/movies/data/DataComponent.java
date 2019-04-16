package com.digitu.movies.data;

import com.digitu.movies.AppModule;
import com.digitu.movies.base.BaseGlideModule;
import com.digitu.movies.data.source.local.database.StorageModule;
import com.digitu.movies.data.source.remote.service.NetworkModule;
import com.digitu.movies.modules.detail.DetailActivity;
import com.digitu.movies.modules.detail.DetailMovieViewModel;
import com.digitu.movies.modules.home.HomeViewModel;
import com.digitu.movies.modules.movies.MovieListActivity;
import com.digitu.movies.modules.movies.MovieListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class})
public interface DataComponent {

    void inject(DetailActivity detailActivity);

    void inject(BaseGlideModule baseGlideModule);

    void inject(MovieListActivity movieListActivity);

    void inject(MovieListViewModel movieListViewModel);

    void inject(HomeViewModel homeViewModel);

    void inject(DetailMovieViewModel detailMovieViewModel);
}
