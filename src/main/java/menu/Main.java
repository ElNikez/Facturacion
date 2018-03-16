package menu;

import facturacion.gestion.Gestion;
import facturacion.misc.GestionClientes;
import facturacion.misc.GestionFacturas;
import facturacion.misc.GestionLlamadas;
import facturacion.misc.Consola;
import facturacion.misc.Mensaje;
import facturacion.misc.MenuClientes;
import facturacion.misc.MenuPrincipal;

public class Main {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
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
                    System.exit(0);
            }
        }
    }

//    public static void main(String[] args) {
//        int opcion;
//        do {
//            consola.mostrarDatos("\n¿Qué tipo de gestión quieres realizar?");
//            consola.mostrarDatos("\n0.- Salir");
//            consola.mostrarDatos("\n1.- Gestión de facturacion.clientes");
//            consola.mostrarDatos("\n2.- Gestión de facturacion.llamadas");
//            consola.mostrarDatos("\n3.- Gestión de facturas");
//            consola.mostrarDatos("\nIntroduce una opción: ");
//            opcion = Integer.parseInt(consola.pedirDatos());
//            switch (opcion) {
//                case 1:
//                    consola.mostrarDatos("\n1.- Dar de alta un nuevo cliente.");
//                    consola.mostrarDatos("\n2.- Borrar un cliente.");
//                    consola.mostrarDatos("\n3.- Cambiar la tarifa de un cliente.");
//                    consola.mostrarDatos("\n4.- Recuperar los datos de un cliente a partir de su NIF.");
//                    consola.mostrarDatos("\n5.- Recuperar el listado de todos los clientes.");
//                    consola.mostrarDatos("\n6.- Mostrar un listado de clientes que fueron dados de alta entre dos fechas.");
//                    consola.mostrarDatos("\nIntroduce una opción: ");
//                    opcion = Integer.parseInt(consola.pedirDatos());
//                    int tipoCliente;
//                    switch (opcion) {
//                        case 1:
//                            consola.mostrarDatos("\n1.- Dar de alta a una empresa");
//                            consola.mostrarDatos("\n2.- Dar de alta a un particular");
//                            consola.mostrarDatos("\nIntroduce una opción: ");
//                            tipoCliente = Integer.parseInt(consola.pedirDatos());
//                            switch (tipoCliente) {
//                                case 1:
//                                    new GestionClientes().darDeAltaCliente(false);
//                                    break;
//                                case 2:
//                                    new GestionClientes().darDeAltaCliente(true);
//                                    break;
//                                default:
//                                    break;
//                            }
//                            break;
//                        case 2:
//                            new GestionClientes().darDeBajaCliente();
//                            break;
//                        case 3:
//                            new GestionClientes().cambiarTarifa();
//                            break;
//                        case 4:
//                            new GestionClientes().mostrarCliente();
//                            break;
//                        case 5:
//                            new GestionClientes().listarClientes();
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                case 2:
//                    consola.mostrarDatos("\n1.- Dar de alta una llamada.");
//                    consola.mostrarDatos("\n2.- Listar todas las llamadas de un cliente.");
//                    consola.mostrarDatos("\n3.- Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas.");
//                    consola.mostrarDatos("\nIntroduce una opción: ");
//                    opcion = Integer.parseInt(consola.pedirDatos());
//                    switch (opcion) {
//                        case 1:
//                            new GestionLlamadas().darDeAltaLlamada();
//                            break;
//                        case 2:
//                            new GestionLlamadas().listarLlamadas();
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                case 3:
//                    consola.mostrarDatos("\n1.- Emitir una factura para un cliente.");
//                    consola.mostrarDatos("\n2.- Recuperar los datos de una factura a partir de su código.");
//                    consola.mostrarDatos("\n3.- Recuperar todas las facturas de un cliente.");
//                    consola.mostrarDatos("\n3.- Mostrar un listado de facturas de un cliente emitidas entre dos fechas.");
//                    consola.mostrarDatos("\nIntroduce una opción: ");
//                    opcion = Integer.parseInt(consola.pedirDatos());
//                    switch (opcion) {
//                        case 1:
//                            new GestionFacturas().emitirFactura();
//                            break;
//                        case 2:
//                            new GestionFacturas().mostrarFactura();
//                            break;
//                        case 3:
//                            new GestionFacturas().listarFacturas();
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        } while (opcion != 0);
//    }

}
