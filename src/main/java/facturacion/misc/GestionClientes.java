package facturacion.misc;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class GestionClientes {

    private Consola consola = new Consola();

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

        if(Gestion.darDeAltaCliente(cliente))
            consola.mostrarDatos(Mensaje.CLIENTE_DAR_DE_ALTA);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_YA_EXISTE);
    }

    public void darDeBajaCliente() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);

        if(Gestion.darDeBajaCliente(nif))
            consola.mostrarDatos(Mensaje.CLIENTE_DAR_DE_BAJA);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);
    }

    public void cambiarTarifa() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
        float tarifa = Float.parseFloat(consola.pedirDatos(Mensaje.INTRODUCE_TARIFA));
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);
//        String nif = consola.pedirDatos();
//        consola.mostrarDatos(mensaje.INTRODUCE_TARIFA);
//        float tarifa = Float.parseFloat(consola.pedirDatos());

        if(Gestion.cambiarTarifa(nif, new Tarifa(tarifa)))
            consola.mostrarDatos(Mensaje.CLIENTE_CAMBIAR_TARIFA);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);
    }

    public void mostrarCliente() {
        String nif = consola.pedirDatos(Mensaje.INTRODUCE_NIF);
//        consola.mostrarDatos(mensaje.INTRODUCE_NIF);
//        String nif = consola.pedirDatos();

        if(Gestion.mostrarCliente(nif) != null)
            consola.mostrarDatos(Gestion.mostrarCliente(nif));
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);
    }

    public void listarClientes() {
        if(Gestion.listarClientes() != null) {
            consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
            for (Cliente cliente : Gestion.listarClientes())
                consola.mostrarDatos("  " + cliente);
        }
        else
            consola.mostrarDatos(Mensaje.CLIENTES_VACIO);
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

        Collection<Cliente> listaClientes = new GestionEntreFechas<Cliente>().muestraColeccionEntreFechas(Gestion.listarClientes(), fechaInicio, fechaFinal);
        consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
        for(Cliente cliente : listaClientes)
            consola.mostrarDatos("  " + cliente);
    }

}
