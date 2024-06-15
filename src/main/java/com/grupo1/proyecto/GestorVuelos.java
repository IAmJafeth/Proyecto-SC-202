package com.grupo1.proyecto;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GestorVuelos {
    private final ArrayList<Vuelo> listaDeVuelos;

    public GestorVuelos() {
        this.listaDeVuelos = new ArrayList<>();
    }

    public void addVuelo(Vuelo vuelo){
        this.listaDeVuelos.add(vuelo);
    }

    public ArrayList<Vuelo> getVuelos(){
        return this.listaDeVuelos;
    }

    public void mostrarVuelos(){ //TODO: Complete functionality
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Vuelo buscarVueloPorID(int id) throws Exception{
        for(int i = 0; i < this.listaDeVuelos.size(); i++){
            if(this.listaDeVuelos.get(i).getId() == id){ return this.listaDeVuelos.get(i); }
        }
        throw new NoSuchElementException("No existe el Vuelo con el número: " + id);
    }
}
