package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTES_VACIO;

public class ListaLlamadasVacia extends Exception {

    public ListaLlamadasVacia() {
        super(CLIENTES_VACIO.mostrarMensaje());
    }

}
