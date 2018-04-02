package facturacion.factorias;

import facturacion.facturas.Tarifa;

public class FactoriaTarifas implements FactoriaTarifa {

    @Override
    public Tarifa tarifaBasica() {
        return new Tarifa();
    }

    @Override
    public Tarifa promocion(Tarifa tarifa, Promociones promocion) {
        return tarifa;
    }
}
