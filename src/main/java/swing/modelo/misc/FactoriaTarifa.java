package swing.modelo.misc;

import swing.modelo.facturas.Tarifa;
import swing.modelo.facturas.TarifaBasica;
import swing.modelo.facturas.TipoPromocion;

public interface FactoriaTarifa {

    TarifaBasica crearTarifa();

    Tarifa añadirPromocion(Tarifa tarifa, TipoPromocion promocion);

}
