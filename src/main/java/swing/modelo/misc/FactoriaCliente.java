package swing.modelo.misc;

import swing.modelo.clientes.Direccion;
import swing.modelo.clientes.Empresa;
import swing.modelo.clientes.Particular;
import swing.modelo.facturas.Tarifa;

public interface FactoriaCliente {

    Empresa crearEmpresa(String nif, String nombre, String correo, Direccion direccion, Tarifa tarifa);

    Particular crearParticular(String nif, String nombre, String apellidos, String correo, Direccion direccion, Tarifa tarifa);

    Direccion crearDireccion(int codigoPostal, String poblacion, String provincia);

}
