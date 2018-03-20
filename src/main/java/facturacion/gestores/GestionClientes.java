package facturacion.gestores;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;
import facturacion.misc.Mensaje;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class GestionClientes {

    private Consola consola = new Consola();
    private Gestion gestion;

    public GestionClientes(Gestion gestion) {
        this.gestion = gestion;
    }

    public void darDeAltaCliente(boolean particular) {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
        String nombre = consola.pedirDatos(Mensaje.INTRODUCE_NOMBRE);
        String apellidos = new String();
        if(particular)
            apellidos = consola.pedirDatos(Mensaje.INTRODUCE_APELLIDOS);
        String correoElectronico = consola.pedirDatos(Mensaje.INTRODUCE_CORREO);
        int codigoPostal = Integer.parseInt(consola.pedirDatos(Mensaje.INTRODUCE_COD_POSTAL));
        String poblacion = consola.pedirDatos(Mensaje.INTRODUCE_POBLACION);
        String provincia = consola.pedirDatos(Mensaje.INTRODUCE_PROVINCIA);
        float tarifa = Float.parseFloat(consola.pedirDatos(Mensaje.INTRODUCE_TARIFA));
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);
//        String nif = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_NOMBRE);
//        String nombre = consola.pedirDatos();
//        String apellidos = new String();
//        if (particular) {
//            consola.mostrarDatos(mensaje.INTRODUCE_APELLIDOS);
//            apellidos = consola.pedirDatos();
//        }
//        consola.mostrarDatos(mensaje.INTRODUCE_CORREO);
//        String correoElectronico = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_COD_POSTAL);
//        int codpostal = Integer.parseInt(consola.pedirDatos());
//        consola.mostrarDatos(mensaje.INTRODUCE_POBLACION);
//        String poblacion = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_PROVINCIA);
//        String provincia = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_TARIFA);
//        float tarifa = Float.parseFloat(consola.pedirDatos());

        Cliente cliente;
        if (particular)
            cliente = new Particular(nif, nombre, apellidos, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), new Tarifa(tarifa));
        else
            cliente = new Empresa(nif, nombre, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), new Tarifa(tarifa));

        gestion.darDeAltaCliente(cliente);
    }

    public void darDeBajaCliente() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);

        gestion.darDeBajaCliente(nif);
    }

    public void cambiarTarifa() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
        float tarifa = Float.parseFloat(consola.pedirDatos(Mensaje.INTRODUCE_TARIFA));
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);
//        String nif = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_TARIFA);
//        float tarifa = Float.parseFloat(consola.pedirDatos());

        gestion.cambiarTarifa(nif, new Tarifa(tarifa));
    }

    public void mostrarCliente() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);
//        String nif = consola.pedirDatos();

        gestion.mostrarCliente(nif);
    }

    public void listarClientes() {
        consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
        for (Cliente cliente : gestion.listarClientes())
            consola.mostrarDatos(cliente);
    }

    public void listarClientesEntreFechas() {
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_INICIO);
        String cadenaInicio = consola.pedirDatos();
        consola.mostrarDatos(Mensaje.INTRODUCE_FECHA_FINAL);
        String cadenaFinal = consola.pedirDatos();
        String[] vectorInicio = cadenaInicio.split("/");
        String[] vectorFinal = cadenaFinal.split("/");

        Calendar fechaInicio = new GregorianCalendar(Integer.parseInt(vectorInicio[2]), Integer.parseInt(vectorInicio[1]) - 1, Integer.parseInt(vectorInicio[0]));
        Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(vectorFinal[2] + 1), Integer.parseInt(vectorFinal[1]) - 1, Integer.parseInt(vectorFinal[0]));

        Collection<Cliente> listaClientes = new GestionEntreFechas<Cliente>().muestraColeccionEntreFechas(gestion.listarClientes(), fechaInicio, fechaFinal);
        consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
        for(Cliente cliente : listaClientes)
            consola.mostrarDatos("  " + cliente);
    }

}
