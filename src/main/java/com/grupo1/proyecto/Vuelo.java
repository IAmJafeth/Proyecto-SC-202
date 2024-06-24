package com.grupo1.proyecto;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Vuelo {
    private final int numeroVuelo;
    private final String origen;
    private final String destino;
    private final int capacidad;
    private final String fecha;
    private boolean cancelado;
    private static final Set<Integer> idsUsados = new HashSet<>();
    private final Asiento[] asientos;


    public Vuelo(int numeroVuelo, String origen, String destino, int capacidad, String fecha) throws Exception {
        if (checkIDUsado(numeroVuelo)) {
            throw new Exception("El número de vuelo ingresado ya está en uso: " + numeroVuelo);
        }
        this.numeroVuelo = numeroVuelo;
        idsUsados.add(numeroVuelo);
        this.destino = destino;
        this.origen = origen;
        this.capacidad = capacidad;
        if (capacidad < 1) {
            throw new Exception("La capacidad del vuelo debe ser mayor a 0");
        }
        this.fecha = fecha;
        this.cancelado = false;
        this.asientos = new Asiento[capacidad];
        for(int i = 0; i < asientos.length; i++){
            asientos[i] = new Asiento(null,i+1, numeroVuelo);
        }
    }

    public Asiento buscarNumeroAsiento(int numeroAsiento) throws NoSuchElementException {
        if (numeroAsiento < 1 || numeroAsiento > capacidad) {
            throw new NoSuchElementException("No existe el asiento con el número: " + numeroAsiento + " en el vuelo " +
                    numeroVuelo + "\nRango de Asientos [1-" + capacidad + "]");
        }
        return asientos[numeroAsiento - 1];
    }

    public String getDetalles(){
        String estado = isCancelado() ? "Cancelado" : "Válido";
        return "Número de vuelo: " + numeroVuelo+"\n" +
                "Origen: " + origen+"\n" +
                "Destino: " + destino+"\n" +
                "Capacidad: " + capacidad+"\n" +
                "Fecha: " + fecha+"\n" +
                "Estado: " + estado+"\n";
    }

    public String getReservaciones(){
        String asientosStr = "";
        for (Asiento asiento : asientos) {
            if (!asiento.isDisponible()) {
                asientosStr = asientosStr + asiento.getDetalles() + "\n";
            }
        }
        return asientosStr;
    }

    public String getNumeroAsientosDisponibles(){
        String asientosStr = "";
        for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                asientosStr = asientosStr + asiento.getNumeroAsiento() + "\n";
            }
        }
        return asientosStr;
    }

    public boolean isLleno(){
        for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                return false;
            }
        }
        return true;
    }


    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigen() {
        return origen;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void cancelarVuelo() {
        this.cancelado = true;
    }

    public Asiento[] getAsientos(){
        return asientos;
    }

    static boolean checkIDUsado(int id){
        return idsUsados.contains(id);
    }
}
