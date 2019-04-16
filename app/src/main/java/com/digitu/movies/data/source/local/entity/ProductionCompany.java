package com.digitu.movies.data.source.local.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCompany {

    @JsonProperty("id")
    private int id;
    @JsonProperty("logo_path")
    private Object logoPath;
    @JsonProperty("name")
    private String name;
    @JsonProperty("origin_country")
    private String originCountry;

    public ProductionCompany() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(Object logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}

