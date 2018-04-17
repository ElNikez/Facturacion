package facturacion.acciones;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.*;

public class DarAltaParticular implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);
        String nombre = consola.pedirDatos(INTRODUCE_NOMBRE);
        String apellidos = consola.pedirDatos(INTRODUCE_APELLIDOS);
        String correoElectronico = consola.pedirDatos(INTRODUCE_CORREO);
        int codigoPostal = Integer.parseInt(consola.pedirDatos(INTRODUCE_COD_POSTAL));
        String poblacion = consola.pedirDatos(INTRODUCE_POBLACION);
        String provincia = consola.pedirDatos(INTRODUCE_PROVINCIA);

        Cliente cliente = factoriaClientes.crearParticular(nif, nombre, apellidos, correoElectronico, new Direccion(codigoPostal, poblacion, provincia), factoriaTarifas.tarifaBasica());

        try {
            gestion.darDeAltaCliente(cliente);
            consola.mostrarDatos(CLIENTE_DAR_DE_ALTA);
        } catch (ClienteYaExiste clienteYaExiste) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
