package com.techelevator.dao;

import com.techelevator.model.Brewery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCBreweryDAO implements BreweryDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCBreweryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Brewery> getAllBreweries() {
        List<Brewery> breweries = new ArrayList<Brewery>();
        String sql = "SELECT id, name, description, street_address, city, state, zipcode, vote_count, carousel_count FROM brewery";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Brewery brewery = mapRowToBrewery(results);
            breweries.add(brewery);
        }

        return breweries;
    }

    public Brewery addNewBrewery(Brewery breweryToInsert) {
        String sql = "INSERT INTO brewery VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Long breweryId = jdbcTemplate.queryForObject(sql, Long.class, breweryToInsert.getName(), breweryToInsert.getDescription(),
                breweryToInsert.getStreetAddress(), breweryToInsert.getCity(), breweryToInsert.getState(), breweryToInsert.getZipcode(),
                 breweryToInsert.getVoteCount(), breweryToInsert.getCarouselCount());
        breweryToInsert.setId(breweryId);
        return breweryToInsert;

    }

    public Brewery getBreweryById(Long id){
        Brewery brewery = new Brewery();
        String sql = "SELECT id, name, description, street_address, city, state, zipcode, " +
                "vote_count, carousel_count FROM brewery WHERE id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            brewery = mapRowToBrewery(results);
        }
        return brewery;
    }

    public void incrementVoteCount(Brewery breweryToUpdate){
        String sql = "UPDATE brewery SET vote_count = ? WHERE id = ?";
        jdbcTemplate.update(sql, breweryToUpdate.getVoteCount() + 1, breweryToUpdate.getId());
    }

    public void incrementCarouselCount(Brewery breweryToUpdate){
        String sql = "UPDATE brewery SET carousel_count = ? WHERE id = ?";
        jdbcTemplate.update(sql, breweryToUpdate.getCarouselCount() + 1, breweryToUpdate.getId());
    }

    public Brewery getFeaturedBrewery(){
        Brewery brewery = new Brewery();
        String sql = "SELECT id, name, description, street_address, city, state, zipcode, vote_count, " +
                "carousel_count FROM brewery WHERE vote_count IN (SELECT MAX(vote_count) FROM brewery);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            brewery = mapRowToBrewery(results);
        }
        return brewery;
    }

    public List<Brewery> getListOfBreweriesByName(String[] breweryNames){
        List<Brewery> breweries = new ArrayList<Brewery>();
        String sql = "SELECT id, name, description, street_address, city, state, zipcode, " +
                "vote_count, carousel_count FROM brewery WHERE name = ?";
        for(String name : breweryNames){
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            while(results.next()){
                Brewery brewery = mapRowToBrewery(results);
                breweries.add(brewery);
            }
        }
        return breweries;
    }

    private Brewery mapRowToBrewery(SqlRowSet results) {
        Brewery brewery = new Brewery();
        brewery.setId(results.getLong("id"));
        brewery.setName(results.getString("name"));
        brewery.setDescription(results.getString("description"));
        brewery.setStreetAddress(results.getString("street_address"));
        brewery.setCity(results.getString("city"));
        brewery.setState(results.getString("state"));
        brewery.setZipcode(results.getString("zipcode"));
        brewery.setVoteCount(results.getInt("vote_count"));
        brewery.setCarouselCount(results.getInt("carousel_count"));

        return brewery;
    }
}
