package com.autentia.training.devops.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class Airport {
    
    private String code;
    
    private String name;

    private City city;
    
    public Airport() {}
    
    
    
    @JsonCreator
    public Airport(@JsonProperty("code") String code, @JsonProperty("name") String name, @JsonProperty("city") City city){
        super();
        this.code = code;
        this.name = name;
        this.city = city;
    }

}
