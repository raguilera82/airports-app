package com.autentia.training.devops.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.training.devops.model.entity.Airport;
import com.autentia.training.devops.model.exception.AirportCityDoesNotExistException;
import com.autentia.training.devops.model.exception.AirportDoesNotExistException;
import com.autentia.training.devops.repository.AirportRepository;

@Transactional
public class AirportServiceImpl implements AirportService{
    
    static Log logger = LogFactory.getLog(AirportServiceImpl.class);
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository){
        super();
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Airport> allAirports(){
        return this.airportRepository.findAll();
    }

    @Override
    public void save(final Airport airport){
        try {
            this.airportRepository.save(airport);
        } catch (Exception e) {
            throw new AirportCityDoesNotExistException("airports.error.city_not_found","La ciudad indicada: "+airport.getCity().getName()+" no ha sido encontrada");
        }
    }
    
    @Override
    public void delete(final String code){
        this.airportRepository.delete(code);
    }
    
    @Override
    @Transactional(readOnly=true)
    public Airport getAirport(final String code){
        Airport airport=null;
        try {
            airport = this.airportRepository.findByCode(code);
        } catch (Exception e) {
            logger.warn(e);
        } 
        
        if(airport!=null) {
            return airport;
        } else {
            throw new AirportDoesNotExistException("airports.error.airport_not_found", "Aeropuerto "+code+" no encontrado");
        }
    }

}
