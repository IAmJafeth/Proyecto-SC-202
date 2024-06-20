package com.grupo1.proyecto;

public class Asiento {
    private String pasajero;
    private int numeroAsiento;
    private int ;

    public Asiento(String pasajero, int numeroAsiento, int numeroVuelo){
        this.pasajero = pasajero;
        this.numeroAsiento = numeroAsiento;
        this.numeroVuelo = numeroVuelo;
    }

    public boolean isDisponible() {
        return pasajero == null;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }
}
