package facturacion.gestores;

import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;
import facturacion.misc.Mensaje;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class GestionLlamadas {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestionLlamadas(Gestion gestion) {
        this.gestion = gestion;
    }

    public void darDeAltaLlamada() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_TELEFONO);
        int numeroDeTelefono = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_DURACION);
        int duracionDeLlamada = Integer.parseInt(consola.pedirDatos());

        gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
    }

    public void listarLlamadas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();

        consola.mostrarDatos(Mensaje.LISTA_LLAMADAS);
        for (Llamada llamada : gestion.listarLlamadas(nif))
            consola.mostrarDatos(llamada);
    }

    public void listarLlamadasEntreFechas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        String cadenaInicio = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_FINAL);
        String cadenaFinal = consola.pedirDatos();
        String[] vectorInicio = cadenaInicio.split("/");
        String[] vectorFinal = cadenaFinal.split("/");

        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]) - 1, Integer.parseInt(vectorInicio[0]));
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2] + 1), Integer.parseInt(vectorFinal[1]) - 1, Integer.parseInt(vectorFinal[0]));

        Collection<Llamada> listaLlamadas = new GestionEntreFechas<Llamada>().muestraColeccionEntreFechas(gestion.listarLlamadas(nif), fechaInicio, fechaFinal);
        for(Llamada llamada : listaLlamadas)
            consola.mostrarDatos(llamada);
    }

}
