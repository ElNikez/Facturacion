package gestion;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import facturas.Tarifa;
import misc.Consola;
import misc.MenuClientes;

import java.util.Collection;
import java.util.Scanner;

public class GestionClientes {

    Scanner scanner = new Scanner(System.in);

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
        System.out.print("1.- Añadir empresa\n");
        System.out.print("2.- Añadir partocilar\n");
        System.out.print("Introduce una opción: ");
        int opcion = scanner.nextByte();
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
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Introduce el código postal: ");
        int codpostal = scanner.nextInt();
        System.out.print("Introduce la población: ");
        String poblacion = scanner.nextLine();
        System.out.print("Introduce la provincia: ");
        String provincia = scanner.nextLine();
        System.out.print("Introduce la tarifa: ");
        float tarifa = scanner.nextFloat();

        Cliente empresa = new Empresa(nif, nombre, correoElectronico, new Direccion(codpostal, poblacion, provincia), new Tarifa(tarifa));

        Gestion.darDeAltaCliente(empresa);
    }

    public void darDeAltaParticular() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Introduce el correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Introduce el código postal: ");
        int codpostal = scanner.nextInt();
        System.out.print("Introduce la población: ");
        String poblacion = scanner.nextLine();
        System.out.print("Introduce la provincia: ");
        String provincia = scanner.nextLine();
        System.out.print("Introduce la tarifa: ");
        float tarifa = scanner.nextFloat();

        Cliente empresa = new Particular(nif, nombre, apellidos, correoElectronico, new Direccion(codpostal, poblacion, provincia), new Tarifa(tarifa));

        Gestion.darDeAltaCliente(empresa);
    }

    public void darDeBajaCliente() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();

        Gestion.darDeBajaCliente(nif);
    }

    public void cambiarTarifa() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Introduce la nueva tarifa: ");
        float tarifa = scanner.nextFloat();

        Gestion.cambiarTarifa(nif, new Tarifa(tarifa));
    }

    public void mostrarCliente() {
        System.out.print("Introduce el NIF: ");
        String nif = scanner.nextLine();

        System.out.print(Gestion.mostrarCliente(nif));
    }

    public void listarClientes() {
        Collection<Cliente> lista = Gestion.listarClientes();
        for (Cliente cliente : lista)
            System.out.print(cliente.toString());
    }

}
