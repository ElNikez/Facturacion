package swing.modelo.facturas;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PromocionFestivo extends Promocion {

    private Tarifa tarifa;

    public PromocionFestivo(Tarifa tarifa, float precioTarifa) {
        super(tarifa, precioTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.duracion() * precio();
        for (Calendar festivo : DiasFestivos.festivos())
            if (llamada.fecha().get(Calendar.DAY_OF_MONTH) == festivo.get(Calendar.DAY_OF_MONTH) &&
                    llamada.fecha().get(Calendar.MONTH) == festivo.get(Calendar.DAY_OF_MONTH) &&
                    precioLlamada < super.calcularPrecioLlamada(llamada))
                return precioLlamada;

        return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String toString() {
        return super.toString() + ", con festivos a " + Tarifa.PRECIO_FESTIVO + " cént/min";
    }

}
