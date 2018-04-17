package facturacion.misc;

import facturacion.acciones.*;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuClientes {

    VOLVER("Volver", new MostrarMenuPrincipal()),

    DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente", new MostrarMenuAltaClientes()),
    DAR_BAJA_CLIENTE("Borrar un cliente", new DarBajaCliente()),
    CAMBIAR_TARIFA_CLIENTE("Cambiar la tarifa de un cliente", new CambiarTarifa()),
    MOSTRAR_DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF", new ObtenerCliente()),
    MOSTRAR_LISTA_CLIENTES("Recuperar el listado de todos los clientes", new ListarClientes()),
    MOSTRAR_CLIENTES_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas", new ListarClientesEntreFechas());

    private String descripcion;
    private EjecutaOpcion ejecutaOpcion;

    MenuClientes(String descripcion, EjecutaOpcion ejecutaOpcion) {
        this.descripcion = descripcion;
        this.ejecutaOpcion = ejecutaOpcion;
    }

    public static MenuClientes opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_CLIENTES);
        for (MenuClientes opcion : values()) {
            menu.append(opcion.ordinal());
            menu.append(".- ");
            menu.append(opcion.descripcion());
            menu.append("\n");
        }

        return menu.toString();
    }

    public String descripcion() {
        return descripcion;
    }

    public void ejecuta(Gestion gestion) {
        ejecutaOpcion.ejecuta(gestion);
    }
}
