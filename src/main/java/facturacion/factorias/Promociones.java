package facturacion.factorias;

import static facturacion.misc.Mensaje.MENU_PROMOCIONES;

public enum Promociones {

    TARDES("Tarifa reducida de tardes"),
    DOMINGOS("Tarifa gratuita de domingos");

    private String descripcion;

    Promociones(String descripcion) {
        this.descripcion = descripcion;
    }

    public String descripcion() {
        return descripcion;
    }

    public static Promociones opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_PROMOCIONES);
        for(Promociones opcion : values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
