package facturacion.gestion;

import facturacion.clientes.Cliente;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;

import java.util.Calendar;


public interface VistaGestionParaControlador {

    boolean darDeBajaCliente(String nif)throws ClienteNoEncontrado;
    boolean darDeAltaCliente(Cliente cliente)throws ClienteYaExiste;
    boolean cambiarTarifa(String nif, Tarifa nuevaTarifa) throws ClienteNoEncontrado;
    boolean darDeAltaLlamada(String nif, Llamada llamada) throws ClienteNoEncontrado;
    boolean emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) throws ClienteNoEncontrado, ClienteNoLlamadas;

}
