package gestion;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import facturas.Tarifa;
import misc.Consola;
import misc.MenuClientes;

import java.util.Collection;
import java.util.Scanner;

public class GestionClientes {

    private Consola consola;

    public void darDeAltaCliente() {
//        consola.mostrarDatos(MenuClientes.mostrarMenu());
//        int opcion = Integer.parseInt(consola.pedirDatos("opción"));
//        switch (opcion) {
//            case 1:
//                darDeAltaEmpresa();
//                break;
//            case 2:
//                darDeAltaParticular();
//                break;
//            default:
//                break;
//        }
        consola.mostrarDatos("1.- Añadir empresa\n");
        consola.mostrarDatos("1.- Añadir partocilar\n");

        consola.mostrarDatos("Introduce una opción: ");
        int opcion = Integer.parseInt(consola.pedirDatos());
        switch(opcion) {
            case 1:
                darDeAltaEmpresa();
                break;
            case 2:
                darDeAltaParticular();
                break;
            default:
                break;
        }
    }

    public void darDeAltaEmpresa() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();
        consola.mostrarDatos("Introduce el nombre: ");
        String nombre = consola.pedirDatos();
        consola.mostrarDatos("Introduce el correo electrónico: ");
        String correoElectronico = consola.pedirDatos();
        consola.mostrarDatos("Introduce el código postal: ");
        int codpostal = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos("Introduce la población: ");
        String poblacion = consola.pedirDatos();
        consola.mostrarDatos("Introduce la provincia: ");
        String provincia = consola.pedirDatos();
        consola.mostrarDatos("Introduce la tarifa: ");
        float tarifa = Float.parseFloat(consola.pedirDatos());

        Cliente empresa = new Empresa(nif, nombre, correoElectronico, new Direccion(codpostal, poblacion, provincia), new Tarifa(tarifa));

        Gestion.darDeAltaCliente(empresa);
    }

    private void darDeAltaParticular() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();
        consola.mostrarDatos("Introduce el nombre: ");
        String nombre = consola.pedirDatos();
        consola.mostrarDatos("Introduce los apellidos: ");
        String apellidos = consola.pedirDatos();
        consola.mostrarDatos("Introduce el correo electrónico: ");
        String correoElectronico = consola.pedirDatos();
        consola.mostrarDatos("Introduce el código postal: ");
        int codpostal = Integer.parseInt(consola.pedirDatos());
        consola.mostrarDatos("Introduce la población: ");
        String poblacion = consola.pedirDatos();
        consola.mostrarDatos("Introduce la provincia: ");
        String provincia = consola.pedirDatos();
        consola.mostrarDatos("Introduce la tarifa: ");
        float tarifa = Float.parseFloat(consola.pedirDatos());

        Cliente empresa = new Empresa(nif, nombre, correoElectronico, new Direccion(codpostal, poblacion, provincia), new Tarifa(tarifa));

        Gestion.darDeAltaCliente(empresa);
    }

    public void darDeBajaCliente() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();

        Gestion.darDeBajaCliente(nif);
    }

    public void cambiarTarifa() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();
        consola.mostrarDatos("Introduce la nueva tarifa: ");
        float tarifa = Float.parseFloat(consola.pedirDatos());

        Gestion.cambiarTarifa(nif, new Tarifa(tarifa));
    }

    public void mostrarCliente() {
        consola.mostrarDatos("Introduce el NIF: ");
        String nif = consola.pedirDatos();

        consola.mostrarDatos(Gestion.mostrarCliente(nif));
    }

    public void listarClientes() {
        Collection<Cliente> lista = Gestion.listarClientes();
        for (Cliente cliente : lista)
            consola.mostrarDatos(cliente.toString());
    }

}
