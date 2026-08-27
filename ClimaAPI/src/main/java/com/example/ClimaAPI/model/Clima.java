package com.example.ClimaAPI.model;

public class Clima {
    private String temperaturaAtual;
    private String arUmidade;
    private String ventoVelocidade;
    private String ventoDirecao;
    private String temperaturaMaxima;
    private String temperaturaMinima;
    private String dataHorario;

    public Clima() {}

    public Clima(
            String temperaturaAtual,
            String arUmidade,
            String ventoVelocidade,
            String ventoDirecao,
            String temperaturaMaxima,
            String temperaturaMinima,
            String dataHorario
    ) {
        this.setTemperaturaAtual(temperaturaAtual);
        this.setArUmidade(arUmidade);
        this.setVentoVelocidade(ventoVelocidade);
        this.setVentoDirecao(ventoDirecao);
        this.setTemperaturaMaxima(temperaturaMaxima);
        this.setTemperaturaMinima(temperaturaMinima);
        this.setDataHorario(dataHorario);
    }

    public String getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(String temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public String getArUmidade() {
        return arUmidade;
    }

    public void setArUmidade(String arUmidade) {
        this.arUmidade = arUmidade;
    }

    public String getVentoVelocidade() {
        return ventoVelocidade;
    }

    public void setVentoVelocidade(String ventoVelocidade) {
        this.ventoVelocidade = ventoVelocidade;
    }

    public String getVentoDirecao() {
        return ventoDirecao;
    }

    public void setVentoDirecao(String ventoDirecao) {
        this.ventoDirecao = ventoDirecao;
    }

    public String getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(String temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public String getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(String temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public String getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(String dataHorario) {
        this.dataHorario = dataHorario;
    }
}
