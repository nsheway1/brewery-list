package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCBeerDAOTest extends DAOIntegrationTest{

    private JDBCBeerDAO jdbcBeerDAO;
    private JdbcTemplate jdbcTemplate;
    private Brewery testBrewery;
    private Beer testBeer;
    private Beer testBeerTwo;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcBeerDAO = new JDBCBeerDAO(jdbcTemplate);

        jdbcTemplate.update("TRUNCATE brewery CASCADE");
        this.testBrewery = new Brewery();
        this.testBrewery.setName("Test Brewery 1");
        this.testBrewery.setDescription("Cool Stuff");
        this.testBrewery.setStreetAddress("123 Street");
        this.testBrewery.setCity("columbus");
        this.testBrewery.setState("Ohio");
        this.testBrewery.setZipcode("43445");
        this.testBrewery.setVoteCount(34);
        this.testBrewery.setCarouselCount(3);
        this.testBrewery = insertTestBrewery(testBrewery);

        jdbcTemplate.update("TRUNCATE beer CASCADE");
        this.testBeer = new Beer();
        this.testBeer.setName("Tasty Brew");
        this.testBeer.setDescription("Wow, what a tasty beer!");
        this.testBeer.setAbv(5.5);
        this.testBeer.setType("Good");
        this.testBeer.setBreweryId(testBrewery.getId());
        this.testBeer = insertTestBeer(testBeer);

        this.testBeerTwo = new Beer();
        this.testBeerTwo.setName("Delicious Brew");
        this.testBeerTwo.setDescription("Wow, what a delicious beer!");
        this.testBeerTwo.setAbv(6.5);
        this.testBeerTwo.setType("Delish");
        this.testBeerTwo.setBreweryId(testBrewery.getId());
        this.testBeerTwo = insertTestBeer(testBeerTwo);
    }

    @Test
    public void get_beers_by_brewery_id_return_list_of_beers(){
        List<Beer> expectedResult = new ArrayList<Beer>();
        expectedResult.add(testBeer);
        expectedResult.add(testBeerTwo);

        List<Beer> actualResult = jdbcBeerDAO.getBeersByBreweryId(testBrewery.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void add_new_beer_successful_database_add(){
        Beer expectedResult = testBeer;
        jdbcTemplate.update("TRUNCATE beer CASCADE");
        jdbcBeerDAO.addNewBeer(expectedResult, expectedResult.getBreweryId());
        Beer actualResult = new Beer();
        String sql = "SELECT * FROM beer WHERE name = ?";
        SqlRowSet sqlResults = jdbcTemplate.queryForRowSet(sql, expectedResult.getName());
        while(sqlResults.next()){
            actualResult = mapRowToBeer(sqlResults);
        }
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void delete_beer_successful_database_delete(){
        List<Beer> initialList = jdbcBeerDAO.getBeersByBreweryId(testBrewery.getId());
        jdbcBeerDAO.deleteBeer(testBeer.getId());
        List<Beer> resultingList = jdbcBeerDAO.getBeersByBreweryId(testBrewery.getId());

        int expectedResult = initialList.size() - 1;
        int actualResult = resultingList.size();

        Assert.assertEquals(expectedResult, actualResult);
    }

    private Brewery insertTestBrewery(Brewery breweryToInsert) {
        String sql = "INSERT INTO brewery VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Long breweryId = jdbcTemplate.queryForObject(sql, Long.class, breweryToInsert.getName(), breweryToInsert.getDescription(),
                breweryToInsert.getStreetAddress(), breweryToInsert.getCity(), breweryToInsert.getState(), breweryToInsert.getZipcode(),
                breweryToInsert.getVoteCount(), breweryToInsert.getCarouselCount());
        breweryToInsert.setId(breweryId);
        return breweryToInsert;
    }

    private Beer insertTestBeer(Beer beerToInsert){
        String sql = "INSERT INTO beer VALUES(DEFAULT, ?, ?, ?, ?, ?) RETURNING id";
        Long beerId = jdbcTemplate.queryForObject(sql, Long.class, beerToInsert.getName(), beerToInsert.getType(),
                beerToInsert.getDescription(), beerToInsert.getAbv(), beerToInsert.getBreweryId());
        beerToInsert.setId(beerId);
        return beerToInsert;
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
