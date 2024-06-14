package com.grupo1.proyecto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {
    private int id;
    private String destino;
    private String origen;
    private int capacidad;
    private Date fecha;
    private static final Set<Integer> idsUsados = new HashSet<>();


    public Vuelo(int id, String destino, String origen, int capacidad, Date fecha) throws Exception {
        if (idsUsados.contains(id)) {
            throw new Exception("El número de vuelo ingresado ya está en uso: " + id);
        }
        this.id = id;
        idsUsados.add(id);
        this.destino = destino;
        this.origen = origen;
        this.capacidad = capacidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
