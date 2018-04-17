package facturacion.misc;

import facturacion.acciones.DarAltaLlamada;
import facturacion.acciones.ListarLlamadas;
import facturacion.acciones.ListarLlamadasEntreFechas;
import facturacion.acciones.MostrarMenuPrincipal;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.MENU_LLAMADAS;

public enum MenuLlamadas implements EjecutaOpcion {

    VOLVER("Volver", new MostrarMenuPrincipal()),

    DAR_ALTA_LLAMADA("Dar de alta una llamada", new DarAltaLlamada()),
    MOSTRAR_LISTA_LLAMADAS("Listar todas las llamadas de un cliente", new ListarLlamadas()),
    MOSTRAR_LLAMADAS_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas", new ListarLlamadasEntreFechas());

    private String descripcion;
    private EjecutaOpcion ejecutaOpcion;

    MenuLlamadas(String descripcion, EjecutaOpcion ejecutaOpcion) {
        this.descripcion = descripcion;
        this.ejecutaOpcion = ejecutaOpcion;
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

    @Override
    public void ejecuta(Gestion gestion) {
        ejecutaOpcion.ejecuta(gestion);
    }
}
