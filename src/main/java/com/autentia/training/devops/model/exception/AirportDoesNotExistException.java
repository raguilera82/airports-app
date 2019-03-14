package com.autentia.training.devops.model.exception;

public class AirportDoesNotExistException extends AirportException{
    private static final long serialVersionUID = -4013689289009158336L;

    public AirportDoesNotExistException(String code, String message){
        super(code, message);
    }

    
}
