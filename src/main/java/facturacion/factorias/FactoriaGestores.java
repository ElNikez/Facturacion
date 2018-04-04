package facturacion.factorias;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestorClientes;
import facturacion.gestores.GestorFacturas;
import facturacion.gestores.GestorLlamadas;
import facturacion.interfaces.FactoriaGestor;

public class FactoriaGestores implements FactoriaGestor {

    public GestorClientes gestorClientes(Gestion gestion) {
        return new GestorClientes(gestion);
    }

    public GestorLlamadas gestorLlamadas(Gestion gestion) {
        return new GestorLlamadas(gestion);
    }

    public GestorFacturas gestorFacturas(Gestion gestion) {
        return new GestorFacturas(gestion);
    }
}
