package com.techelevator.dao;

import com.techelevator.model.Brewery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCBreweryDAOTest  extends DAOIntegrationTest{

    private JDBCBreweryDAO jdbcBreweryDAO;
    private JdbcTemplate jdbcTemplate;
    private Brewery testBrewery;
    private Brewery testBreweryTwo;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcBreweryDAO = new JDBCBreweryDAO(jdbcTemplate);

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

        this.testBreweryTwo = new Brewery();
        this.testBreweryTwo.setName("Test Brewery 2");
        this.testBreweryTwo.setDescription("Cool Stuff 2");
        this.testBreweryTwo.setStreetAddress("123 Street 2");
        this.testBreweryTwo.setCity("columbus 2");
        this.testBreweryTwo.setState("Ohio 2");
        this.testBreweryTwo.setZipcode("99999");
        this.testBreweryTwo.setVoteCount(20);
        this.testBreweryTwo.setCarouselCount(2);
        this.testBreweryTwo = insertTestBrewery(testBreweryTwo);
    }


    @Test
    public void get_all_breweries_return_list() {
        List<Brewery> expectedResult = new ArrayList<Brewery>();
        expectedResult.add(testBrewery);
        expectedResult.add(testBreweryTwo);

        List<Brewery> actualResult = jdbcBreweryDAO.getAllBreweries();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void get_brewery_by_id_return_correct_brewery(){
        Brewery expectedResult = testBrewery;
        Brewery actualResult = jdbcBreweryDAO.getBreweryById(testBrewery.getId());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void add_new_brewery_successful_database_add() {
        //getAllBreweries/ breweries from db.size
        int initialSize = jdbcBreweryDAO.getAllBreweries().size();
        //insert a brewery
        Brewery testBreweryThree = new Brewery();
        testBreweryThree.setName("Bob's");
        testBreweryThree.setDescription("A place");
        testBreweryThree.setStreetAddress("234 street");
        testBreweryThree.setCity("Columbys");
        testBreweryThree.setState("Ohio");
        testBreweryThree.setZipcode("77777");
        testBreweryThree.setVoteCount(22);
        testBreweryThree.setCarouselCount(4);
        jdbcBreweryDAO.addNewBrewery(testBrewery);

        //getAllBreweries / compare size
        int actualSize = jdbcBreweryDAO.getAllBreweries().size();
        int expectedSize = initialSize + 1;
        //expect size to be +1
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void increment_vote_count_vote_added_correctly() {
        jdbcBreweryDAO.incrementVoteCount(testBrewery);
        int expectedResult = testBrewery.getVoteCount() + 1;
        String sql = "SELECT vote_count FROM brewery WHERE id = ?";
        int actualResult = jdbcTemplate.queryForObject(sql, int.class, testBrewery.getId());

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void get_list_of_breweries_by_name_return_correct_breweries(){
        List<Brewery> expectedResults = new ArrayList<Brewery>();
        expectedResults.add(testBrewery);
        expectedResults.add(testBreweryTwo);
        String[] names = {testBrewery.getName(), testBreweryTwo.getName()};
        List<Brewery> actualResults = jdbcBreweryDAO.getListOfBreweriesByName(names);

        Assert.assertEquals(expectedResults, actualResults);

    }


    private Brewery insertTestBrewery(Brewery breweryToInsert) {
        String sql = "INSERT INTO brewery VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Long breweryId = jdbcTemplate.queryForObject(sql, Long.class, breweryToInsert.getName(), breweryToInsert.getDescription(),
                breweryToInsert.getStreetAddress(), breweryToInsert.getCity(), breweryToInsert.getState(), breweryToInsert.getZipcode(),
                breweryToInsert.getVoteCount(), breweryToInsert.getCarouselCount());
        breweryToInsert.setId(breweryId);
        return breweryToInsert;
    }
}
