package main;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestionClientes;
import facturacion.gestores.GestionFacturas;
import facturacion.gestores.GestionLlamadas;
import facturacion.misc.Consola;
import facturacion.misc.Mensaje;
import facturacion.misc.MenuClientes;
import facturacion.misc.MenuPrincipal;

public class Facturacion {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Facturacion().start();
    }

    private void start() {
        load();
        MenuPrincipal opcionMenu;
        while(true) {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            consola.mostrarDatos(Mensaje.INTRODUCE_OPCION);
            byte opcion = Byte.parseByte(consola.pedirDatos());
            opcionMenu = MenuPrincipal.opcion(opcion);
            switch (opcionMenu) {
                case DAR_ALTA_CLIENTE:
                    consola.mostrarDatos(MenuClientes.mostrarMenu());
                    consola.mostrarDatos(Mensaje.INTRODUCE_OPCION);
                    byte opcionClientes = Byte.parseByte(consola.pedirDatos());
                    MenuClientes opcionMenuClientes = MenuClientes.opcion(opcionClientes);
                    switch (opcionMenuClientes) {
                        case DAR_ALTA_EMPRESA:
                            new GestionClientes().darDeAltaCliente(false);
                            break;
                        case DAR_ALTA_PARTICULAR:
                            new GestionClientes().darDeAltaCliente(true);
                            break;
                        case VOLVER:
                            break;
                    }
                    break;
                case DAR_BAJA_CLIENTE:
                    new GestionClientes().darDeBajaCliente();
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    new GestionClientes().cambiarTarifa();
                    break;
                case MOSTRAR_DATOS_CLIENTE:
                    new GestionClientes().mostrarCliente();
                    break;
                case MOSTRAR_LISTA_CLIENTES:
                    new GestionClientes().listarClientes();
                    break;
                case MOSTRAR_CLIENTES_FECHAS:
                    new GestionClientes().listarClientesEntreFechas();
                    break;
                case DAR_ALTA_LLAMADA:
                    new GestionLlamadas().darDeAltaLlamada();
                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    new GestionLlamadas().listarLlamadas();
                    break;
                case MOSTRAR_LLAMADAS_FECHAS:
                    new GestionLlamadas().listarLlamadasEntreFechas();
                    break;
                case EMITIR_FACTURA:
                    new GestionFacturas().emitirFactura();
                    break;
                case MOSTRAR_FACTURA_CODIGO:
                    new GestionFacturas().mostrarFactura();
                    break;
                case MOSTRAR_FACTURAS_CLIENTE:
                    new GestionFacturas().listarFacturas();
                    break;
                case MOSTRAR_FACTURAS_FECHAS:
                    new GestionFacturas().listarFacturasEntreFechas();
                    break;
                case SALIR:
                    save();
                    System.exit(0);
            }
        }
    }

    private void load() {
        gestion.cargarDatos();
    }

    private void save() {
        gestion.guardarDatos();
    }

}
