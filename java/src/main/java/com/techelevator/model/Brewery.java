package com.techelevator.model;

import java.util.Objects;

public class Brewery {

    private Long id;
    private String name;
    private String description;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private int voteCount;
    private int carouselCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brewery)) return false;
        Brewery brewery = (Brewery) o;
        return Objects.equals(getId(), brewery.getId()) && Objects.equals(getName(), brewery.getName()) && Objects.equals(getDescription(), brewery.getDescription()) && Objects.equals(getStreetAddress(), brewery.getStreetAddress()) && Objects.equals(getCity(), brewery.getCity()) && Objects.equals(getState(), brewery.getState()) && Objects.equals(getZipcode(), brewery.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getStreetAddress(), getCity(), getState(), getZipcode());
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getCarouselCount() {
        return carouselCount;
    }

    public void setCarouselCount(int carouselCount) {
        this.carouselCount = carouselCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
