package com.autentia.training.devops.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage{
    
    private final String error;
    private final String detail;
    
    @JsonCreator
    public ErrorMessage(@JsonProperty("error") String error, @JsonProperty("detail") String detail){
        super();
        this.error = error;
        this.detail = detail;
    }

    public String getError(){
        return error;
    }

    public String getDetail(){
        return detail;
    }

}
