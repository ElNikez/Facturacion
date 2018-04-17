package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class EmitirFactura implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String cadenaFacturacion = consola.pedirDatos(INTRODUCE_FECHA);
        String[] vectorFacturacion = cadenaFacturacion.split("/");

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = new GregorianCalendar(Integer.parseInt(vectorFacturacion[2]), Integer.parseInt(vectorFacturacion[1]) - 1, Integer.parseInt(vectorFacturacion[0]));

        try {
            gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
            consola.mostrarDatos(FACTURA_EMITIDA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        } catch (ClienteNoLlamadas clienteNoLlamadas) {
            consola.mostrarDatos(CLIENTE_NO_LLAMADAS);
        }
    }

}
