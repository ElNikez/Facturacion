package facturacion.misc;

import facturacion.acciones.MostrarMenuClientes;
import facturacion.acciones.MostrarMenuFacturas;
import facturacion.acciones.MostrarMenuLlamadas;
import facturacion.acciones.Salir;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.MENU_PRINCIPAL;

public enum MenuPrincipal {

    SALIR("Salir", new Salir()),

    MENU_CLIENTES("Gestión de clientes", new MostrarMenuClientes()),
    MENU_LLAMADAS("Gestión de llamadas", new MostrarMenuLlamadas()),
    MENU_FACTURAS("Gestión de facturas", new MostrarMenuFacturas());

    private String descripcion;
    private EjecutaOpcion ejecutaOpcion;

    MenuPrincipal(String descripcion, EjecutaOpcion ejecutaOpcion) {
        this.descripcion = descripcion;
        this.ejecutaOpcion = ejecutaOpcion;
    }

    public static MenuPrincipal opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_PRINCIPAL);
        for (MenuPrincipal opcion : MenuPrincipal.values()) {
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
