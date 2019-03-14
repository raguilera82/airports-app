package com.autentia.training.devops.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HelloResourceIT {
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void shouldGetVersion() {
		String version = restTemplate.getForObject("/version", String.class);
		assertThat(version, is(not(nullValue())));
	}

	
	@Test
	public void shouldGetRaguilera() {
		String raguilera = restTemplate.getForObject("/raguilera", String.class);
		assertThat(raguilera, is("raguilera"));
	}
	
}
