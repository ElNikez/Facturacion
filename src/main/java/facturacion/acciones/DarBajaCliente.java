package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.CLIENTE_DAR_DE_BAJA;
import static facturacion.misc.Mensaje.CLIENTE_NO_EXISTE;
import static facturacion.misc.Mensaje.INTRODUCE_NIF;

public class DarBajaCliente implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            gestion.darDeBajaCliente(nif);
            consola.mostrarDatos(CLIENTE_DAR_DE_BAJA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
