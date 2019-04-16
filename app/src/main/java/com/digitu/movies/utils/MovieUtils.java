package com.digitu.movies.utils;

import com.digitu.movies.Config;
import com.digitu.movies.data.source.local.entity.Genre;
import com.digitu.movies.data.source.local.entity.Movie;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;

public class MovieUtils {

    public static String getBackdrop(String backdropPath) {
        Logger.v("Backdrop", Config.BASE_URL + Config.BACKDROP_SIZES[1] + backdropPath);
        return Config.BASE_URL + Config.BACKDROP_SIZES[1] + backdropPath;
    }

    public static String getPoster(String posterPath) {
        Logger.v("Poster", Config.BASE_URL + Config.POSTER_SIZES[3] + posterPath);
        return Config.BASE_URL + Config.POSTER_SIZES[3] + posterPath;
    }

    public static String getRate(double voteAverage) {
        Logger.v("Rate", String.valueOf(voteAverage));
        return String.valueOf(voteAverage);
    }


    public static String getDate(String releaseDate) {
        Logger.v("Date", DateTimeUtils.format(releaseDate, "yyyy-MM-dd", " MMMM dd, yyyy"));
        return DateTimeUtils.format(releaseDate, "yyyy-MM-dd", " MMMM dd, yyyy");
    }

    public static long getIdMovie(LiveData<List<Movie>> movies, int position) {
        return movies != null && CollectionUtils.size(movies.getValue()) > position ? movies.getValue().get(position).getId() : null;
    }


    public static String getDuration(int runtime) {
        final long hours = TimeUnit.MINUTES.toHours(runtime);
        long minute = TimeUnit.MINUTES.toMinutes(runtime) -
                TimeUnit.HOURS.toMinutes(hours);
        Logger.v("Duration", String.format(Locale.ENGLISH, "%1$dh %2$dm", hours, minute));
        return String.format(Locale.ENGLISH, "%1$dh %2$dm", hours, minute);
    }

    public static String getGenre(List<Genre> genres) {
        String genresStr = "";
        if (CollectionUtils.isNotEmpty(genres)) {
            for (Genre genre : genres) {
                genresStr += genre.getName() + ", ";
            }
        }
        Logger.v("Genre", genresStr);
        return genresStr;
    }

}
