package com.digitu.movies;

import com.digitu.movies.data.DaggerDataComponent;
import com.digitu.movies.data.DataComponent;
import com.digitu.movies.data.source.local.database.StorageModule;
import com.digitu.movies.data.source.remote.service.NetworkModule;
import com.digitu.movies.data.source.remote.service.ServiceEndpoint;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    private static DataComponent dataComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ServiceEndpoint.BASE_URL))
                .storageModule(new StorageModule())
                .build();
    }
}
