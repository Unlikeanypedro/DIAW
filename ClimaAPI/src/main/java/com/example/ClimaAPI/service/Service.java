package com.example.ClimaAPI.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

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
        String json = consultarURL(BASE_URL_GEOCODING + "name=" + localizacao);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        double latitude = root.get("results").get(0).get("latitude").asDouble();
        double longitude = root.get("results").get(0).get("longitude").asDouble();

        return "latitude=" + latitude + "&longitude=" + longitude;
    }

    public String consultarClima(String latitudeLongitude) {
        return consultarURL(
                BASE_URL_FORECAST
                + latitudeLongitude
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
}
