package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuCambioTarifa {

    VOLVER("Volver", 0),
    TARIFA_BASICA("Tarifa básica", 15),
    TARIFA_MADRUGADA("Madrugadas extra-reducidas", 5),
    TARIFA_TARDE("Tardes reducidas", 10),
    TARIFA_DOMINGO("Domingos gratis", 0);

    private String descripcion;
    private float precio;

    MenuCambioTarifa(String descripcion, float precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public static MenuCambioTarifa opcion(int posicion) {
        return values()[posicion];
    }

    public static String mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(MENU_CLIENTES);
        for (MenuCambioTarifa opcion : values()) {
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

    public float precio() {
        return precio;
    }

}
