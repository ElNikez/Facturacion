package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_LLAMADAS;

public enum MenuLlamadas {

    VOLVER("Volver"),

    DAR_ALTA_LLAMADA("Dar de alta una llamada"),
    MOSTRAR_LISTA_LLAMADAS("Listar todas las llamadas de un cliente"),
    MOSTRAR_LLAMADAS_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas");

    private String descripcion;

    MenuLlamadas(String descripcion) {
        this.descripcion = descripcion;
    }

    public static MenuLlamadas opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_LLAMADAS);
        for (MenuLlamadas opcion : values()) {
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
