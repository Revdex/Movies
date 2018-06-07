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
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> peoples);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    int delete();

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> findAll();

}