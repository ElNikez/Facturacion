package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_FACTURAS;

public enum MenuFacturas {

    VOLVER("Volver"),

    EMITIR_FACTURA("Emitir una factura para un cliente"),
    MOSTRAR_FACTURA_CODIGO("Recuperar los datos de una factura a partir de su código"),
    MOSTRAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente"),
    MOSTRAR_FACTURAS_FECHAS("Mostrar un listado de facturas de un cliente emitidas entre dos fechas");

    private String descripcion;

    MenuFacturas(String descripcion) {
        this.descripcion = descripcion;
    }

    public static MenuFacturas opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_FACTURAS);
        for (MenuFacturas opcion : values()) {
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
