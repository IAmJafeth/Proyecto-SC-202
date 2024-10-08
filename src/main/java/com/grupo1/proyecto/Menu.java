package com.grupo1.proyecto;

import javax.swing.*;
import java.io.*;
import java.util.NoSuchElementException;

public class Menu {

    private GestorVuelos gestorVuelos;

    public Menu() {
        gestorVuelos = new GestorVuelos();
    }

    public void saveGestorVuelos() {
        try (FileOutputStream fileOut = new FileOutputStream("gestorVuelos.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(gestorVuelos);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadGestorVuelos() {
        try (FileInputStream fileIn = new FileInputStream("gestorVuelos.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            gestorVuelos = (GestorVuelos) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            gestorVuelos = new GestorVuelos(); // Initialize if file not found or error occurs
        }
    }

    private boolean validarRespuesta(String response, int min, int max){
        int intResponse;
        try {
            intResponse = Integer.parseInt(response);
        }catch (NumberFormatException e){
            return false;
        }
        return intResponse >= min && intResponse <= max;
    }

    private boolean validarRespuesta(String response){
        try {
            Integer.parseInt(response);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private int getIntResponse(String menuOptions, int min, int max){
        while (true){
            String menuSelected = JOptionPane.showInputDialog(menuOptions);
            if(menuSelected == null){
                JOptionPane.showMessageDialog(null, "Cerrando Programa");
                System.exit(0);
            }
            if (validarRespuesta(menuSelected,min,max)){
                return Integer.parseInt(menuSelected);
            }

            JOptionPane.showMessageDialog(null, "ERROR: Opción incorrecta\nIngresa un valor entre "+min+" a "+max);
        }
    }

    private int getIntResponse(String menuOptions){
        while (true){
            String menuSelected = JOptionPane.showInputDialog(menuOptions);
            if(menuSelected == null){
                JOptionPane.showMessageDialog(null, "Cerrando Programa");
                System.exit(0);
            }
            if (validarRespuesta(menuSelected)){
                return Integer.parseInt(menuSelected);
            }
            JOptionPane.showMessageDialog(null, "ERROR: Opción incorrecta\nIngresa un valor numérico");
        }
    }

    public void mainMenu(){
        String menuOptions = """
                Menú Principal
                
                1- Añadir vuelos
                2- Buscar vuelos
                3- Buscar asiento
                4- Reservar un asiento
                5- Cancelar una reservación
                6- Cancelar un vuelo
                7- Mostrar todos los vuelos
                8- Mostrar reservaciones
                
                0- Salir""";
        boolean continuar = true;
        while (continuar) {
            switch (getIntResponse(menuOptions, 0, 8)) {
                case 1:
                    agregarVuelo();
                    break;
                case 2:
                    buscarVuelo();
                    break;
                case 3:
                    buscarAsiento();
                    break;
                case 4:
                    reservarAsiento();
                    break;
                case 5:
                    cancelarReservacion();
                    break;
                case 6:
                    cancelarVuelo();
                    break;
                case 7:
                    mostrarVuelos();
                    break;
                case 8:
                    mostrarReservaciones();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Cerrando Programa");
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR: Opción incorrecta");
            }
        }



    }

    private void agregarVuelo(){
        String subMenuName = "Ingresando Vuelo\n\n";
        int numeroVuelo;

        while (true) {
            numeroVuelo = getIntResponse(subMenuName + "Ingresa el número de Vuelo");
            if (Vuelo.checkIDUsado(numeroVuelo)){
                JOptionPane.showMessageDialog(null,"Este Número de Vuelo ya está ocupado\nIngresa uno diferente");
                continue;
            }
            break;
        }

        String origen = JOptionPane.showInputDialog(subMenuName + "Ingresa el Origen del vuelo");
        String destino = JOptionPane.showInputDialog(subMenuName + "Ingresa el Destino del vuelo");

        int capacidad;
        while (true) {
            capacidad = getIntResponse(subMenuName + "Ingresa la capacidad del vuelo");
            if (capacidad < 1){
                JOptionPane.showMessageDialog(null,"La capacidad del vuelo debe ser mayor a 0");
                continue;
            }
            break;
        }

        String date = JOptionPane.showInputDialog(subMenuName + "Ingresa la fecha en formato dd/mm/aaaa\nEjemplo: 31/12/2024");

        try {
            Vuelo vuelo = new Vuelo(numeroVuelo,origen,destino,capacidad,date);
            gestorVuelos.addVuelo(vuelo);
            JOptionPane.showMessageDialog(null,"VUELO GUARDADO CON ÉXITO\n\n"+vuelo.getDetalles());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


    }

    private void buscarVuelo(){
        if (gestorVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO ES POSIBLE BUSCAR VUELOS\nNo se a guardado ningún vuelo");
            return;
        }

        try {
            String subMenuName = "Buscando Vuelo\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo a buscar"));
            JOptionPane.showMessageDialog(null,"Vuelo " + vuelo.getNumeroVuelo() + " encontrado\n\n"+vuelo.getDetalles());
        }catch (NoSuchElementException e){
            JOptionPane.showMessageDialog(null,"ERROR: "+e.getMessage());
        }
    }

    private void buscarAsiento(){
        if (gestorVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO ES POSIBLE BUSCAR ASIENTOS\nNo se a guardado ningún vuelo");
            return;
        }

        try {
            String subMenuName = "Buscando Asiento\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo del Asiento a buscar"));
            Asiento asiento = vuelo.buscarNumeroAsiento(getIntResponse(subMenuName + "Ingrese el Numero de Asiento a buscar"));
            JOptionPane.showMessageDialog(null,"Asiento " + asiento.getNumeroAsiento() + " encontrado\n\n"+asiento.getDetalles());
        }catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    private void reservarAsiento(){
        if (gestorVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO ES POSIBLE RESERVAR ASIENTOS\nNo se a guardado ningún vuelo");
            return;
        }

        try {
            String subMenuName = "Reservando Asiento\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo del Asiento a reservar"));

            if (vuelo.isCancelado()){
                JOptionPane.showMessageDialog(null,"NO ES POSIBLE RESERVAR ASIENTOS\nEl vuelo "+vuelo.getNumeroVuelo()+" está cancelado");
                return;
            }

            if (vuelo.isLleno()){
                JOptionPane.showMessageDialog(null,"NO ES POSIBLE RESERVAR ASIENTOS\nEl vuelo "+vuelo.getNumeroVuelo()+" está lleno");
                return;
            }

            Asiento asiento = vuelo.buscarNumeroAsiento(getIntResponse(subMenuName + "Asientos Disponibles en vuelo "
                    + vuelo.getNumeroVuelo() + ":\n" + vuelo.getNumeroAsientosDisponibles()+"\nIngrese el Numero de Asiento a reservar", 1, vuelo.getCapacidad()));
            String pasajero = JOptionPane.showInputDialog(subMenuName + "Ingrese el nombre del pasajero");
            asiento.reservarAsiento(pasajero);
            JOptionPane.showMessageDialog(null, "ASIENTO RESERVADO CON ÉXITO\n\n"+asiento.getDetalles());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    private void cancelarReservacion(){
        if (gestorVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO ES POSIBLE CANCELAR ASIENTOS\nNo se a guardado ningún vuelo");
            return;
        }

        try {
            String subMenuName = "Cancelando Asiento\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo del Asiento a cancelar"));
            Asiento asiento = vuelo.buscarNumeroAsiento(getIntResponse(subMenuName + "Ingrese el Numero de Asiento a cancelar", 1, vuelo.getCapacidad()));
            asiento.liberarAsiento();
            JOptionPane.showMessageDialog(null, "ASIENTO LIBERADO CON ÉXITO\n\n"+asiento.getDetalles());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    private void cancelarVuelo(){
        try {
            String subMenuName = "Cancelando Vuelo\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo a cancelar"));
            vuelo.cancelarVuelo();
            JOptionPane.showMessageDialog(null, "VUELO CANCELADO CON ÉXITO\n\n"+vuelo.getDetalles());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    private void mostrarVuelos(){
        JOptionPane.showMessageDialog(null, "Mostrando todos los Vuelos \n\n" + gestorVuelos.listarTodosLosVuelos());

    }

    private void mostrarReservaciones(){
        if (gestorVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO HAY RESERVACIONES\nNo se a guardado ningún vuelo");
            return;
        }

        try {
            String subMenuName = "Mostrando Reservaciones\n\n";
            Vuelo vuelo = gestorVuelos.buscarVueloPorID(getIntResponse(subMenuName + "Ingrese el Numero de Vuelo a mostrar"));
            JOptionPane.showMessageDialog(null,"Reservaciones del Vuelo " + vuelo.getNumeroVuelo() + "\n\n"+vuelo.getReservaciones());
        }catch (NoSuchElementException e){
            JOptionPane.showMessageDialog(null,"ERROR: "+e.getMessage());
        }
    }
}
