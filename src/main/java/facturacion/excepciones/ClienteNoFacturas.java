package facturacion.excepciones;

import facturacion.misc.Mensaje;

public class ClienteNoFacturas extends Exception {

        public ClienteNoFacturas() {
            super(Mensaje.CLIENTE_NO_FACTURAS.mostrarMensaje());
        }
}
