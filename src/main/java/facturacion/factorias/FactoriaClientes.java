package facturacion.factorias;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.facturas.Tarifa;

public class FactoriaClientes implements FactoriaCliente {

    @Override
    public Cliente crearEmpresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        Cliente cliente = new Empresa(nif, nombre, correoElectronico, direccion, tarifa);

        return cliente;
    }

    @Override
    public Cliente crearParticular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        Cliente cliente = new Particular(nif, nombre, apellidos, correoElectronico, direccion, tarifa);

        return cliente;
    }

}
