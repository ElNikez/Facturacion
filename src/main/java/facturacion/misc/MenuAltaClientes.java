package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuAltaClientes {

    VOLVER("Volver"),

    DAR_ALTA_EMPRESA("Dar de alta una nueva empresa"),
    DAR_ALTA_PARTICULAR("Dar de alta un nuevo particular");

    private String descripcion;

    MenuAltaClientes(String descripcion) {
        this.descripcion = descripcion;
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

}
