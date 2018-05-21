package swing.controlador;

import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.*;
import swing.modelo.facturas.Factura;
import swing.modelo.facturas.Llamada;

import java.util.Collection;

public interface GestionParaVista {

    boolean existeCliente(String nif);

    boolean hayClientes();

    boolean clienteConLlamadas(String nif);

    boolean clienteConFacturas(String nif);

    boolean existeFactura(int codigo);

    Cliente mostrarCliente(String nif) throws ClienteNoEncontrado;

    Collection<Cliente> listarClientes() throws ListaClientesVacio;

    Collection<Llamada> listarLlamadas(String nif) throws ClienteNoEncontrado, ClienteNoLlamadas;

    Factura mostrarFactura(int codigo) throws ListaFacturasVacia, FacturaNoEncontrada;

    Collection<Factura> listarFacturas(String nif) throws ClienteNoEncontrado, ClienteNoFacturas;

    void cargarDatos();

    void guardarDatos();

}
