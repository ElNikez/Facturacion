package facturacion.excepciones;

import static facturacion.misc.Mensaje.LLAMADAS_VACIO;

public class ListaLlamadasVacia extends Exception {

    public ListaLlamadasVacia() {
        super(LLAMADAS_VACIO.mostrarMensaje());
    }

}
