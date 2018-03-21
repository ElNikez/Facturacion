package facturacion.gestores;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoFacturas;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.excepciones.FacturaNoEncontrada;
import facturacion.facturas.Factura;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class GestorFacturas {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestorFacturas(Gestion gestion) {
        this.gestion = gestion;
    }

    public void emitirFactura() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String cadenaFacturacion = consola.pedirDatos(INTRODUCE_FECHA);
        String[] vectorFacturacion = cadenaFacturacion.split("/");

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = new GregorianCalendar(Integer.parseInt(vectorFacturacion[2]), Integer.parseInt(vectorFacturacion[1]) - 1, Integer.parseInt(vectorFacturacion[0]));

        try {
            gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            clienteNoLlamadas.getMessage();
        }
    }

    public void mostrarFactura() {
        int codigo = Integer.parseInt(consola.pedirDatos(INTRODUCE_CODIGO));

        try {
            consola.mostrarDatos(gestion.mostrarFactura(codigo));
        } catch (FacturaNoEncontrada facturaNoEncontrada) {
            facturaNoEncontrada.getMessage();
        }
    }

    public void listarFacturas() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            Collection<Factura> listaFacturas = gestion.listarFacturas(nif);
            consola.mostrarDatos(LISTA_FACTURAS);
            for (Factura factura : listaFacturas)
                consola.mostrarDatos(factura);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        } catch (ClienteNoFacturas clienteNoFacturas) {
            clienteNoFacturas.getMessage();
        }
    }

    public void listarFacturasEntreFechas() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorInicio = cadenaInicio.split("/");
        String[] vectorFinal = cadenaFinal.split("/");

        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]) - 1, Integer.parseInt(vectorInicio[0]));
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2] + 1), Integer.parseInt(vectorFinal[1]) - 1, Integer.parseInt(vectorFinal[0]));

        try {
            Collection<Factura> listaFacturas = gestion.listarFacturas(nif);
            Collection<Factura> listaFacturasEntreFechas = new GestionEntreFechas<Factura>().muestraColeccionEntreFechas(listaFacturas, fechaInicio, fechaFinal);
            consola.mostrarDatos(LISTA_FACTURAS);
            for (Factura factura : listaFacturasEntreFechas)
                consola.mostrarDatos(factura);
        } catch (ClienteNoFacturas clienteNoFacturas) {
            clienteNoFacturas.getMessage();
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        }
    }

}
