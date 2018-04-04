package facturacion.interfaces;

import facturacion.facturas.Tarifa;
import facturacion.misc.MenuCambioTarifa;

public interface FactoriaTarifa {

    Tarifa tarifaBasica();

    Tarifa promocion(Tarifa tarifa, MenuCambioTarifa promocion);

}
