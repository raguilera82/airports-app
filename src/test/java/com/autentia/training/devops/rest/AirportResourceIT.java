package com.autentia.training.devops.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.autentia.training.devops.model.entity.Airport;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AirportResourceIT {
    
    @Autowired
    private TestRestTemplate restTemplate;
     
    
   @Test
	public void airports() {
	   Airport[] airports = restTemplate.getForObject("/airport", Airport[].class);
     
       assertThat(airports.length,is(2)); 
       assertThat(airports[0].getCode(), is("MAD"));
       assertThat(airports[0].getName(), is("Adolfo Suarez"));
       assertThat(airports[0].getCity().getName(), is("Madrid"));
       
       assertThat(airports[1].getCode(), is("BIO"));
       assertThat(airports[1].getName(), is("Sondika"));
       assertThat(airports[1].getCity().getName(), is("Bilbao"));
       
	}
   
   
   @Test
   public void airport() {
      Airport airports = restTemplate.getForObject("/airport/MAD", Airport.class);
      assertThat(airports.getCode(), is("MAD"));
      assertThat(airports.getName(), is("Adolfo Suarez"));
      assertThat(airports.getCity().getName(), is("Madrid"));
   }
   

}
