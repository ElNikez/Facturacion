package facturacion.gestores;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.factorias.FactoriaClientes;
import facturacion.factorias.FactoriaTarifas;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import facturacion.misc.Consola;
import facturacion.misc.MenuCambioTarifa;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static facturacion.misc.Mensaje.*;

public class GestorClientes {

    private Consola consola = new Consola();
    private Gestion gestion;
    private FactoriaClientes factoriaClientes;
    private FactoriaTarifas factoriaTarifas;

    public GestorClientes(Gestion gestion) {
        this.gestion = gestion;
        factoriaClientes = new FactoriaClientes();
        factoriaTarifas = new FactoriaTarifas();
    }

    public void darDeAltaCliente(boolean particular) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String nombre = consola.pedirDatos(INTRODUCE_NOMBRE);
        String apellidos = particular ? consola.pedirDatos(INTRODUCE_APELLIDOS) : null;
        String correoElectronico = consola.pedirDatos(INTRODUCE_CORREO);
        int codigoPostal = Integer.parseInt(consola.pedirDatos(INTRODUCE_COD_POSTAL));
        String poblacion = consola.pedirDatos(INTRODUCE_POBLACION);
        String provincia = consola.pedirDatos(INTRODUCE_PROVINCIA);

        Cliente cliente;
        if (particular)
            cliente = factoriaClientes.crearParticular(nif, nombre, apellidos, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), factoriaTarifas.tarifaBasica());
        else
            cliente = factoriaClientes.crearEmpresa(nif, nombre, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), factoriaTarifas.tarifaBasica());

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

        MenuCambioTarifa opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuCambioTarifa.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuCambioTarifa.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuCambioTarifa.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    break;
                case TARIFA_BASICA:
                    cambiarTarifa(nif, MenuCambioTarifa.TARIFA_BASICA);
                    break;
                case TARIFA_MADRUGADA:
                    cambiarTarifa(nif, MenuCambioTarifa.TARIFA_MADRUGADA);
                    break;
                case TARIFA_TARDE:
                    cambiarTarifa(nif, MenuCambioTarifa.TARIFA_TARDE);
                    break;
                case TARIFA_DOMINGO:
                    cambiarTarifa(nif, MenuCambioTarifa.TARIFA_DOMINGO);
                    break;
            }
        } while (opcionMenu != MenuCambioTarifa.VOLVER);
    }

    private void cambiarTarifa(String nif, MenuCambioTarifa tarifa) {
        try {
            switch (tarifa) {
                case TARIFA_BASICA:
                    gestion.cambiarTarifa(nif, factoriaTarifas.tarifaBasica());
                    break;
                case TARIFA_MADRUGADA:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_MADRUGADA));
                    break;
                case TARIFA_TARDE:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_TARDE));
                    break;
                case TARIFA_DOMINGO:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_DOMINGO));
                    break;
            }
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
