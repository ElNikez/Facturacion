package gestion;

import facturas.Factura;
import misc.Consola;

import java.util.Calendar;
import java.util.HashSet;

public class GestionFacturas {

    private Consola consola;

    private Gestion gestion;

    public Factura emitirFactura() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = fechaEmision;
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        return gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
    }

    public Factura mostrarFactura() {
        consola.mostrarDatos("Introduce el código de la factura: ");
        int codigo = Integer.parseInt(consola.pedirDatos());

        return gestion.mostrarFactura(codigo);
    }

    public HashSet<Factura> listarFacturas() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();

        return gestion.listarFacturas(nif);
    }

}
