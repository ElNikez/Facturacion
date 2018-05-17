package swing.controlador;

import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.ClienteNoEncontrado;
import swing.modelo.excepciones.ClienteNoLlamadas;
import swing.modelo.excepciones.ClienteYaExiste;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;

import java.util.Calendar;

public interface GestionParaControlador {

    boolean darAltaCliente(Cliente cliente) throws ClienteYaExiste;

    boolean darBajaCliente(String nif) throws ClienteNoEncontrado;

    boolean cambiarTarifa(String nif, Tarifa tarifa) throws ClienteNoEncontrado;

    Cliente mostrarCliente(String nif) throws ClienteNoEncontrado;

    boolean darAltaLlamada(String nif, Llamada llamada) throws ClienteNoEncontrado;

    boolean emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) throws ClienteNoEncontrado, ClienteNoLlamadas;

}
