package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTE_NO_EXISTE;

public class ClienteNoEncontrado extends Exception {

    public ClienteNoEncontrado() {
        super(CLIENTE_NO_EXISTE.mostrarMensaje());
    }

}
