package facturacion.facturas;

import java.util.Calendar;

public class PromocionDomingos extends Promocion {

    private Tarifa tarifa;

    public PromocionDomingos(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    public float calcularPrecioLlamada(Llamada llamada) {
        return llamada.getFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 0 : tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", domingos gratis";
    }
}
