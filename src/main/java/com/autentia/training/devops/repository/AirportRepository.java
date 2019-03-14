package com.autentia.training.devops.repository;

import java.util.List;

import com.autentia.training.devops.model.entity.Airport;


public interface AirportRepository{
    
    public void save(Airport airport);

    public void delete(String code);

    public List<Airport> findAll();

    public Airport findByCode(String code);

}
