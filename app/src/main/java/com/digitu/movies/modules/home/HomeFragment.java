package com.digitu.movies.modules.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseFragment;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.modules.movies.MovieListFragment;
import com.digitu.movies.modules.movies.MovieListViewModel;
import com.digitu.movies.utils.EndlessScroll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment {

    private static final String CATEGORY = "category";

    private String category;
    private RecyclerView mRcvMovies;
    private HomeAdapter mMovieAdapter;
    private LinearLayoutManager mLayoutManager;
    private MovieListViewModel mViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(@Movie.Category String category) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

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
        mViewModel.getMovies(category).observe(this, movies -> mMovieAdapter.change(movies));
    }

    private void initRecyclerView() {
        mMovieAdapter = new HomeAdapter();
        mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvMovies.addOnScrollListener(new EndlessScroll(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mViewModel.loadMovies(category);
            }
        });
        mRcvMovies.setLayoutManager(mLayoutManager);
        mRcvMovies.setHasFixedSize(true);
        mRcvMovies.setAdapter(mMovieAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRcvMovies = view.findViewById(R.id.home_rcv_movies);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        initObserver();
    }
}
