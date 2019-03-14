package com.autentia.training.devops.model.exception;

public class AirportException extends RuntimeException{

    private static final long serialVersionUID = -4045817409130596296L;
    private final String code;

    public AirportException(String code, String message){
        super(message);
        this.code = code;
    }


    public String getCode(){
        return code;
    }

}
