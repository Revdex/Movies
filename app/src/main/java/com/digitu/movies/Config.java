package com.digitu.movies;

import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final String BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String[] BACKDROP_SIZES = {"w300", "w780", "w1280", "original"};
    public static final String[] POSTER_SIZES = {"w92", "w154", "w185", "w342", "w500", "w780", "original"};
    public static final String[] LOGO_SIZES = {"w45", "w92", "w154", "w185", "w300", "w500", "original"};
    public static final String[] PROFILE_SIZES = {"w45", "w185", "h632", "original"};
    public static final String[] STILL_SIZES = {"w92", "w185", "w300", "original"};
    public static final Map<Integer, String> GENRES;

    static {
        GENRES = new HashMap<Integer, String>() {{
            put(28, "Action");
            put(12, "Adventure");
            put(16, "Animation");
            put(35, "Comedy");
            put(80, "Crime");
            put(99, "Documentary");
            put(18, "Drama");
            put(10751, "Family");
            put(14, "Fantasy");
            put(36, "History");
            put(27, "Horror");
            put(10402, "Music");
            put(9648, "Mystery");
            put(10749, "Romance");
            put(878, "Science Fiction");
            put(10770, "TV Movie");
            put(53, "Thriller");
            put(10752, "War");
            put(37, "Western");
        }};
    }
}
