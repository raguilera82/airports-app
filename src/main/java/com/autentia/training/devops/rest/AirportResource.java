package com.autentia.training.devops.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autentia.training.devops.model.entity.Airport;
import com.autentia.training.devops.service.AirportService;

@RestController
@RequestMapping("/airport")
public class AirportResource {
    
    private final AirportService airportService;
    
    public AirportResource(AirportService airportService){
        super();
        this.airportService = airportService;
    }
   
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Airport> airports() {
        return this.airportService.allAirports();
    }
    
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.CREATED)
    public void save(@RequestBody Airport airport) {
        this.airportService.save(airport);
    }
    
    
    @DeleteMapping(path="{code}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String code) {
        this.airportService.delete(code);
    }
    
    
    @GetMapping(path="{code}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Airport get(@PathVariable String code) {
        return this.airportService.getAirport(code);
    }
    
      
}
