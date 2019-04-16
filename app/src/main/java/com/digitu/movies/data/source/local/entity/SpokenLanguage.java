package com.digitu.movies.data.source.local.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguage {

    @JsonProperty("iso_639_1")
    private String iso6391;
    @JsonProperty("name")
    private String name;

    public SpokenLanguage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}