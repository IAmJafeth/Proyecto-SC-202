package com.grupo1.proyecto;

public class Asiento {
    private String pasajero;
    private final int numeroAsiento;
    private final int numeroVuelo;

    public Asiento(String pasajero, int numeroAsiento, int numeroVuelo){
        this.pasajero = pasajero;
        this.numeroAsiento = numeroAsiento;
        this.numeroVuelo = numeroVuelo;
    }

    public void reservarAsiento(String pasajero) throws Exception{
        if (isDisponible()){
            this.pasajero = pasajero;
        }
        else {
            throw new Exception("El asiento " + this.numeroAsiento + " del vuelo +" + this.numeroVuelo +
                    " no está disponible\n" + "Por favor seleccionar otro asiento");
        }
    }

    public void liberarAsiento() throws Exception{
        if (!isDisponible()){
            this.pasajero = null;
        }
        else {
            throw new Exception("El asiento seleccionado ya está libre");
        }
    }

    public boolean isDisponible() {
        return pasajero == null;
    }

    public String getPasajero() {
        return pasajero;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

}
