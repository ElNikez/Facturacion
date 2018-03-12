package gestion;

import facturas.Llamada;
import misc.Consola;

import java.util.HashSet;

public class GestionLlamadas {

    private Consola consola;

    public boolean darDeAltaLlamada() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();
        consola.mostrarDatos("Introduce el número de teléfono: ");
        int numeroDeTelefono = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos("Introduce la duración (en ms): ");
        int duracionDeLlamada = Integer.parseInt(consola.pedirDatos());

        return Gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
    }

    public HashSet<Llamada> listarLlamadas() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();

        return Gestion.listarLlamadas(nif);
    }

}
