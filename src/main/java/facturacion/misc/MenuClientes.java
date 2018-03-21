package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuClientes {

    VOLVER("Volver"),

    DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente"),
    DAR_BAJA_CLIENTE("Borrar un cliente"),
    CAMBIAR_TARIFA_CLIENTE("Cambiar la tarifa de un cliente"),
    MOSTRAR_DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF"),
    MOSTRAR_LISTA_CLIENTES("Recuperar el listado de todos los clientes"),
    MOSTRAR_CLIENTES_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas");
//    DAR_ALTA_EMPRESA("Dar de alta una nueva empresa"),
//    DAR_ALTA_PARTICULAR("Dar de alta un nuevo particular");

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
