package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.CLIENTE_NO_EXISTE;
import static facturacion.misc.Mensaje.INTRODUCE_NIF;

public class ObtenerCliente implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            consola.mostrarDatos(gestion.mostrarCliente(nif));
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
