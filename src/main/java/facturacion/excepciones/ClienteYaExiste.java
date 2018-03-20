package facturacion.excepciones;

import facturacion.misc.Mensaje;

public class ClienteYaExiste extends Exception {

    public ClienteYaExiste() {
        super(Mensaje.CLIENTE_YA_EXISTE.mostrarMensaje());
    }
}
