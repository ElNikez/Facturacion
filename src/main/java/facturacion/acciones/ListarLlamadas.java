package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Collection;

import static facturacion.misc.Mensaje.*;

public class ListarLlamadas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            Collection<Llamada> listaLlamadas = gestion.listarLlamadas(nif);
            consola.mostrarDatos(LISTA_LLAMADAS);
            for (Llamada llamada : listaLlamadas)
                consola.mostrarDatos(llamada);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            consola.mostrarDatos(CLIENTE_NO_LLAMADAS);
        }
    }

}
