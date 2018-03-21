package facturacion.excepciones;

import static facturacion.misc.Mensaje.FACTURAS_VACIO;

public class ListaFacturasVacio extends Exception {

    public ListaFacturasVacio() {
        super(FACTURAS_VACIO.mostrarMensaje());
    }

}
