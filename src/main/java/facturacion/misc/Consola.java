package facturacion.misc;

import java.util.Scanner;

public class Consola {

    private Scanner scanner = new Scanner(System.in);

//    public Consola() {
//        scanner = new Scanner(System.in);
//    }

    public void mostrarDatos(Object dato) {
        System.out.print(dato);
    }

    public String pedirDatos(Mensaje mensaje) {
        mostrarDatos(mensaje.mostrarMensaje());

        String cadena = "";
        while(cadena.isEmpty())
            cadena = scanner.nextLine();

        return cadena;
    }

}
