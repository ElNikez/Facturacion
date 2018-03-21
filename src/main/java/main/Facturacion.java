package main;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestorClientes;
import facturacion.gestores.GestorFacturas;
import facturacion.gestores.GestorLlamadas;
import facturacion.misc.*;

import static facturacion.misc.Mensaje.*;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        load();
        mostrarMenuPrincipal();
        save();
    }

    private void mostrarMenuPrincipal() {
        String opcionString;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuPrincipal.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuPrincipal opcionMenu = MenuPrincipal.opcion(opcionByte);
//        do {
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
            default:
                mostrarMenuPrincipal();
                break;
        }
//        } while (opcionPrincipal != SALIR);
    }

    private void mostrarMenuClientes() {
        String opcionString;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuClientes.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuClientes.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuClientes opcionMenu = MenuClientes.opcion(opcionByte);
//        do {
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
            default:
                mostrarMenuClientes();
                break;
        }
//        } while (opcionClientes != MenuClientes.VOLVER);
    }

    private void mostrarMenuAltaClientes() {
        String opcionString;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuAltaClientes.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuAltaClientes.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuAltaClientes opcionMenu = MenuAltaClientes.opcion(opcionByte);
//        do {
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
            default:
                mostrarMenuAltaClientes();
                break;
        }
//        } while (opcionAltaClientes != MenuAltaClientes.VOLVER);
    }

    private void mostrarMenuLlamadas() {
        String opcionString;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuLlamadas.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuLlamadas.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuLlamadas opcionMenu = MenuLlamadas.opcion(opcionByte);
//        do {
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
                new GestorFacturas(gestion).listarFacturasEntreFechas();
                break;
            default:
                mostrarMenuLlamadas();
                break;
        }
//        } while (opcionLlamadas != MenuLlamadas.VOLVER);
    }

    private void mostrarMenuFacturas() {
        String opcionString;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuFacturas.mostrarMenu());
            opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuFacturas.values().length)
                consola.mostrarDatos(MENU_OPCION_CORRECTA);
            else
                break;
        } while (true);
        byte opcionByte = Byte.parseByte(opcionString);
        MenuFacturas opcionMenu = MenuFacturas.opcion(opcionByte);
//        do {
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
            default:
                mostrarMenuFacturas();
                break;
        }
//        } while (opcionFacturas != MenuFacturas.VOLVER);
    }

    private void load() {
        consola.mostrarDatos(CARGANDO_DATOS);
        gestion.cargarDatos();
        consola.mostrarDatos(DATOS_CARGADOS);
    }

    private void save() {
        consola.mostrarDatos(GUARDANDO_DATOS);
        gestion.guardarDatos();
        consola.mostrarDatos(DATOS_GUARDADOS);
    }

}
