package com.digitu.movies.data.source.local.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public SharedPreference provideSharedPreferences(@NonNull Context context, @NonNull ObjectMapper objectMapper) {
        return new SharedPreference(context, objectMapper);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, AppDatabase.NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
