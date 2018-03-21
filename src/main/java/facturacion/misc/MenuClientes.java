package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuClientes {

    VOLVER("Volver"),

    DAR_ALTA_EMPRESA("Dar de alta una nueva empresa"),
    DAR_ALTA_PARTICULAR("Dar de alta un nuevo particular");

    private String descripcion;

    MenuClientes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String descripcion() {
        return descripcion;
    }

    public static MenuClientes opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_CLIENTES);
        for(MenuClientes opcion : MenuClientes.values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
