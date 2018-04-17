package facturacion.misc;

import facturacion.acciones.*;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.MENU_FACTURAS;

public enum MenuFacturas implements EjecutaOpcion {

    VOLVER("Volver", new MostrarMenuPrincipal()),

    EMITIR_FACTURA("Emitir una factura para un cliente", new EmitirFactura()),
    MOSTRAR_FACTURA_CODIGO("Recuperar los datos de una factura a partir de su código", new ObtenerFacturaPorCodigo()),
    MOSTRAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente", new ListarFacturasCliente()),
    MOSTRAR_FACTURAS_FECHAS("Mostrar un listado de facturas de un cliente emitidas entre dos fechas", new ListarFacturasEntreFechas());

    private String descripcion;
    private EjecutaOpcion ejecutaOpcion;

    MenuFacturas(String descripcion, EjecutaOpcion ejecutaOpcion) {
        this.descripcion = descripcion;
        this.ejecutaOpcion = ejecutaOpcion;
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

    @Override
    public void ejecuta(Gestion gestion) {
        ejecutaOpcion.ejecuta(gestion);
    }
}
