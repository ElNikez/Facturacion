package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTES_VACIO;

public class ListaClientesVacio extends Exception {

    public ListaClientesVacio() {
        super(CLIENTES_VACIO.mostrarMensaje());
    }

}
