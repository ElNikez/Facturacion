package gestion;

import facturas.Factura;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;

public class GestionFacturas {

    Scanner scanner = new Scanner(System.in);

    public Factura emitirFactura() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = fechaEmision;
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        return Gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
    }

    public Factura mostrarFactura() {
        System.out.print("Introduce el código de la factura: ");
        int codigo = scanner.nextInt();

        return Gestion.mostrarFactura(codigo);
    }

    public HashSet<Factura> listarFacturas() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();

        return Gestion.listarFacturas(nif);
    }

}
