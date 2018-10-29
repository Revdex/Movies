package com.digitu.movies.data.source.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;


public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> objs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);

}