package com.techelevator.model;

import java.util.Objects;

public class Beer {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Double abv;
    private Long breweryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Objects.equals(id, beer.id) && Objects.equals(name, beer.name) && Objects.equals(type, beer.type) && Objects.equals(description, beer.description) && Objects.equals(abv, beer.abv) && Objects.equals(breweryId, beer.breweryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, abv, breweryId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }
}
