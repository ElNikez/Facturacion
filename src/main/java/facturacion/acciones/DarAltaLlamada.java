package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.*;

public class DarAltaLlamada implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        int numeroDeTelefono = Integer.parseInt(consola.pedirDatos(INTRODUCE_TELEFONO));
        int duracionDeLlamada = Integer.parseInt(consola.pedirDatos(INTRODUCE_DURACION));

        try {
            gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
            consola.mostrarDatos(LLAMADA_CREADA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
