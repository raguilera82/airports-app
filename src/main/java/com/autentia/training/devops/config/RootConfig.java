package com.autentia.training.devops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.autentia.training.devops.repository.AirportRepository;
import com.autentia.training.devops.service.AirportService;
import com.autentia.training.devops.service.AirportServiceImpl;

@Configuration
public class RootConfig{
    @Bean
    public AirportService airportService(AirportRepository airportRepository) throws Exception{
        return new AirportServiceImpl(airportRepository);
    }
}
