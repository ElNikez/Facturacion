package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class ListarLlamadasEntreFechas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String[] vectorInicio = cadenaInicio.split("/");
        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]), Integer.parseInt(vectorInicio[0]));

        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorFinal = cadenaFinal.split("/");
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2]), Integer.parseInt(vectorFinal[1]), Integer.parseInt(vectorFinal[0]));


        try {
            Collection<Llamada> listaLlamadas = gestion.listarLlamadas(nif);
            Collection<Llamada> listaLlamadasEntreFechas = new GestionEntreFechas<Llamada>().muestraColeccionEntreFechas(listaLlamadas, fechaInicio, fechaFinal);
            for (Llamada llamada : listaLlamadasEntreFechas)
                consola.mostrarDatos(llamada);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            consola.mostrarDatos(CLIENTE_NO_LLAMADAS);
        }
    }

}
