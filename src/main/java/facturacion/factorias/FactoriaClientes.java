package facturacion.factorias;

import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.facturas.Tarifa;
import facturacion.interfaces.FactoriaCliente;

public class FactoriaClientes implements FactoriaCliente {

    @Override
    public Empresa crearEmpresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        return new Empresa(nif, nombre, correoElectronico, direccion, tarifa);
    }

    @Override
    public Particular crearParticular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        return new Particular(nif, nombre, apellidos, correoElectronico, direccion, tarifa);
    }

}
