package misc;

public enum MenuPrincipal {

    // CLIENTES
    DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente"),
    DAR_BAJA_CLIENTE("Borrar un cliente"),
    CAMBIAR_TARIFA_CLIENTE("Cambiar la tarifa de un cliente"),
    MOSTRAR_DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF"),
    MOSTRAR_LISTA_CLIENTES("Recuperar el listado de todos los clientes"),

    // LLAMADAS
    DAR_ALTA_LLAMADA("Dar de alta una llamada"),
    MOSTRAR_LISTA_LLAMADAS("Listar las llamadas de un cliente"),

    // FACTURAS
    EMITIR_FACTURA("Emitir una factura para un cliente"),
    MOSTRAR_FACTURA_CODIGO("Recuperar los datos de una factura"),
    MOSTRAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente"),

    // OTROS
    SALIR("Salir");

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
        for(MenuPrincipal opcion : MenuPrincipal.values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
