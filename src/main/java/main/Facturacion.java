package main;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestorClientes;
import facturacion.gestores.GestorFacturas;
import facturacion.gestores.GestorLlamadas;
import facturacion.misc.*;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.MENU_OPCION_CORRECTA;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        gestion.cargarDatos();
        mostrarMenuPrincipal();
        gestion.guardarDatos();
    }

    private void mostrarMenuPrincipal() {
        String opcionString;
        do {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            int opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuPrincipal.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuPrincipal opcionMenu = MenuPrincipal.opcion(opcionByte);
        switch (opcionMenu) {
            case SALIR:
                break;
            case MENU_CLIENTES:
                mostrarMenuClientes();
                break;
            case MENU_LLAMADAS:
                mostrarMenuLlamadas();
                break;
            case MENU_FACTURAS:
                mostrarMenuFacturas();
                break;
        }
    }

    private void mostrarMenuClientes() {
        String opcionString;
        do {
            consola.mostrarDatos(MenuClientes.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            int opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuClientes.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuClientes opcionMenu = MenuClientes.opcion(opcionByte);
        switch (opcionMenu) {
            case VOLVER:
                mostrarMenuPrincipal();
                break;
            case DAR_ALTA_CLIENTE:
                mostrarMenuAltaClientes();
                break;
            case DAR_BAJA_CLIENTE:
                new GestorClientes(gestion).darDeBajaCliente();
                break;
            case CAMBIAR_TARIFA_CLIENTE:
                new GestorClientes(gestion).cambiarTarifa();
                break;
            case MOSTRAR_DATOS_CLIENTE:
                new GestorClientes(gestion).mostrarCliente();
                break;
            case MOSTRAR_LISTA_CLIENTES:
                new GestorClientes(gestion).listarClientes();
                break;
            case MOSTRAR_CLIENTES_FECHAS:
                new GestorClientes(gestion).listarClientesEntreFechas();
                break;
        }
    }

    private void mostrarMenuAltaClientes() {
        String opcionString;
        do {
            consola.mostrarDatos(MenuAltaClientes.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            int opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuAltaClientes.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuAltaClientes opcionMenu = MenuAltaClientes.opcion(opcionByte);
        switch (opcionMenu) {
            case VOLVER:
                mostrarMenuClientes();
                break;
            case DAR_ALTA_EMPRESA:
                new GestorClientes(gestion).darDeAltaCliente(false);
                break;
            case DAR_ALTA_PARTICULAR:
                new GestorClientes(gestion).darDeAltaCliente(true);
                break;
        }
    }

    private void mostrarMenuLlamadas() {
        String opcionString;
        do {
            consola.mostrarDatos(MenuLlamadas.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            int opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuLlamadas.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuLlamadas opcionMenu = MenuLlamadas.opcion(opcionByte);
        switch (opcionMenu) {
            case VOLVER:
                mostrarMenuPrincipal();
                break;
            case DAR_ALTA_LLAMADA:
                new GestorLlamadas(gestion).darDeAltaLlamada();
                break;
            case MOSTRAR_LISTA_LLAMADAS:
                new GestorLlamadas(gestion).listarLlamadas();
                break;
            case MOSTRAR_LLAMADAS_FECHAS:
                new GestorLlamadas(gestion).listarLlamadasEntreFechas();
                break;
        }
    }

    private void mostrarMenuFacturas() {
        String opcionString;
        do {
            consola.mostrarDatos(MenuFacturas.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            int opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuFacturas.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuFacturas opcionMenu = MenuFacturas.opcion(opcionByte);
        switch (opcionMenu) {
            case VOLVER:
                mostrarMenuPrincipal();
                break;
            case EMITIR_FACTURA:
                new GestorFacturas(gestion).emitirFactura();
                break;
            case MOSTRAR_FACTURA_CODIGO:
                new GestorFacturas(gestion).mostrarFactura();
                break;
            case MOSTRAR_FACTURAS_CLIENTE:
                new GestorFacturas(gestion).listarFacturas();
                break;
            case MOSTRAR_FACTURAS_FECHAS:
                new GestorFacturas(gestion).listarFacturasEntreFechas();
                break;
        }
    }

}
