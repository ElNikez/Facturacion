package facturacion.excepciones;

import static facturacion.misc.Mensaje.LISTA_CLIENTES_VACIO;

public class ListaClientesVacio extends Exception {

    public ListaClientesVacio() {
        super(LISTA_CLIENTES_VACIO.mostrarMensaje());
    }

}
