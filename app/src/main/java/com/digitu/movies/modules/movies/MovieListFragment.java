package com.digitu.movies.modules.movies;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseFragment;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.utils.Logger;
import com.digitu.movies.views.RecyclerEndlessScroll;


public class MovieListFragment extends BaseFragment {

    private static final String CATEGORY = "category";

    private String category;
    private RecyclerView mRcvMovies;
    private MovieAdapter mMovieAdapter;
    private LinearLayoutManager mLayoutManager;
    private MovieListViewModel mViewModel;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(@Movie.Category String category) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

/*    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible && mViewModel != null) {
            mViewModel.loadMovies(category);
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
        mViewModel = ViewModelProviders.of(mActivity).get(MovieListViewModel.class);

    }

    private void initObserver() {
        mViewModel.loadMovies(category);
        mViewModel.getMovies(category).observe(mActivity, movies -> {
            mMovieAdapter.change(movies);
            Logger.i("LoadMovies()", "[" + category + "] (" + movies.size() + ")");
        });
    }

    private void initRecyclerView() {
        mMovieAdapter = new MovieAdapter();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRcvMovies.addOnScrollListener(new RecyclerEndlessScroll(mLayoutManager, (page, totalItemsCount, view) -> mViewModel.loadMovies(category)));
        mRcvMovies.setLayoutManager(mLayoutManager);
        mRcvMovies.setHasFixedSize(true);
        mRcvMovies.setAdapter(mMovieAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mRcvMovies = view.findViewById(R.id.movies_rcv_movies);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        initObserver();
    }
}
