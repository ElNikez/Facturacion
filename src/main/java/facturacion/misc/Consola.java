package facturacion.misc;

import java.util.Scanner;

public class Consola {

    private Scanner scanner;

    public Consola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarDatos(Object dato) {
        System.out.print(dato);
    }

    public String pedirDatos() {
        return scanner.nextLine();
    }

    public String pedirDatos(Mensaje mensaje) {
        mostrarDatos(mensaje.mostrarMensaje());

        return scanner.next();
    }

}
