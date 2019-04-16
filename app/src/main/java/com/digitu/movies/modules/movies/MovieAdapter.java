package com.digitu.movies.modules.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseAdapter;
import com.digitu.movies.base.BaseViewHolder;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.databinding.ItemMovieBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class MovieAdapter extends BaseAdapter<Movie> {

    public MovieAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseViewHolder<Movie> {

        private final ItemMovieBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        @Override
        public void bind(Movie movie) {
            super.bind(movie);
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
