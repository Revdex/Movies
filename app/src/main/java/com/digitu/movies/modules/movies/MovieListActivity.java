package com.digitu.movies.modules.movies;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.base.BaseFragment;
import com.digitu.movies.data.source.local.entity.Movie;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MoviePagerAdapter mAdapter;
    private List<BaseFragment> mPages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        intiViews();
        intiPager();
    }

    protected void intiViews() {
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tabLayout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //go to an activity or end this activity with finish
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void intiPager() {
        mPages = new ArrayList<>();
        try {
            mPages.add(MovieListFragment.newInstance(Movie.POPULAR));
            mPages.add(MovieListFragment.newInstance(Movie.TOP_RATED));
            mPages.add(MovieListFragment.newInstance(Movie.UPCOMING));
            mPages.add(MovieListFragment.newInstance(Movie.NOW_PLAYING));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mViewPager.setOffscreenPageLimit(mPages.size());
        mAdapter = new MoviePagerAdapter(getSupportFragmentManager(), mPages);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
