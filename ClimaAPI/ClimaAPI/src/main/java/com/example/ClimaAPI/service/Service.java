package com.example.ClimaAPI.service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Service {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast?";
    private static final String BASE_URL_GEO = "https://geocoding-api.open-meteo.com/v1/search?";

    private String consultarURL(String apiUrl){
        String dados = "";
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
        return consultarURL(BASE_URL_GEO + "&name=" + localizacao);
    }
    public String consultarTemperaturaAtual(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=temperature_2m");
    }
    public String consultarUmidadeDoAr(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=relative_humidity_2m");
    }
    public String consultarVelocidadeDoVento(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=wind_speed_10m");
    }
    public String consultarDirecaoDoVento(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=wind_direction_10m");
    }
    public String consultarTemperaturaMaxima(String longLat) {
        return consultarURL(BASE_URL + longLat + "&daily=temperature_2m_max");
    }
    public String consultarTemperaturaMinima(String longLat) {
        return consultarURL(BASE_URL + longLat + "&daily=temperature_2m_min");
    }
    public String consultarCondicao(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=weather_code");
    }
    public String receberDataHora(String longLat) {
        return consultarURL(BASE_URL + longLat + "&timezone=auto");
    }
    public String consultarData(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=temperature_2m");
    }
    public String consultarHora(String longLat) {
        return consultarURL(BASE_URL + longLat + "&current=temperature_2m");
    }

}