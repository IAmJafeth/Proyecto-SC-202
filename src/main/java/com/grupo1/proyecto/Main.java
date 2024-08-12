package com.grupo1.proyecto;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.loadGestorVuelos();

        Runtime.getRuntime().addShutdownHook(new Thread(menu::saveGestorVuelos));

        menu.mainMenu();
    }
}