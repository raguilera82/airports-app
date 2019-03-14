package com.autentia.training.devops.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.autentia.training.devops.model.entity.Airport;
import com.autentia.training.devops.model.entity.City;
import com.autentia.training.devops.model.exception.AirportCityDoesNotExistException;
import com.autentia.training.devops.model.exception.AirportDoesNotExistException;
import com.autentia.training.devops.repository.AirportRepository;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceTest {

	@Mock
	AirportRepository airportRepository;

	@InjectMocks
	AirportServiceImpl airportService;

	@Test
	public void shoudlGetAllAirports() {
		Mockito.when(airportRepository.findAll()).thenReturn(new ArrayList<Airport>());
		List<Airport> airports = airportService.allAirports();
		Assert.assertEquals(0, airports.size());
	}
	
	@Test
	public void shouldSaveAirport() {
		Mockito.doNothing().when(airportRepository).save(Mockito.any(Airport.class));
		airportService.save(new Airport());
	}
	
	@Test(expected=AirportCityDoesNotExistException.class)
	public void shouldNotSaveAirport() {
		City city = new City(5, "Alcala de Henares");
		Airport airport = new Airport("ALC", "Alcala Magna", city);
		Mockito.doThrow(AirportCityDoesNotExistException.class).when(airportRepository).save(airport);
		airportService.save(airport);
	}
	
	@Test
	public void shoulDeleteAirport() {
		String code = "ALC";
		Mockito.doNothing().when(airportRepository).delete(code);
		airportService.delete(code);
	}
	
	@Test
	public void shouldGetOneAirport() {
		City city = new City(5, "Alcala de Henares");
		Airport airport = new Airport("ALC", "Alcala Magna", city);
		Mockito.when(airportRepository.findByCode(Mockito.anyString())).thenReturn(airport);
		airportService.getAirport("ALC");	
	}
	
	@Test(expected=AirportDoesNotExistException.class)
	public void shoulGetOneAiportWithException() {
		Mockito.doThrow(AirportDoesNotExistException.class).when(airportRepository).findByCode(Mockito.anyString());
		airportService.getAirport("ALC");
	}

}
