package com.autentia.training.devops.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


public @Data class City {

    private Integer id;

    private String name;

    public City(){}

    @JsonCreator
    public City(@JsonProperty("id") Integer id, @JsonProperty("name") String name){
        super();
        this.id = id;
        this.name = name;
    }

}
