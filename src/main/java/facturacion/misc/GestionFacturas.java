package facturacion.misc;

import facturacion.facturas.Factura;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;

import java.util.Calendar;
import java.util.Collection;

public class GestionFacturas {

    private Consola consola = new Consola();

    public void emitirFactura() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int dia = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int mes = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        int año = Integer.parseInt(consola.pedirDatos());

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.DAY_OF_MONTH, dia);
        fechaFacturacion.set(Calendar.MONTH, mes);
        fechaFacturacion.set(Calendar.YEAR, año);

        Gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
    }

    public void mostrarFactura() {
        consola.mostrarDatos(Mensaje.INTRODUCE_CODIGO);
        int codigo = Integer.parseInt(consola.pedirDatos());

        consola.mostrarDatos(Gestion.mostrarFactura(codigo));
    }

    public void listarFacturas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_NIF);
        String nif = consola.pedirDatos();

        for (Factura factura : Gestion.listarFacturas(nif))
            consola.mostrarDatos(factura);
    }

    public void listarFacturasEntreFechas() {
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

        Collection<Factura> listaFacturas = new GestionEntreFechas<Factura>().muestraColeccionEntreFechas(Gestion.listarFacturas(nif), fechaInicio, fechaFin);
        for (Factura llamada : listaFacturas)
            consola.mostrarDatos(llamada);
    }

}
