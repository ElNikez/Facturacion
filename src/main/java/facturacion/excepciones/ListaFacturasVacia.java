package facturacion.excepciones;

import static facturacion.misc.Mensaje.LISTA_FACTURAS_VACIO;

public class ListaFacturasVacia extends Exception {

    public ListaFacturasVacia() {
        super(LISTA_FACTURAS_VACIO.mostrarMensaje());
    }

}
