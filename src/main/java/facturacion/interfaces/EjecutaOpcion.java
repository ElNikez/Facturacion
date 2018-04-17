package facturacion.interfaces;

import facturacion.factorias.FactoriaClientes;
import facturacion.factorias.FactoriaTarifas;
import facturacion.gestion.Gestion;
import facturacion.misc.Consola;

public interface EjecutaOpcion {

    Consola consola = new Consola();

    FactoriaClientes factoriaClientes = new FactoriaClientes();

    FactoriaTarifas factoriaTarifas = new FactoriaTarifas();

    void ejecuta(Gestion gestion);
}
