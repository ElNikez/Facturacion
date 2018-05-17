package swing.controlador;

import swing.modelo.clientes.Direccion;
import swing.modelo.clientes.Empresa;
import swing.modelo.clientes.Particular;
import swing.modelo.excepciones.ClienteNoEncontrado;
import swing.modelo.excepciones.ClienteNoLlamadas;
import swing.modelo.excepciones.ClienteYaExiste;
import swing.modelo.factorias.FactoriaClientes;
import swing.modelo.factorias.FactoriaTarifas;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;
import swing.modelo.facturas.TipoPromocion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Controlador {

    private GestionParaControlador gestion;
    private VistaParaControlador vista;

    private FactoriaClientes factoriaClientes = new FactoriaClientes();
    private FactoriaTarifas factoriaTarifas = new FactoriaTarifas();
    private Empresa empresa;
    private Particular particular;
    private String nif, nombre, apellidos, correo, poblacion, provincia;
    private int codigoPostal;
    private Direccion direccion;
    private Tarifa tarifa;

    public void cambiarGestion(GestionParaControlador gestion) {
        this.gestion = gestion;
    }

    public void cambiarVista(VistaParaControlador vista) {
        this.vista = vista;
    }

    public boolean darAltaEmpresa() {
        nif = vista.nif();
        nombre = vista.nombre();
        correo = vista.correoElectronico();
        codigoPostal = vista.codigoPostal();
        poblacion = vista.poblacion();
        provincia = vista.provincia();
        direccion = factoriaClientes.crearDireccion(codigoPostal, poblacion, provincia);
        tarifa = factoriaTarifas.crearTarifa();

        empresa = factoriaClientes.crearEmpresa(nif, nombre, correo, direccion, tarifa);

        try {
            return gestion.darAltaCliente(empresa);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        return false;
    }

    public boolean darAltaParticular() {
        nif = vista.nif();
        nombre = vista.nombre();
        apellidos = vista.apellidos();
        correo = vista.correoElectronico();
        codigoPostal = vista.codigoPostal();
        poblacion = vista.poblacion();
        provincia = vista.provincia();
        direccion = factoriaClientes.crearDireccion(codigoPostal, poblacion, provincia);
        tarifa = factoriaTarifas.crearTarifa();

        particular = factoriaClientes.crearParticular(nif, nombre, apellidos, correo, direccion, tarifa);

        try {
            return gestion.darAltaCliente(particular);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        return false;
    }

    public boolean darBajaCliente() {
        nif = vista.nif();

        try {
            return gestion.darBajaCliente(nif);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        return false;
    }

    public void cambiarTarifa() {
        nif = vista.nif();
        try {
            tarifa = gestion.mostrarCliente(nif).tarifa();
        } catch (ClienteNoEncontrado ex) {
            ex.printStackTrace();
        }

        try {
            switch (vista.opcionTarifa()) {
                case 0: /*"Tarifa básica":*/
                    gestion.cambiarTarifa(nif, factoriaTarifas.crearTarifa());
                case 1: /*"Promoción madrugadas":*/
                    gestion.cambiarTarifa(nif, factoriaTarifas.añadirPromocion(tarifa, TipoPromocion.promocion(0)));
                case 2: /*"Promoción tardes":*/
                    gestion.cambiarTarifa(nif, factoriaTarifas.añadirPromocion(tarifa, TipoPromocion.promocion(1)));
                case 3: /*"Promoción domingos":*/
                    gestion.cambiarTarifa(nif, factoriaTarifas.añadirPromocion(tarifa, TipoPromocion.promocion(2)));
                case 4: /*"Promoción festivos":*/
                    gestion.cambiarTarifa(nif, factoriaTarifas.añadirPromocion(tarifa, TipoPromocion.promocion(3)));
            }
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

    }

    public boolean darAltaLlamada() {
        nif = vista.nif();

        int numeroDeTelefono = Integer.parseInt(vista.numeroTelefono());
        int duracionDeLlamada = Integer.parseInt(vista.duracionLlamada());
        Llamada llamada = new Llamada(numeroDeTelefono, duracionDeLlamada);

        try {
            gestion.darAltaLlamada(nif, llamada);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        return true;
    }

    public void emitirFactura() {
        nif = vista.nif();

        String fecha1 = vista.fechaEmision();
        String fecha2 = vista.fechaFacturacion();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        try {
            fechaEmision.setTime(formatoFecha.parse(fecha1));
            fechaEmision.setTime(formatoFecha.parse(fecha2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

    }

}