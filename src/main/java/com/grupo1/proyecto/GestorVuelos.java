package com.grupo1.proyecto;

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

    public String listarTodosLosVuelos(){
        if (listaDeVuelos.isEmpty()){
            return "No hay vuelos disponibles";
        }
        String vuelosStr = "";
        for (Vuelo vuelo : listaDeVuelos) {
            vuelosStr = vuelosStr + vuelo.getDetalles() + "\n";
        }
        return vuelosStr;
    }

    public Vuelo buscarVueloPorID(int numeroVuelo) throws NoSuchElementException{
        for(int i = 0; i < this.listaDeVuelos.size(); i++){
            if(this.listaDeVuelos.get(i).getNumeroVuelo() == numeroVuelo){ return this.listaDeVuelos.get(i); }
        }
        throw new NoSuchElementException("No existe el Vuelo con el número: " + numeroVuelo);
    }
}
