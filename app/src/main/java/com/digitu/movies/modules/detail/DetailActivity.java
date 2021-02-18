package com.digitu.movies.modules.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.digitu.movies.App;
import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.data.source.local.entity.DetailMovie;
import com.digitu.movies.databinding.ActivityDetailBinding;
import com.digitu.movies.di.ViewModelFactory;
import com.digitu.movies.utils.Logger;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends BaseActivity {


    public static final String MOVIE_ID = "MOVIE_ID";
    private long movieId;
    @Inject ViewModelFactory mViewModelFactory;
    private DetailMovieViewModel mViewModel;
    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getDataComponent().inject(this);
        movieId = getIntent().getLongExtra(MOVIE_ID, 0);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailMovieViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
      //  mBinding.setMovie(mViewModel.getMovie());
        mBinding.setLifecycleOwner(this);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setTitle(null);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*        mViewModel.loadMovie(movieId);
        mViewModel.getMovie().observe(this, movie -> {
            Logger.i("movie", movie);
        });*/

        final DisposableSingleObserver<DetailMovie> disposable = mViewModel.getDetailMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DetailMovie>() {
                    @Override
                    public void onSuccess(DetailMovie movie) {
                        Logger.d("onSuccess", movie);
                        mBinding.setMovie(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError", e.getMessage());


                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
