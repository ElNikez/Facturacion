package misc;

public enum MenuClientes {

    // CLIENTES
    DAR_ALTA_EMPRESA("Dar de alta una empresa"),
    DAR_ALTA_PARTICULAR("Dar de alta un particular"),

    // OTROS
    SALIR("Salir");

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
        for(MenuClientes opcion : MenuClientes.values())
            menu.append(opcion.ordinal() + ".- " + opcion.descripcion() + "\n");

        return menu.toString();
    }

}
