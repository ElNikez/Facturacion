package facturacion.factorias;

import facturacion.facturas.*;
import facturacion.interfaces.FactoriaTarifa;
import facturacion.misc.MenuCambioTarifa;

public class FactoriaTarifas implements FactoriaTarifa {

    @Override
    public Tarifa tarifaBasica() {
        return new TarifaBasica(15);
    }

    @Override
    public Tarifa promocion(Tarifa tarifa, MenuCambioTarifa promocion) {
        switch (promocion) {
            case TARIFA_MADRUGADA:
                tarifa = new PromocionMadrugada(tarifa, tarifa.PRECIO_MADRUGADA);
                break;
            case TARIFA_TARDE:
                tarifa = new PromocionTarde(tarifa, tarifa.PRECIO_TARDE);
                break;
            case TARIFA_DOMINGO:
                tarifa = new PromocionDomingo(tarifa, tarifa.PRECIO_DOMINGO);
                break;
        }

        return tarifa;
    }
}
