package swing.controlador;

import facturacion.clientes.Direccion;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.factorias.FactoriaClientes;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import swing.vista.VistaParaControlador;
import swing.vista.VistaPrincipal;

public class Controlador {

    private Gestion gestion;
    private VistaParaControlador vista;
    private FactoriaClientes factoriaClientes = new FactoriaClientes();

    private String NIF;
    private String nombre;
    private String apellidos;
    private int codPostal;
    private String poblacion;
    private String provincia;
    private String correo;
    private Tarifa tarifa;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    public void setVista(VistaPrincipal vista) {
        this.vista = vista;
    }

    public void recuperarDatos() {
        NIF = vista.getNIF();
        nombre = vista.getNombre();
        apellidos = vista.getApellidos();
        codPostal = vista.getCodigoPostal();
        poblacion = vista.getPoblacion();
        provincia = vista.getProvincia();
        correo = vista.getCorreo();
        tarifa = vista.getTarifa();
    }

    public boolean darDeAltaCliente() {
        try {
            if (vista.esEmpresa())
                return gestion.darDeAltaCliente(factoriaClientes.crearEmpresa(NIF, nombre, correo, new Direccion(codPostal, poblacion, provincia), tarifa));
            if(vista.esParticular())
                return gestion.darDeAltaCliente(factoriaClientes.crearParticular(NIF, nombre, apellidos, correo, new Direccion(codPostal, poblacion, provincia), tarifa));
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        return false;
    }

    public boolean darDeBaja(String nif) {

        try {
            gestion.darDeBajaCliente(nif);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
        return true;
    }

}
