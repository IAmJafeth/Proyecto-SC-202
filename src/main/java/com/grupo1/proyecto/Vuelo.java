package com.grupo1.proyecto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {
    private int numeroVuelo;
    private String destino;
    private String origen;
    private int capacidad;
    private Date fecha;
    private boolean cancelado;
    private static final Set<Integer> idsUsados = new HashSet<>();


    public Vuelo(int numeroVuelo, String destino, String origen, int capacidad, Date fecha) throws Exception {
        if (idsUsados.contains(numeroVuelo)) {
            throw new Exception("El número de vuelo ingresado ya está en uso: " + numeroVuelo);
        }
        this.numeroVuelo = numeroVuelo;
        idsUsados.add(numeroVuelo);
        this.destino = destino;
        this.origen = origen;
        this.capacidad = capacidad;
        this.fecha = fecha;
        this.cancelado = false;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
