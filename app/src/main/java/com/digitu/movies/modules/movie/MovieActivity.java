package com.digitu.movies.modules.movie;

import android.os.Bundle;

import com.digitu.movies.App;
import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.databinding.ActivityMovieBinding;
import com.digitu.movies.di.ViewModelFactory;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class MovieActivity extends BaseActivity {
    @Inject
    ViewModelFactory mViewModelFactory;
    private MovieViewModel mViewModel;
    private ActivityMovieBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getDataComponent().inject(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
    }
}
