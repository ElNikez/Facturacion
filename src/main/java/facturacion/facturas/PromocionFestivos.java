package facturacion.facturas;

import facturacion.misc.DiasFestivos;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PromocionFestivos extends Promocion {

    private Tarifa tarifa;

    public PromocionFestivos(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.getDuracionDeLlamada() * precio();
        for (GregorianCalendar fechaFestiva : DiasFestivos.festivos())
            if (llamada.getFecha().get(Calendar.DAY_OF_MONTH) == fechaFestiva.get(Calendar.DAY_OF_MONTH)
                    && llamada.getFecha().get(Calendar.MONTH) == fechaFestiva.get(Calendar.MONTH)
                    && precioLlamada < super.calcularPrecioLlamada(llamada))
                return precioLlamada;

        return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", festivos gratis";
    }
}
