package facturacion.misc;

public enum MenuPrincipal {

    SALIR("Salir"),

    // CLIENTES
    DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente"),
    DAR_BAJA_CLIENTE("Borrar un cliente"),
    CAMBIAR_TARIFA_CLIENTE("Cambiar la tarifa de un cliente"),
    MOSTRAR_DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF"),
    MOSTRAR_LISTA_CLIENTES("Recuperar el listado de todos los clientes"),
    MOSTRAR_CLIENTES_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas"),

    // LLAMADAS
    DAR_ALTA_LLAMADA("Dar de alta una llamada"),
    MOSTRAR_LISTA_LLAMADAS("Listar todas las llamadas de un cliente"),
    MOSTRAR_LLAMADAS_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas"),

    // FACTURAS
    EMITIR_FACTURA("Emitir una factura para un cliente"),
    MOSTRAR_FACTURA_CODIGO("Recuperar los datos de una factura a partir de su código"),
    MOSTRAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente"),
    MOSTRAR_FACTURAS_FECHAS("Mostrar un listado de facturas de un cliente emitidas entre dos fechas");

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
        menu.append(Mensaje.MENU_GESTION);
        for(MenuPrincipal opcion : MenuPrincipal.values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
