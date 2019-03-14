package com.autentia.training.devops.repository.jdbc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.training.devops.model.entity.Airport;
import com.autentia.training.devops.model.entity.City;
import com.autentia.training.devops.repository.AirportRepository;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AirportRepositorySpringJdbcIT{

    @Autowired
    AirportRepository sut;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldReturnAllAirports(){
        List<Airport> airports = sut.findAll();
        assertThat(airports, hasSize(2));
        assertThat(airports.get(0), is(new Airport("MAD", "Adolfo Suarez", new City(2, "Madrid"))));
        assertThat(airports.get(1), is(new Airport("BIO", "Sondika", new City(3, "Bilbao"))));
    }

    @Test
    public void shouldReturnOneAirportByName(){
        Airport airport = sut.findByCode("MAD");
        assertThat(airport, is(new Airport("MAD", "Adolfo Suarez", new City(2, "Madrid"))));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowDataAccessException(){
        sut.save(new Airport("MEPASO", "Otro", new City(2, "Madrid")));
    }

    @Test
    public void shouldSaveOneNewAirport(){
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate, "airport");
        Airport newAirport = new Airport("JFK", "JFK", new City(1,"Nueva York"));
        sut.save(newAirport);
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate, "airport");
        assertThat(after, is(before + 1));
        Map<String, Object> result = jdbcTemplate.queryForMap(
                "select airport.code as code, airport.name as name, city.name as city from airport inner join city where airport.city = city.id and code = ?",
                "JFK");

        assertThat(result.get("CODE"), is("JFK"));
        assertThat(result.get("NAME"), is("JFK"));
        assertThat(result.get("CITY"), is("Nueva York"));
      
    }

    @Test
    @Sql(statements="INSERT INTO airport VALUES ('LGA','La Guardia','1')")
    public void shouldDeleteOneOldAirport() {
        int before = JdbcTestUtils.countRowsInTable(jdbcTemplate, "airport");
        sut.delete("LGA");
        int after = JdbcTestUtils.countRowsInTable(jdbcTemplate, "airport");
        assertThat(after, is(before-1));
        
        assertThat(jdbcTemplate.queryForObject("select count(airport.code) from airport where code = ?", Integer.class, "LGA"), is(0));
    }
    
}
