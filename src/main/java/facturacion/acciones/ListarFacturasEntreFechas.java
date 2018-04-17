package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoFacturas;
import facturacion.facturas.Factura;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class ListarFacturasEntreFechas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String[] vectorInicio = cadenaInicio.split("/");
        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]), Integer.parseInt(vectorInicio[0]));

        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorFinal = cadenaFinal.split("/");
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2]), Integer.parseInt(vectorFinal[1]), Integer.parseInt(vectorFinal[0]));

        try {
            Collection<Factura> listaFacturas = gestion.listarFacturas(nif);
            Collection<Factura> listaFacturasEntreFechas = new GestionEntreFechas<Factura>().muestraColeccionEntreFechas(listaFacturas, fechaInicio, fechaFinal);
            consola.mostrarDatos(LISTA_FACTURAS);
            for (Factura factura : listaFacturasEntreFechas)
                consola.mostrarDatos(factura);
        } catch (ClienteNoFacturas clienteNoFacturas) {
            consola.mostrarDatos(CLIENTE_NO_FACTURAS);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
