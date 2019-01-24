package com.digitu.movies.data.source.local.database;

import com.digitu.movies.data.source.local.converter.Converters;
import com.digitu.movies.data.source.local.dao.MovieDao;
import com.digitu.movies.data.source.local.entity.Movie;
import com.digitu.movies.utils.ApplicationUtils;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import static com.digitu.movies.data.source.local.database.AppDatabase.VERSION;

@Database(entities = {Movie.class}, version = VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 2;
    static final String NAME = ApplicationUtils.NAME + ".db";

    public abstract MovieDao getMovieDao();
}