package com.example.ClimaAPI.controller;

import com.example.ClimaAPI.service.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    Service service = new Service();

    @GetMapping("/clima")
    public String consultarClima() {
        return service.consultarClima(service.consultarLocalizacao("Belo+Horizonte"));
    }

    @GetMapping("/clima/{localizacao}")
    public String consultarClima(@PathVariable String localizacao) {
        return service.consultarClima(service.consultarLocalizacao(localizacao));
    }
}
