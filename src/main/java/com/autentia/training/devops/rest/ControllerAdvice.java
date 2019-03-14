package com.autentia.training.devops.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.autentia.training.devops.model.entity.ErrorMessage;
import com.autentia.training.devops.model.exception.AirportCityDoesNotExistException;
import com.autentia.training.devops.model.exception.AirportDoesNotExistException;
import com.autentia.training.devops.model.exception.AirportException;


@RestControllerAdvice(annotations = RestController.class)
public class ControllerAdvice{
    
    
    @Autowired
    MessageSource messages;
    
    
    @ExceptionHandler(AirportDoesNotExistException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ErrorMessage handleAirportDoesNotExistException(AirportDoesNotExistException exception) {
        return new ErrorMessage(getMessage(exception), exception.getMessage());
    }
    
    
    @ExceptionHandler(AirportCityDoesNotExistException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ErrorMessage handleAirportDoesNotExistException(AirportCityDoesNotExistException exception) {
        return new ErrorMessage(getMessage(exception), exception.getMessage());
    }
    
    
    private String getMessage(AirportException e) {
        return messages.getMessage(e.getCode(), null, LocaleContextHolder.getLocale());
    }
    
   
}
