import gestion.Gestion;
import gestion.GestionClientes;
import gestion.GestionFacturas;
import gestion.GestionLlamadas;
import misc.Consola;
import misc.MenuClientes;
import misc.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        new Gestion();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Consola consola = new Consola();

        while (true) {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            consola.mostrarDatos("Introduce una opción: ");
            byte opcion = Byte.parseByte(consola.pedirDatos());
            MenuPrincipal opcionMenu = MenuPrincipal.opcion(opcion);
            switch (opcionMenu) {
                case DAR_ALTA_CLIENTE:
                    new GestionClientes().darDeAltaCliente();
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
                case DAR_ALTA_LLAMADA:
                    new GestionLlamadas().darDeAltaLlamada();
                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    new GestionLlamadas().listarLlamadas();
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
                case SALIR:
                    consola.mostrarDatos("");
                    System.exit(0);
            }
        }
    }

}
