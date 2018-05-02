package swing.controlador;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;

import facturacion.factorias.FactoriaClientes;
import facturacion.facturas.Tarifa;
import facturacion.facturas.TarifaBasica;
import facturacion.gestion.Gestion;
import swing.vista.VistaParaControlador;

public class Controlador{
    private Gestion gestion;
    private Cliente cliente;
    private FactoriaClientes factoriaClientes= new FactoriaClientes();
    private Direccion direccion;
    private TarifaBasica tarifa1;
    private Particular particular;
    private Empresa empresa;

    public void setGestion(Gestion gestion){
        this.gestion=gestion;
    }

    public boolean darDeAltaParticular(String nif, String nombre,String apellidos, String correoElectronico,String codigoPostal, String poblacion,String provincia){
        int codigoPost = Integer.parseInt(codigoPostal);
        direccion = new Direccion(codigoPost,poblacion,provincia);

        boolean anyadido=false;

        factoriaClientes.crearParticular(nif,nombre,apellidos,correoElectronico,direccion,new TarifaBasica(Tarifa.PRECIO_BASICA));
        try {
            anyadido=  gestion.darDeAltaCliente(particular);

        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }
        return anyadido;
    }

    public boolean darDeAltaEmpresa(String nif, String nombre, String correoElectronico,String codigoPostal, String poblacion,String provincia){
        int codigoPost = Integer.parseInt(codigoPostal);
        direccion = new Direccion(codigoPost,poblacion,provincia);

        empresa = new Empresa(nif,nombre,correoElectronico,direccion,new TarifaBasica(Tarifa.PRECIO_BASICA));
        try {
            System.out.println(empresa.getNif());
            gestion.darDeAltaCliente(empresa);
            return true;
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        return false;
    }

    public boolean darDeBaja(String nif){

        try {
            gestion.darDeBajaCliente(nif);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
        return true;
    }

}
