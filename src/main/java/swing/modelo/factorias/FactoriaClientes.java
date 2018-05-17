package swing.modelo.factorias;

import swing.modelo.clientes.Direccion;
import swing.modelo.clientes.Empresa;
import swing.modelo.clientes.Particular;
import swing.modelo.facturas.Tarifa;
import swing.modelo.misc.FactoriaCliente;

public class FactoriaClientes implements FactoriaCliente {

    @Override
    public Empresa crearEmpresa(String nif, String nombre, String correo, Direccion direccion, Tarifa tarifa) {
        return new Empresa(nif, nombre, correo, direccion, tarifa);
    }

    @Override
    public Particular crearParticular(String nif, String nombre, String apellidos, String correo, Direccion direccion, Tarifa tarifa) {
        return new Particular(nif, nombre, apellidos, correo, direccion, tarifa);
    }

    @Override
    public Direccion crearDireccion(int codigoPostal, String poblacion, String provincia) {
        return new Direccion(codigoPostal, poblacion, provincia);
    }
}
