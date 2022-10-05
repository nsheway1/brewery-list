package com.techelevator.dao;

import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDAO {

    public List<Brewery> getAllBreweries();
    public Brewery getBreweryById(Long id);
    public Brewery addNewBrewery(Brewery breweryToInsert);
    public void incrementVoteCount(Brewery breweryToUpdate);
    public Brewery getFeaturedBrewery();
    public List<Brewery> getListOfBreweriesByName(String[] breweryNames);
    public void incrementCarouselCount(Brewery breweryToUpdate);
}
