package facturacion.interfaces;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.facturas.Tarifa;
import facturacion.facturas.TarifaBasica;

public interface FactoriaCliente {

    Cliente crearEmpresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa);

    Cliente crearParticular(String nif, String nombre, String apellidos, String correoElectronico, Direccion direccion, Tarifa tarifa);

}
