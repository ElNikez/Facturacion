package facturacion.excepciones;

import static facturacion.misc.Mensaje.FACTURA_NO_EXISTE;

public class FacturaNoEncontrada extends Exception {

    public FacturaNoEncontrada() {
        super(FACTURA_NO_EXISTE.mostrarMensaje());
    }

}
