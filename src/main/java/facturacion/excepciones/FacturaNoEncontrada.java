package facturacion.excepciones;

import facturacion.misc.Mensaje;

public class FacturaNoEncontrada extends Exception{

    public FacturaNoEncontrada() {
        super(Mensaje.FACTURA_NO_EXISTE.mostrarMensaje());
    }

}
