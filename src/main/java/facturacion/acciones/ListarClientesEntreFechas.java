package facturacion.acciones;

import facturacion.clientes.Cliente;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.interfaces.EjecutaOpcion;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class ListarClientesEntreFechas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String[] vectorInicio = cadenaInicio.split("/");
        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]), Integer.parseInt(vectorInicio[0]));

        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorFinal = cadenaFinal.split("/");
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2]), Integer.parseInt(vectorFinal[1]), Integer.parseInt(vectorFinal[0]));

        try {
            Collection<Cliente> listaClientes = gestion.listarClientes();
            Collection<Cliente> listaClientesEntreFechas = new GestionEntreFechas<Cliente>().muestraColeccionEntreFechas(listaClientes, fechaInicio, fechaFinal);
            consola.mostrarDatos(LISTA_CLIENTES);
            for (Cliente cliente : listaClientesEntreFechas)
                consola.mostrarDatos(cliente);
        } catch (ListaClientesVacio listaClientesVacio) {
            consola.mostrarDatos(LISTA_CLIENTES_VACIO);
        }
    }

}
