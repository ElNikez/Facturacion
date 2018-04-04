package facturacion.interfaces;

import facturacion.gestion.Gestion;
import facturacion.gestores.GestorClientes;
import facturacion.gestores.GestorFacturas;
import facturacion.gestores.GestorLlamadas;

public interface FactoriaGestor {

    GestorClientes gestorClientes(Gestion gestion);

    GestorLlamadas gestorLlamadas(Gestion gestion);

    GestorFacturas gestorFacturas(Gestion gestion);

}
