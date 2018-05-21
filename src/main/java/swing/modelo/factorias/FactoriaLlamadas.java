package swing.modelo.factorias;

import swing.modelo.facturas.Llamada;
import swing.modelo.misc.FactoriaLlamada;

public class FactoriaLlamadas implements FactoriaLlamada {

    @Override
    public Llamada crearLlamada(int numeroDeTelefono, int duracionDeLlamada) {
        return new Llamada(numeroDeTelefono, duracionDeLlamada);
    }

}
