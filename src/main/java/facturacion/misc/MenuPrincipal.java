package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_PRINCIPAL;

public enum MenuPrincipal {

    SALIR("Salir"),

    MENU_CLIENTES("Gestión de clientes"),
    MENU_LLAMADAS("Gestión de llamadas"),
    MENU_FACTURAS("Gestión de facturas");

    private String descripcion;

    MenuPrincipal(String descripcion) {
        this.descripcion = descripcion;
    }

    public String descripcion() {
        return descripcion;
    }

    public static MenuPrincipal opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_PRINCIPAL);
        for(MenuPrincipal opcion : MenuPrincipal.values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
