package gestion;

import facturas.Llamada;
import misc.Consola;

import java.util.HashSet;
import java.util.Scanner;

public class GestionLlamadas {

    Scanner scanner = new Scanner(System.in);

    public boolean darDeAltaLlamada() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Introduce el número de teléfono: ");
        int numeroDeTelefono = scanner.nextInt();
        System.out.print("Introduce la duración (en ms): ");
        int duracionDeLlamada = scanner.nextInt();

        return Gestion.darDeAltaLlamada(nif, new Llamada(numeroDeTelefono, duracionDeLlamada));
    }

    public HashSet<Llamada> listarLlamadas() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();

        return Gestion.listarLlamadas(nif);
    }

}
