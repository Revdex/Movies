package com.digitu.movies.data.source.local.dao;

import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public abstract class MovieDao implements BaseDao<Movie> {

    @Query("DELETE FROM movie")
    public abstract int delete();

    @Query("SELECT * FROM movie")
    public abstract LiveData<List<Movie>> findAll();
    
    @Query("SELECT * FROM movie")
    public abstract Flowable<List<Movie>> getAll();

}