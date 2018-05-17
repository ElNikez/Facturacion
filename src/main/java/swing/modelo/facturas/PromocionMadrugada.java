package swing.modelo.facturas;

import java.util.Calendar;

public class PromocionMadrugada extends Promocion {

    private Tarifa tarifa;

    public PromocionMadrugada(Tarifa tarifa, float precioTarifa) {
        super(tarifa, precioTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.duracion() * precio();
        if (llamada.fecha().get(Calendar.HOUR_OF_DAY) >= 0 &&
                llamada.fecha().get(Calendar.HOUR_OF_DAY) < 4 &&
                precioLlamada < tarifa.calcularPrecioLlamada(llamada))
            return precioLlamada;

        return tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String toString() {
        return super.toString() + ", con madrugadas a " + Tarifa.PRECIO_MADRUGADA + " cént/min";
    }

}
