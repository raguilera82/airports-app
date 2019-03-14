package com.autentia.training.devops.model.exception;

public class AirportCityDoesNotExistException extends AirportException{
    
    private static final long serialVersionUID = 7195609906743679214L;

    public AirportCityDoesNotExistException(String code, String message){
        super(code, message);
    }

    
}
