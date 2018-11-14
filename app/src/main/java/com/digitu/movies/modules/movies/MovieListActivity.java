package com.digitu.movies.modules.movies;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.databinding.ActivityMovieListBinding;
import com.digitu.movies.utils.EndlessScroll;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MovieListActivity extends BaseActivity {

    private RecyclerView mRcvMovies;
    private MovieAdapter mMovieAdapter;
    private LinearLayoutManager mLayoutManager;
    private SlideInUpAnimator mSlideInUpAnimator;
    private SlideInBottomAnimationAdapter mAnimationAdapter;
    private ActivityMovieListBinding mBinding;
    private MovieListViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        initDataBinding();
        initRecyclerView();
        initObserver();
    }

    private void initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        setSupportActionBar(mBinding.toolbar);
        mRcvMovies = mBinding.moviesRcvMovies;
    }

    public void onClickFabLoad(View view) {
        mViewModel.deleteAll();
    }

    private void initObserver() {
        mViewModel.loadMovies();
        mViewModel.getMovies().observe(this,
                movies -> mMovieAdapter.change(movies));
    }

    private void initRecyclerView() {
        mMovieAdapter = new MovieAdapter();
        mLayoutManager = new LinearLayoutManager(mContext);
        mRcvMovies.addOnScrollListener(new EndlessScroll(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mViewModel.loadMovies();
            }
        });
        mRcvMovies.setLayoutManager(mLayoutManager);
        mRcvMovies.setHasFixedSize(true);

        mSlideInUpAnimator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        mSlideInUpAnimator.setAddDuration(2000);
        mRcvMovies.setItemAnimator(mSlideInUpAnimator);
        mRcvMovies.setAdapter(mMovieAdapter);


    }
}
