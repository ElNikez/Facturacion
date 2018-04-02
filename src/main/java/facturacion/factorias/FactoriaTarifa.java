package facturacion.factorias;

import facturacion.facturas.Tarifa;

public interface FactoriaTarifa {

    Tarifa tarifaBasica();

    Tarifa promocion(Tarifa tarifa, Promociones promocion);

}
