package swing.vista;

import facturacion.facturas.Tarifa;

public interface VistaParaControlador {

    String getNIF();

    String getNombre();

    String getApellidos();

    int getCodigoPostal();

    String getPoblacion();

    String getProvincia();

    String getCorreo();

    Tarifa getTarifa();

    boolean esEmpresa();

    boolean esParticular();

}
