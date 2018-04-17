package facturacion.acciones;

import facturacion.clientes.Cliente;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Collection;

import static facturacion.misc.Mensaje.LISTA_CLIENTES;
import static facturacion.misc.Mensaje.LISTA_CLIENTES_VACIO;

public class ListarClientes implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        try {
            Collection<Cliente> listaClientes = gestion.listarClientes();
            consola.mostrarDatos(LISTA_CLIENTES);
            for (Cliente cliente : listaClientes)
                consola.mostrarDatos(cliente);
        } catch (ListaClientesVacio listaClientesVacio) {
            consola.mostrarDatos(LISTA_CLIENTES_VACIO);
        }
    }

}
