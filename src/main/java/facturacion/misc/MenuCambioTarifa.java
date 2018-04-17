package facturacion.misc;

import static facturacion.misc.Mensaje.MENU_CLIENTES;

public enum MenuCambioTarifa {

    VOLVER("Volver"),
    TARIFA_BASICA("Tarifa básica"),
    TARIFA_MADRUGADA("Madrugadas extra-reducidas"),
    TARIFA_TARDE("Tardes reducidas"),
    TARIFA_DOMINGO("Domingos gratis"),
    TARIFA_FESTIVO("Festivos gratis");

    private String descripcion;

    MenuCambioTarifa(String descripcion) {
        this.descripcion = descripcion;
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

}
