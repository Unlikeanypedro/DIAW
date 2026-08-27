package com.example.ClimaAPI.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Service {
    private static final String BASE_URL_FORECAST = "https://api.open-meteo.com/v1/forecast?";
    private static final String BASE_URL_GEOCODING = "https://geocoding-api.open-meteo.com/v1/search?";

    private String consultarURL(String apiUrl){
        String dados;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
        } else {
            dados = "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }

        return dados;
    }

    public String consultarLocalizacao(String localizacao) {
        return consultarURL(BASE_URL_GEOCODING + "&name=" + localizacao);
    }

    public String consultarClima(String longitudeLatitude) {
        return consultarURL(
                BASE_URL_FORECAST
                + longitudeLatitude
                + "&current=temperature_2m"
                + "&current=relative_humidity_2m"
                + "&current=wind_speed_10m"
                + "&current=wind_direction_10m"
                + "&daily=temperature_2m_max"
                + "&daily=temperature_2m_min"
                + "&current=weather_code"
                + "&timezone=auto"
                + "&current=temperature_2m"
        );
    }

    public String consultarTemperaturaAtual(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&current=temperature_2m");
    }

    public String consultarUmidadeDoAr(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&current=relative_humidity_2m");
    }

    public String consultarVelocidadeDoVento(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&current=wind_speed_10m");
    }

    public String consultarDirecaoDoVento(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&current=wind_direction_10m");
    }

    public String consultarTemperaturaMaxima(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&daily=temperature_2m_max");
    }

    public String consultarTemperaturaMinima(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&daily=temperature_2m_min");
    }

    public String consultarCondicao(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&current=weather_code");
    }

    public String receberDataHora(String longitudeLatitude) {
        return consultarURL(BASE_URL_FORECAST + longitudeLatitude + "&timezone=auto");
    }
}
