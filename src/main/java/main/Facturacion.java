package main;

import facturacion.factorias.FactoriaGestores;
import facturacion.gestion.Gestion;
import facturacion.misc.*;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();
    private FactoriaGestores factoriaGestor = new FactoriaGestores();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        gestion.cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        MenuPrincipal opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuPrincipal.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuPrincipal.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuPrincipal.opcion(opcionByte);
            switch (opcionMenu) {
                case SALIR:
                    gestion.guardarDatos();
                    System.exit(0);
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
        } while (true);
    }

    private void mostrarMenuClientes() {
        MenuClientes opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuClientes.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuClientes.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuClientes.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    return;
                case DAR_ALTA_CLIENTE:
                    mostrarMenuAltaClientes();
                    break;
                case DAR_BAJA_CLIENTE:
                    factoriaGestor.gestorClientes(gestion).darDeBajaCliente();
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    factoriaGestor.gestorClientes(gestion).cambiarTarifa();
                    break;
                case MOSTRAR_DATOS_CLIENTE:
                    factoriaGestor.gestorClientes(gestion).mostrarCliente();
                    break;
                case MOSTRAR_LISTA_CLIENTES:
                    factoriaGestor.gestorClientes(gestion).listarClientes();
                    break;
                case MOSTRAR_CLIENTES_FECHAS:
                    factoriaGestor.gestorClientes(gestion).listarClientesEntreFechas();
                    break;
            }
        } while (true);
    }

    private void mostrarMenuAltaClientes() {
        MenuAltaClientes opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuAltaClientes.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuAltaClientes.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuAltaClientes.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    return;
                case DAR_ALTA_EMPRESA:
                    factoriaGestor.gestorClientes(gestion).darDeAltaCliente(false);
                    break;
                case DAR_ALTA_PARTICULAR:
                    factoriaGestor.gestorClientes(gestion).darDeAltaCliente(true);
                    break;
            }
        } while (true);
    }

    private void mostrarMenuLlamadas() {
        MenuLlamadas opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuLlamadas.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuLlamadas.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuLlamadas.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    return;
                case DAR_ALTA_LLAMADA:
                    factoriaGestor.gestorLlamadas(gestion).darDeAltaLlamada();
                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    factoriaGestor.gestorLlamadas(gestion).listarLlamadas();
                    break;
                case MOSTRAR_LLAMADAS_FECHAS:
                    factoriaGestor.gestorLlamadas(gestion).listarLlamadasEntreFechas();
                    break;
            }
        } while (true);
    }

    private void mostrarMenuFacturas() {
        MenuFacturas opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuFacturas.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuFacturas.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuFacturas.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    return;
                case EMITIR_FACTURA:
                    factoriaGestor.gestorFacturas(gestion).emitirFactura();
                    break;
                case MOSTRAR_FACTURA_CODIGO:
                    factoriaGestor.gestorFacturas(gestion).mostrarFactura();
                    break;
                case MOSTRAR_FACTURAS_CLIENTE:
                    factoriaGestor.gestorFacturas(gestion).listarFacturas();
                    break;
                case MOSTRAR_FACTURAS_FECHAS:
                    factoriaGestor.gestorFacturas(gestion).listarFacturasEntreFechas();
                    break;
            }
        } while (true);
    }

}
