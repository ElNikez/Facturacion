package main;

import facturacion.acciones.MostrarMenuPrincipal;
import facturacion.gestion.Gestion;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private MostrarMenuPrincipal menuPrincipal = new MostrarMenuPrincipal();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        gestion.cargarDatos();
        menuPrincipal.ejecuta(gestion);
        gestion.guardarDatos();
    }

}
