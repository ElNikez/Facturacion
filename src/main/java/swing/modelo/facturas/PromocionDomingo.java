package swing.modelo.facturas;

import java.util.Calendar;

public class PromocionDomingo extends Promocion {

    private Tarifa tarifa;

    public PromocionDomingo(Tarifa tarifa, float precioTarifa) {
        super(tarifa, precioTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.duracion() * precio();
        if ((llamada.fecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) &&
                precioLlamada < tarifa.calcularPrecioLlamada(llamada))
            return precioLlamada;

        return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String toString() {
        return super.toString() + ", con domingos a " + Tarifa.PRECIO_DOMINGO + " cént/min";
    }

}
