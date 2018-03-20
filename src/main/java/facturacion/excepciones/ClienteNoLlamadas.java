package facturacion.excepciones;

import facturacion.misc.Mensaje;

public class ClienteNoLlamadas extends Exception {

    public ClienteNoLlamadas() {
        super(Mensaje.CLIENTE_NO_LLAMADAS.mostrarMensaje());
    }
}
