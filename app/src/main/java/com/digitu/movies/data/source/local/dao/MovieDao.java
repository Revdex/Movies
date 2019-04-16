package com.digitu.movies.data.source.local.dao;

import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class MovieDao implements BaseDao<Movie> {

    @Query("DELETE FROM movie")
    public abstract int delete();

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    public abstract LiveData<List<Movie>> findAll();

    @Query("SELECT * FROM movie WHERE categories LIKE :category ORDER BY popularity DESC")
    public abstract LiveData<List<Movie>> findByCategory(String category);

}