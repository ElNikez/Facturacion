package swing.modelo.facturas;

import java.util.Calendar;

public class PromocionTarde extends Promocion {

    private Tarifa tarifa;

    public PromocionTarde(Tarifa tarifa, float precioTarifa) {
        super(tarifa, precioTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.duracion() * precio();
        if (llamada.fecha().get(Calendar.HOUR_OF_DAY) >= 16 &&
                llamada.fecha().get(Calendar.HOUR_OF_DAY) < 20 &&
                precioLlamada < tarifa.calcularPrecioLlamada(llamada))
            return precioLlamada;

        return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String toString() {
        return super.toString() + ", con tardes a " + Tarifa.PRECIO_TARDE + " cént/min";
    }

}
