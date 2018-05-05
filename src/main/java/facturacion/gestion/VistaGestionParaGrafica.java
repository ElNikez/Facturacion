package facturacion.gestion;

import facturacion.clientes.Cliente;
import facturacion.excepciones.*;
import facturacion.facturas.Factura;
import facturacion.facturas.Llamada;

import java.util.Collection;
import java.util.HashSet;

public interface VistaGestionParaGrafica {

     Cliente mostrarCliente(String nif)throws ClienteNoEncontrado;
     Collection<Cliente> listarClientes() throws ListaClientesVacio;
     HashSet<Llamada> listarLlamadas(String nif) throws ClienteNoEncontrado, ClienteNoLlamadas;
     Factura mostrarFactura(int codigo)throws ListaFacturasVacia, FacturaNoEncontrada;
     HashSet<Factura> listarFacturas(String nif) throws ClienteNoEncontrado, ClienteNoFacturas;
}
