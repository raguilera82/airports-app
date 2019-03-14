package com.autentia.training.devops.service;

import java.util.List;

import com.autentia.training.devops.model.entity.Airport;


public interface AirportService {
    List<Airport> allAirports();

    void save(Airport airport);

    void delete(String code);

    Airport getAirport(String code);
}
