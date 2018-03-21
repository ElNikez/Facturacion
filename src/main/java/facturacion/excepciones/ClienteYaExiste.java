package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTE_YA_EXISTE;

public class ClienteYaExiste extends Exception {

    public ClienteYaExiste() {
        super(CLIENTE_YA_EXISTE.mostrarMensaje());
    }

}
