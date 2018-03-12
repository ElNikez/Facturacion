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

    private Gestion gestion;

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
        consola.mostrarDatos("yet");
        consola.mostrarDatos("11.- Añadir empresa\n");
        consola.mostrarDatos("12.- Añadir particular\n");

        consola.mostrarDatos("Introduce una opción: ");
        int opcion2 = Integer.parseInt(consola.pedirDatos());
        switch(opcion2) {
            case 11:
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
                break;
            case 12:
                consola.mostrarDatos("Introduce el NIF: ");
                String nif2 = consola.pedirDatos();
                consola.mostrarDatos("Introduce el nombre: ");
                String nombre2 = consola.pedirDatos();
                consola.mostrarDatos("Introduce los apellidos: ");
                String apellidos = consola.pedirDatos();
                consola.mostrarDatos("Introduce el correo electrónico: ");
                String correoElectronico2 = consola.pedirDatos();
                consola.mostrarDatos("Introduce el código postal: ");
                int codpostal2 = Integer.parseInt(consola.pedirDatos());
                consola.mostrarDatos("Introduce la población: ");
                String poblacion2 = consola.pedirDatos();
                consola.mostrarDatos("Introduce la provincia: ");
                String provincia2 = consola.pedirDatos();
                consola.mostrarDatos("Introduce la tarifa: ");
                float tarifa2 = Float.parseFloat(consola.pedirDatos());

                Cliente empresa2 = new Empresa(nif2, nombre2, correoElectronico2, new Direccion(codpostal2, poblacion2, provincia2), new Tarifa(tarifa2));

                Gestion.darDeAltaCliente(empresa2);
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

        String nif = consola.pedirDatos();
        consola.mostrarDatos("Introduce el NIF: ");


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
