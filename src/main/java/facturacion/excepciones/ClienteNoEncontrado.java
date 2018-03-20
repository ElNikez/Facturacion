package facturacion.excepciones;

import facturacion.misc.Mensaje;

public class ClienteNoEncontrado extends Exception {

    public ClienteNoEncontrado() {
        super(Mensaje.CLIENTE_NO_EXISTE.mostrarMensaje());
    }



}
