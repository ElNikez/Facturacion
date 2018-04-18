package facturacion.facturas;

import java.util.Calendar;

public class PromocionDomingos extends Promocion {

    private Tarifa tarifa;

    public PromocionDomingos(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        float precioLlamada = llamada.getDuracionDeLlamada() * precio();
        if (llamada.getFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                && precioLlamada < super.calcularPrecioLlamada(llamada))
            return precioLlamada;
        else
            return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", domingos gratis";
    }
}
