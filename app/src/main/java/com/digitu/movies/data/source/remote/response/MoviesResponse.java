package com.digitu.movies.data.source.remote.response;

import com.digitu.movies.data.source.local.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoviesResponse {

    @JsonProperty("page")
    private long page;
    @JsonProperty("results")
    private List<Movie> movies;
    @JsonProperty("dates")
    private Movie.Dates dates;
    @JsonProperty("total_results")
    private long totalResults;
    @JsonProperty("total_pages")
    private long totalPages;

    public MoviesResponse() {
        movies = new ArrayList<>();
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Movie.Dates getDates() {
        return dates;
    }

    public void setDates(Movie.Dates dates) {
        this.dates = dates;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        String data = "";
        data += "Page" + "[" + page + "]";
        data += "TotalPages" + "[" + totalPages + "]";
        data += "TotalResults" + "[" + totalResults + "]";
        data += "Dates" + "[" + dates + "]";
        //data += "\n";
        data += "Movies" + "[" + movies.size() + "]";
        data += "\n";
        return data;
    }
}

