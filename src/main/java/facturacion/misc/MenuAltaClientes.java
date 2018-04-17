package facturacion.misc;

import facturacion.acciones.DarAltaEmpresa;
import facturacion.acciones.DarAltaParticular;
import facturacion.acciones.MostrarMenuClientes;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuAltaClientes implements EjecutaOpcion {

    VOLVER("Volver", new MostrarMenuClientes()),

    DAR_ALTA_EMPRESA("Dar de alta una nueva empresa", new DarAltaEmpresa()),
    DAR_ALTA_PARTICULAR("Dar de alta un nuevo particular", new DarAltaParticular());

    private String descripcion;
    private EjecutaOpcion ejecutaOpcion;

    MenuAltaClientes(String descripcion, EjecutaOpcion ejecutaOpcion) {
        this.descripcion = descripcion;
        this.ejecutaOpcion = ejecutaOpcion;
    }

    public static MenuAltaClientes opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_CLIENTES);
        for (MenuAltaClientes opcion : values()) {
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

    @Override
    public void ejecuta(Gestion gestion) {
        ejecutaOpcion.ejecuta(gestion);
    }
}
