package swing.modelo.misc;

import swing.modelo.facturas.Llamada;

public interface FactoriaLlamada {

    Llamada crearLlamada(int numeroDeTelefono, int duracionDeLlamada);

}
