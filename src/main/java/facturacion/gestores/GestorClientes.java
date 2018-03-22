package facturacion.gestores;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class GestorClientes {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestorClientes(Gestion gestion) {
        this.gestion = gestion;
    }

    public void darDeAltaCliente(boolean particular) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String nombre = consola.pedirDatos(INTRODUCE_NOMBRE);
        String apellidos = particular ? consola.pedirDatos(INTRODUCE_APELLIDOS) : null;
        String correoElectronico = consola.pedirDatos(INTRODUCE_CORREO);
        int codigoPostal = Integer.parseInt(consola.pedirDatos(INTRODUCE_COD_POSTAL));
        String poblacion = consola.pedirDatos(INTRODUCE_POBLACION);
        String provincia = consola.pedirDatos(INTRODUCE_PROVINCIA);
        float tarifa = Float.parseFloat(consola.pedirDatos(INTRODUCE_TARIFA));

        Cliente cliente;
        if (particular)
            cliente = new Particular(nif, nombre, apellidos, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), new Tarifa(tarifa));
        else
            cliente = new Empresa(nif, nombre, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), new Tarifa(tarifa));

        try {
            gestion.darDeAltaCliente(cliente);
            consola.mostrarDatos(CLIENTE_DAR_DE_ALTA);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }
    }

    public void darDeBajaCliente() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            gestion.darDeBajaCliente(nif);
            consola.mostrarDatos(CLIENTE_DAR_DE_BAJA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        }
    }

    public void cambiarTarifa() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        float tarifa = Float.parseFloat(consola.pedirDatos(INTRODUCE_TARIFA));

        try {
            gestion.cambiarTarifa(nif, new Tarifa(tarifa));
            consola.mostrarDatos(CLIENTE_CAMBIAR_TARIFA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        }
    }

    public void mostrarCliente() {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        try {
            consola.mostrarDatos(gestion.mostrarCliente(nif));
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.getMessage();
        }
    }

    public void listarClientes() {
        try {
            Collection<Cliente> listaClientes = gestion.listarClientes();
            consola.mostrarDatos(LISTA_CLIENTES);
            for (Cliente cliente : listaClientes)
                consola.mostrarDatos(cliente);
        } catch (ListaClientesVacio listaClientesVacio) {
            listaClientesVacio.getMessage();
        }
    }

    public void listarClientesEntreFechas() {
        String cadenaInicio = consola.pedirDatos(INTRODUCE_FECHA_INICIO);
        String cadenaFinal = consola.pedirDatos(INTRODUCE_FECHA_FINAL);
        String[] vectorInicio = cadenaInicio.split("/");
        String[] vectorFinal = cadenaFinal.split("/");

        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]) - 1, Integer.parseInt(vectorInicio[0]));
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2] + 1), Integer.parseInt(vectorFinal[1]) - 1, Integer.parseInt(vectorFinal[0]));

        try {
            Collection<Cliente> listaClientes = gestion.listarClientes();
            Collection<Cliente> listaClientesEntreFechas = new GestionEntreFechas<Cliente>().muestraColeccionEntreFechas(listaClientes, fechaInicio, fechaFinal);
            consola.mostrarDatos(LISTA_CLIENTES);
            for (Cliente cliente : listaClientesEntreFechas)
                consola.mostrarDatos(cliente);
        } catch (ListaClientesVacio listaClientesVacio) {
            listaClientesVacio.printStackTrace();
        }
    }

}
