package facturacion.gestores;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class GestorLlamadas {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestorLlamadas(Gestion gestion) {
        this.gestion = gestion;
    }

    public void darDeAltaLlamada() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        int numeroDeTelefono = Integer.parseInt(consola.pedirDatos(INTRODUCE_TELEFONO));
        int duracionDeLlamada = Integer.parseInt(consola.pedirDatos(INTRODUCE_DURACION));

        try {
            gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
            consola.mostrarDatos(LLAMADA_CREADA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        }
    }

    public void listarLlamadas() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            Collection<Llamada> listaLlamadas = gestion.listarLlamadas(nif);
            consola.mostrarDatos(LISTA_LLAMADAS);
            for (Llamada llamada : listaLlamadas)
                consola.mostrarDatos(llamada);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            clienteNoLlamadas.getMessage();
        }
    }

    public void listarLlamadasEntreFechas() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorInicio = cadenaInicio.split("/");
        String[] vectorFinal = cadenaFinal.split("/");

        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]) - 1, Integer.parseInt(vectorInicio[0]));
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2] + 1), Integer.parseInt(vectorFinal[1]) - 1, Integer.parseInt(vectorFinal[0]));

        try {
            Collection<Llamada> listaLlamadas = gestion.listarLlamadas(nif);
            Collection<Llamada> listaLlamadasEntreFechas = new GestionEntreFechas<Llamada>().muestraColeccionEntreFechas(listaLlamadas, fechaInicio, fechaFinal);
            for (Llamada llamada : listaLlamadasEntreFechas)
                consola.mostrarDatos(llamada);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            clienteNoLlamadas.getMessage();
        }
    }

}
