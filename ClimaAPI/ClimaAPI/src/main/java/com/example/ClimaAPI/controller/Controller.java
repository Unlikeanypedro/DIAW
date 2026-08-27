package com.example.ClimaAPI.controller;

import com.example.ClimaAPI.service.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Service service = new Service();

    @GetMapping("/clima")
    public String consultarLocalizacao(){
        
        return service.consultarLocalizacao("Belo+Horizonte");
    }
    @GetMapping("/clima/{localizacao}")
    public String consultarLocalizacao(@PathVariable String localizacao){
        return service.consultarLocalizacao(localizacao);
    }
}
