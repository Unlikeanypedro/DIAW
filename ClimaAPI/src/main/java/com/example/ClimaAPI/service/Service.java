package com.example.ClimaAPI.service;

import com.example.ClimaAPI.model.Clima;
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

        JsonNode root = mapper.readTree(json),
                result = root.get("results").get(0);

        double latitude = result.get("latitude").asDouble(),
                longitude = result.get("longitude").asDouble();

        return "latitude=" + latitude + "&longitude=" + longitude;
    }

    public String consultarClima(String localizacao) {
        String latitudeLongitude = consultarLocalizacao(localizacao);

        String jsonExtract = consultarURL(
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

        ObjectMapper mapperExtract = new ObjectMapper();

        JsonNode rootExtract = mapperExtract.readTree(jsonExtract),
                current = rootExtract.get("current"),
                currentUnits = rootExtract.get("current_units"),
                daily = rootExtract.get("daily"),
                dailyUnits = rootExtract.get("daily_units");

        String temperaturaAtual = current.get("temperature_2m").asString() + currentUnits.get("temperature_2m").asString(),
                arUmidade = current.get("relative_humidity_2m").asString() + currentUnits.get("relative_humidity_2m").asString(),
                ventoVelocidade = current.get("wind_speed_10m").asString() + currentUnits.get("wind_speed_10m").asString(),
                ventoDirecao = current.get("wind_direction_10m").asString() + currentUnits.get("wind_direction_10m").asString(),
                temperaturaMaxima = daily.get("temperature_2m_max").get(0).asString() + dailyUnits.get("temperature_2m_max").asString(),
                temperaturaMinima = daily.get("temperature_2m_min").get(0).asString() + dailyUnits.get("temperature_2m_min").asString(),
                dataHorario = current.get("time").asString();

        Clima clima = new Clima(temperaturaAtual, arUmidade, ventoVelocidade, ventoDirecao, temperaturaMaxima, temperaturaMinima, dataHorario);

        ObjectMapper mapperCreate = new ObjectMapper();

        return mapperCreate.writeValueAsString(clima);
    }
}
