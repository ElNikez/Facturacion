package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoFacturas;
import facturacion.facturas.Factura;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Collection;

import static facturacion.misc.Mensaje.*;

public class ListarFacturasCliente implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            Collection<Factura> listaFacturas = gestion.listarFacturas(nif);
            consola.mostrarDatos(LISTA_FACTURAS);
            for (Factura factura : listaFacturas)
                consola.mostrarDatos(factura);
        } catch (ClienteNoFacturas clienteNoFacturas) {
            consola.mostrarDatos(CLIENTE_NO_FACTURAS);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
