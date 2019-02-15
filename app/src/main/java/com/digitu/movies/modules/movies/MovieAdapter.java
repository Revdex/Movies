package com.digitu.movies.modules.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseAdapter;
import com.digitu.movies.base.BaseDiffCallback;
import com.digitu.movies.base.BaseViewHolder;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.databinding.ItemMovieBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class MovieAdapter extends BaseAdapter<Movie, MovieAdapter.ViewHolder> {

    public MovieAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Movie movie = items.get(position);
        holder.bind(movie);
    }

    @Override
    public void change(List<Movie> items) {
        change(new MovieDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {

        private final Context context;
        private final ItemMovieBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(Movie movie) {
            if (binding.getMovie() != null) {
                binding.getMovie().setMovie(movie);
            } else {
                binding.setMovie(new MovieItemData(context, movie));
            }
            binding.executePendingBindings();
        }
    }

    public class MovieDiffCallback extends BaseDiffCallback<Movie> {

        public MovieDiffCallback(List<Movie> oldList, List<Movie> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Movie oldMovie = oldList.get(oldItemPosition);
            Movie newMovie = newList.get(newItemPosition);
            return oldMovie.getTitle().equals(newMovie.getTitle());
        }
    }
}
