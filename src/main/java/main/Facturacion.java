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
                            new GestionClientes(gestion).darDeAltaCliente(false);
                            break;
                        case DAR_ALTA_PARTICULAR:
                            new GestionClientes(gestion).darDeAltaCliente(true);
                            break;
                        case VOLVER:
                            break;
                    }
                    break;
                case DAR_BAJA_CLIENTE:
                    new GestionClientes(gestion).darDeBajaCliente();
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    new GestionClientes(gestion).cambiarTarifa();
                    break;
                case MOSTRAR_DATOS_CLIENTE:
                    new GestionClientes(gestion).mostrarCliente();
                    break;
                case MOSTRAR_LISTA_CLIENTES:
                    new GestionClientes(gestion).listarClientes();
                    break;
                case MOSTRAR_CLIENTES_FECHAS:
                    new GestionClientes(gestion).listarClientesEntreFechas();
                    break;
                case DAR_ALTA_LLAMADA:
                    new GestionLlamadas(gestion).darDeAltaLlamada();
                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    new GestionLlamadas(gestion).listarLlamadas();
                    break;
                case MOSTRAR_LLAMADAS_FECHAS:
                    new GestionLlamadas(gestion).listarLlamadasEntreFechas();
                    break;
                case EMITIR_FACTURA:
                    new GestionFacturas(gestion).emitirFactura();
                    break;
                case MOSTRAR_FACTURA_CODIGO:
                    new GestionFacturas(gestion).mostrarFactura();
                    break;
                case MOSTRAR_FACTURAS_CLIENTE:
                    new GestionFacturas(gestion).listarFacturas();
                    break;
                case MOSTRAR_FACTURAS_FECHAS:
                    new GestionFacturas(gestion).listarFacturasEntreFechas();
                    break;
                case SALIR:
                    save();
                    consola.mostrarDatos(Mensaje.SALIR);
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
