package com.techelevator.dao;

import com.techelevator.model.Beer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCBeerDAO implements BeerDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCBeerDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Beer> getBeersByBreweryId(Long id){
        List<Beer> beers = new ArrayList<Beer>();
        String sql = "SELECT id, name, type, description, abv, brewery_id FROM beer WHERE brewery_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }
        return beers;
    }

    public Beer addNewBeer(Beer beer, Long breweryId){
        String sql = "INSERT INTO beer VALUES (default, ?, ?, ?, ?, ?) RETURNING id";
        Long beerId = jdbcTemplate.queryForObject(sql, Long.class, beer.getName(), beer.getType(),
                beer.getDescription(), beer.getAbv(), breweryId);
        beer.setId(beerId);
        beer.setBreweryId(breweryId);
        return beer;
    }

    public void deleteBeer(Long id){
        String sql = "DELETE FROM beer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Beer mapRowToBeer(SqlRowSet results){
        Beer beer = new Beer();
        beer.setId(results.getLong("id"));
        beer.setName(results.getString("name"));
        beer.setType(results.getString("type"));
        beer.setDescription(results.getString("description"));
        beer.setAbv(results.getDouble("abv"));
        beer.setBreweryId(results.getLong("brewery_id"));
        return beer;
    }


}
