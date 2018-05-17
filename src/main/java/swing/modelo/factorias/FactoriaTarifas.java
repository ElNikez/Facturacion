package swing.modelo.factorias;

import swing.modelo.facturas.*;
import swing.modelo.misc.FactoriaTarifa;

public class FactoriaTarifas implements FactoriaTarifa {

    @Override
    public TarifaBasica crearTarifa() {
        return new TarifaBasica(Tarifa.PRECIO_BASICA);
    }

    @Override
    public Tarifa añadirPromocion(Tarifa tarifa, TipoPromocion promocion) {
        Tarifa nuevaTarifa;
        switch (promocion) {
            case MADRUGADA:
                nuevaTarifa = new PromocionMadrugada(tarifa, Tarifa.PRECIO_MADRUGADA);
                break;
            case TARDE:
                nuevaTarifa = new PromocionTarde(tarifa, Tarifa.PRECIO_TARDE);
                break;
            case DOMINGO:
                nuevaTarifa = new PromocionDomingo(tarifa, Tarifa.PRECIO_DOMINGO);
                break;
            case FESTIVO:
                nuevaTarifa = new PromocionFestivo(tarifa, Tarifa.PRECIO_FESTIVO);
                break;
            default:
                nuevaTarifa = new TarifaBasica(Tarifa.PRECIO_BASICA);
        }

        return nuevaTarifa;
    }

}
