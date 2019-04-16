package com.digitu.movies.data.source.remote.service;

import android.app.Application;

import com.digitu.movies.utils.Logger;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {

    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final long CONNECT_TIMEOUT = 60; // 10MB
    private static final long READ_TIMEOUT = 60; // 10MB
    private static final String LOG_INTERCEPTOR = "LogInterceptor";
    private static final String REQUEST_INTERCEPTOR = "RequestInterceptor";
    private final String baseUrl;

    public NetworkModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    Cache provideHttpCache(@NonNull Application application) {
        return new Cache(application.getCacheDir(), DISK_CACHE_SIZE);
    }

    @Provides
    @Singleton
    @Named(LOG_INTERCEPTOR)
    Interceptor provideLogInterceptor() {
        final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(Logger::v);
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new HttpLoggingInterceptor();
    }

    @Singleton
    @Provides
    @Named(REQUEST_INTERCEPTOR)
    Interceptor provideRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Singleton
    @Provides
    ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        return objectMapper;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(@NonNull Cache cache, @NonNull @Named(REQUEST_INTERCEPTOR) Interceptor requestInterceptor, @NonNull @Named(LOG_INTERCEPTOR) Interceptor logInterceptor) {
        final OkHttpClient.Builder httpClientBuild = new OkHttpClient.Builder();
        httpClientBuild.addInterceptor(logInterceptor);
        httpClientBuild.addInterceptor(requestInterceptor);
        httpClientBuild.cache(cache);
        httpClientBuild.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuild.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        return httpClientBuild.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, @NonNull ObjectMapper mapper) {
        final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor());
        return retrofitBuilder.build();
    }

    @Singleton
    @Provides
    ServiceEndpoint provideServiceEndpoint(@NonNull Retrofit retrofit) {
        return retrofit.create(ServiceEndpoint.class);
    }
}
