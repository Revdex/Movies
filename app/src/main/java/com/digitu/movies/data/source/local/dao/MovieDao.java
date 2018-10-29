package com.digitu.movies.data.source.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;

@Dao
public abstract class MovieDao implements BaseDao<Movie> {

    @Query("DELETE FROM movie")
    public abstract int delete();

    @Query("SELECT * FROM movie")
    public abstract LiveData<List<Movie>> findAll();

}