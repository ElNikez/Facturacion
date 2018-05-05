package swing.controlador;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.excepciones.ClienteYaExiste;

import facturacion.factorias.FactoriaClientes;
import facturacion.facturas.Factura;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;
import facturacion.facturas.TarifaBasica;
import facturacion.gestion.Gestion;
import facturacion.gestion.VistaGestionParaControlador;
import swing.vista.VistaGraficaParaControlador;
import swing.vista.VistaPrincipal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Controlador implements VistaControlador {
    private Gestion gestion;
    private Cliente cliente;
    private FactoriaClientes factoriaClientes= new FactoriaClientes();
    private Direccion direccion;
    private TarifaBasica tarifa1;
    private Particular particular;
    private Empresa empresa;
    private VistaGraficaParaControlador vistaGraficaParaControlador;
    private VistaGestionParaControlador vistaGestionParaControlador;
    private String nif;

    public void setGestion(VistaGestionParaControlador vistaGestionParaControlador){
        this.vistaGestionParaControlador= vistaGestionParaControlador;
    }
    public void setVista(VistaGraficaParaControlador vistaGraficaParaControlador){
        this.vistaGraficaParaControlador = vistaGraficaParaControlador ;
    }
    public boolean darDeAltaParticular(){

        nif = vistaGraficaParaControlador.getNIf() ;
        String nombre= vistaGraficaParaControlador.getNombre();
        String apellidos = vistaGraficaParaControlador.getApellidos();
        String correoElectronico = vistaGraficaParaControlador.getCorreo();
        int codigoPostal = vistaGraficaParaControlador.getCodigoPostal();
        String poblacion = vistaGraficaParaControlador.getPoblacion();
        String provincia = vistaGraficaParaControlador.getProvincia();


        direccion = new Direccion(codigoPostal,poblacion,provincia);

        boolean anyadido=false;

        particular = new Particular(nif,nombre,apellidos,correoElectronico,direccion,new TarifaBasica(Tarifa.PRECIO_BASICA));
        try {
            anyadido=  vistaGestionParaControlador.darDeAltaCliente(particular);

        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }
        return anyadido;
    }
    public boolean darDeAltaEmpresa(){

        String nif = vistaGraficaParaControlador.getNIf() ;
        String nombre= vistaGraficaParaControlador.getNombre();
        String correoElectronico = vistaGraficaParaControlador.getCorreo();
        int codigoPostal = vistaGraficaParaControlador.getCodigoPostal();
        String poblacion = vistaGraficaParaControlador.getPoblacion();
        String provincia = vistaGraficaParaControlador.getProvincia();


        direccion = new Direccion(codigoPostal,poblacion,provincia);

        empresa = new Empresa(nif,nombre,correoElectronico,direccion,new TarifaBasica(Tarifa.PRECIO_BASICA));
        try {
            System.out.println(empresa.getNif());
            vistaGestionParaControlador.darDeAltaCliente(empresa);
            return true;
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        return false;
    }
    public boolean darDeBaja(){
        String nif = vistaGraficaParaControlador.getNIf();
        try {
            vistaGestionParaControlador.darDeBajaCliente(nif);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
        return true;
    }
    public boolean cambiarTarifa() {
        String nif = vistaGraficaParaControlador.getNIf() ;
        String nombreTarifa = vistaGraficaParaControlador.getTarifa();
        try {
            switch (nombreTarifa) {
                case "Tarifa básica":
                    vistaGestionParaControlador.cambiarTarifa(nif, new TarifaBasica(Tarifa.PRECIO_BASICA));
                    return true;
                case "Promoción madrugadas":
                    vistaGestionParaControlador.cambiarTarifa(nif, new TarifaBasica(Tarifa.PRECIO_MADRUGADA));
                    return true;
                case "Promoción tardes":
                    vistaGestionParaControlador.cambiarTarifa(nif, new TarifaBasica(Tarifa.PRECIO_TARDE));
                    return true;
                case "Promoción domingos":
                    vistaGestionParaControlador.cambiarTarifa(nif, new TarifaBasica(Tarifa.PRECIO_DOMINGO));
                    return true;
                case "Promoción festivos":
                    vistaGestionParaControlador.cambiarTarifa(nif, new TarifaBasica(Tarifa.PRECIO_FESTIVO));
                    return true;
                default:
                    return false;
            }
        }catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
        return false;
    }
    public boolean darDeAltaLlamada() throws ClienteNoEncontrado {
        nif = vistaGraficaParaControlador.getNIf();
        int numeroDeTelefono = Integer.parseInt(vistaGraficaParaControlador.getNumeroDeTelefono());
        int duracionDeLlamada= Integer.parseInt(vistaGraficaParaControlador.getDuracionDeLlamada());
        Llamada llamada = new Llamada(numeroDeTelefono,duracionDeLlamada);
        vistaGestionParaControlador.darDeAltaLlamada(nif,llamada);
        return true;
    }

   public boolean emitirFactura() throws ParseException, ClienteNoLlamadas, ClienteNoEncontrado {


        nif= vistaGraficaParaControlador.getNIf();
        String fecha1 = vistaGraficaParaControlador.getFechaEmision();
        String fecha2 = vistaGraficaParaControlador.getFechaFacturacion();

        Calendar fechaEmision = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        fechaEmision.setTime(sdf.parse(fecha1));

        Calendar fechaFacturacion = Calendar.getInstance();
        SimpleDateFormat fechaFa = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        fechaEmision.setTime(sdf.parse(fecha2));
        vistaGestionParaControlador.emitirFactura(nif,fechaFacturacion,fechaEmision);
        return false;
   }




}
