package facturacion.excepciones;

import static facturacion.misc.Mensaje.FACTURAS_VACIO;

public class ListaFacturasVacia extends Exception {

    public ListaFacturasVacia() {
        super(FACTURAS_VACIO.mostrarMensaje());
    }

}
