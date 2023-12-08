package com.example.ecorecicla.modelo;

public class Liquidos {

    private float medida;
    private float cantidad;
    private String mes;

    public Liquidos(float medida, float cantidad, String mes) {
        this.medida = medida;
        this.cantidad = cantidad;
        this.mes = mes;

    }

    public float getMedida() {
        return medida;
    }

    public void setMedida(float medida) {
        this.medida = medida;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
