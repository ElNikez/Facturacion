package facturacion.misc;

import facturacion.facturas.Llamada;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;

import java.util.Calendar;
import java.util.Collection;

public class GestionLlamadas {

    private Consola consola = new Consola();

    public void darDeAltaLlamada() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_TELEFONO);
        int numeroDeTelefono = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_DURACION);
        int duracionDeLlamada = Integer.parseInt(consola.pedirDatos());

        Gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
    }

    public void listarLlamadas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();

        for (Llamada llamada : Gestion.listarLlamadas(nif))
            consola.mostrarDatos(llamada);
    }

    public void listarLlamadasEntreFechas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int diaInicio = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int mesInicio = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int añoInicio = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_FINAL);
        int diaFin = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_FINAL);
        int mesFin = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_FINAL);
        int añoFin = Integer.parseInt(consola.pedirDatos());
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(Calendar.DAY_OF_MONTH, diaInicio);
        fechaInicio.set(Calendar.MONTH, mesInicio);
        fechaInicio.set(Calendar.YEAR, añoInicio);
        fechaFin.set(Calendar.DAY_OF_MONTH, diaFin);
        fechaFin.set(Calendar.MONTH, mesFin);
        fechaFin.set(Calendar.YEAR, añoFin);

        Collection<Llamada> listaLlamadas = new GestionEntreFechas<Llamada>().muestraColeccionEntreFechas(Gestion.listarLlamadas(nif), fechaInicio, fechaFin);
        for(Llamada llamada : listaLlamadas)
            consola.mostrarDatos(llamada);
    }

}
