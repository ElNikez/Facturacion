package swing.controlador;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;

import java.text.ParseException;
import java.util.Calendar;

public interface VistaControlador {

    boolean darDeAltaParticular();
    boolean darDeAltaEmpresa();
    boolean darDeBaja()throws ClienteNoEncontrado;
    boolean cambiarTarifa() throws ClienteNoEncontrado;
    boolean darDeAltaLlamada() throws ClienteNoEncontrado;
    boolean emitirFactura() throws ClienteNoEncontrado, ClienteNoLlamadas, ParseException;



}
