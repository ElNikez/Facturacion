package facturacion.gestores;

import facturacion.facturas.Factura;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;
import facturacion.misc.Mensaje;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class GestionFacturas {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestionFacturas(Gestion gestion) {
        this.gestion = gestion;
    }

    public void emitirFactura() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA);
        String cadenaFacturacion = consola.pedirDatos();
        String[] vectorFacturacion = cadenaFacturacion.split("/");

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = new GregorianCalendar(Integer.parseInt(vectorFacturacion[2]), Integer.parseInt(vectorFacturacion[1]) - 1, Integer.parseInt(vectorFacturacion[0]));

        gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
    }

    public void mostrarFactura() {
        consola.mostrarDatos(Mensaje.INTRODUCE_CODIGO);
        int codigo = Integer.parseInt(consola.pedirDatos());

        consola.mostrarDatos(gestion.mostrarFactura(codigo));
    }

    public void listarFacturas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();

        consola.mostrarDatos(Mensaje.LISTA_FACTURAS);
        for (Factura factura : gestion.listarFacturas(nif))
            consola.mostrarDatos(factura);
    }

    public void listarFacturasEntreFechas() {
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

        Collection<Factura> listaFacturas = new GestionEntreFechas<Factura>().muestraColeccionEntreFechas(gestion.listarFacturas(nif), fechaInicio, fechaFinal);
        consola.mostrarDatos(Mensaje.LISTA_FACTURAS);
        for (Factura llamada : listaFacturas)
            consola.mostrarDatos(llamada);
    }

}
