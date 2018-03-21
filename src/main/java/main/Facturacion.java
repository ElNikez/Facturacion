package main;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestorClientes;
import facturacion.gestores.GestorFacturas;
import facturacion.gestores.GestorLlamadas;
import facturacion.misc.Consola;
import facturacion.misc.MenuClientes;
import facturacion.misc.MenuPrincipal;

import static facturacion.misc.Mensaje.*;
import static facturacion.misc.MenuPrincipal.SALIR;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        load();

        MenuPrincipal opcionMenu;
        do {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            byte opcion = Byte.parseByte(consola.pedirDatos(INTRODUCE_OPCION));
            opcionMenu = MenuPrincipal.opcion(opcion);
            switch (opcionMenu) {
                case SALIR:
                    save();
                    break;
                case DAR_ALTA_CLIENTE:
                    consola.mostrarDatos(MenuClientes.mostrarMenu());
                    byte opcionClientes = Byte.parseByte(consola.pedirDatos(INTRODUCE_OPCION));
                    MenuClientes opcionMenuClientes = MenuClientes.opcion(opcionClientes);
                    switch (opcionMenuClientes) {
                        case DAR_ALTA_EMPRESA:
                            new GestorClientes(gestion).darDeAltaCliente(false);
                            break;
                        case DAR_ALTA_PARTICULAR:
                            new GestorClientes(gestion).darDeAltaCliente(true);
                            break;
                        case VOLVER:
                            break;
                    }
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
                case DAR_ALTA_LLAMADA:
                    new GestorLlamadas(gestion).darDeAltaLlamada();
                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    new GestorLlamadas(gestion).listarLlamadas();
                    break;
                case MOSTRAR_LLAMADAS_FECHAS:
                    new GestorLlamadas(gestion).listarLlamadasEntreFechas();
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
        } while (opcionMenu != SALIR);
    }

    private void load() {
        gestion.cargarDatos();
        consola.mostrarDatos(DATOS_CARGADOS);
    }

    private void save() {
        gestion.guardarDatos();
        consola.mostrarDatos(DATOS_GUARDADOS);
    }

}
