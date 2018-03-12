package misc;

import java.util.Scanner;

public class Consola {

    private Scanner scanner;

    public Consola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarDatos(Object dato) {
        System.out.println(dato);
    }

    public String pedirDatos() {
        return scanner.nextLine();
    }

}
