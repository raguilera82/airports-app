package com.autentia.training.devops.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.autentia.training.devops.model.entity.Airport;
import com.autentia.training.devops.model.entity.City;
import com.autentia.training.devops.repository.AirportRepository;

@Repository
public class AirportRepositorySpringJdbc implements AirportRepository{
    
    private final JdbcTemplate jdbcTemplate;


    public AirportRepositorySpringJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Airport airport){
        if(exists(airport)) {
            update(airport);
        } else {
            create(airport);
        }
        
    }

    private void update(final Airport airport){
        this.jdbcTemplate.update("update airport set name = ?, city = ? where code = ?",airport.getName(),airport.getCity().getId(),airport.getCode());
    }

    private void create(final Airport airport){
        this.jdbcTemplate.update("insert into airport (code,name,city) values (?,?,?)",airport.getCode(),airport.getName(),airport.getCity().getId());
    }

    private boolean exists(final Airport airport){
        return this.jdbcTemplate.queryForObject("select count(code) from airport where code = ?",Integer.class, airport.getCode())>0;
    }

    @Override
    public void delete(final String code){
        this.jdbcTemplate.update("delete from airport where code = ?",code);
    }

    @Override
    public List<Airport> findAll(){
        return this.jdbcTemplate.query(
                "select airport.name as name, airport.code as code, city.id as city_id, city.name as city from devops.airport LEFT JOIN devops.city ON airport.city = city.id order by name", 
                      new AirportRowMapper());
    }

    @Override
    public Airport findByCode(final String code){
        return this.jdbcTemplate.queryForObject(
                "select airport.name as name, airport.code as code, city.id as city_id, city.name as city from devops.airport LEFT JOIN devops.city ON airport.city = city.id where code = ?",
                    new AirportRowMapper(), code);
        
    }

    
    public class AirportRowMapper implements RowMapper<Airport>{
        @Override
        public Airport mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Airport(rs.getString("code"), rs.getString("name"), new City(rs.getInt("city_id"),rs.getString("city")));
        }
    }

    
    
}
