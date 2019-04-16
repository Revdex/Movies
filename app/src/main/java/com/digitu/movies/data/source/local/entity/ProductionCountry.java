package com.digitu.movies.data.source.local.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountry {

    @JsonProperty("iso_3166_1")
    private String iso31661;
    @JsonProperty("name")
    private String name;

    public ProductionCountry() {
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

