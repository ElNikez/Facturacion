package facturacion.excepciones;

import static facturacion.misc.Mensaje.CLIENTE_NO_FACTURAS;

public class ClienteNoFacturas extends Exception {

    public ClienteNoFacturas() {
        super(CLIENTE_NO_FACTURAS.mostrarMensaje());
    }

}
