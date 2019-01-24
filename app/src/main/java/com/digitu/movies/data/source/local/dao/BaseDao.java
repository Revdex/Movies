package com.digitu.movies.data.source.local.dao;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;


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