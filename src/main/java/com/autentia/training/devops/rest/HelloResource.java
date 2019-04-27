package com.autentia.training.devops.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autentia.training.devops.config.SettingsConfig;

@RestController
public class HelloResource {

    @Autowired
    private SettingsConfig settingsConfig;

    @GetMapping("/version")
    public String hello() {
        return "VERSIÓN UPM: " + this.settingsConfig.getVersion();
    }
    
    @GetMapping("/raguilera")
    public String getRaguilera() {
    	return "raguilera";
    }

}