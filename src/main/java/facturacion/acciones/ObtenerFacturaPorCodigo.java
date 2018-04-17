package facturacion.acciones;

import facturacion.excepciones.FacturaNoEncontrada;
import facturacion.excepciones.ListaFacturasVacia;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.FACTURA_NO_EXISTE;
import static facturacion.misc.Mensaje.INTRODUCE_CODIGO;
import static facturacion.misc.Mensaje.LISTA_FACTURAS_VACIO;

public class ObtenerFacturaPorCodigo implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        int codigo = Integer.parseInt(consola.pedirDatos(INTRODUCE_CODIGO));

        try {
            consola.mostrarDatos(gestion.mostrarFactura(codigo));
        } catch (ListaFacturasVacia listaFacturasVacia) {
            consola.mostrarDatos(LISTA_FACTURAS_VACIO);
        } catch (FacturaNoEncontrada facturaNoEncontrada) {
            consola.mostrarDatos(FACTURA_NO_EXISTE);
        }
    }

}
