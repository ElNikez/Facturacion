package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTE_NO_LLAMADAS;

public class ClienteNoLlamadas extends Exception {

    public ClienteNoLlamadas() {
        super(CLIENTE_NO_LLAMADAS.mostrarMensaje());
    }

}
