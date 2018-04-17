package facturacion.facturas;

import java.util.Calendar;

public class PromocionTardes extends Promocion {

    private Tarifa tarifa;

    public PromocionTardes(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) > 16 && llamada.getFecha().get(Calendar.HOUR_OF_DAY) < 20)
            return llamada.getDuracionDeLlamada() * precio();
        else
            return tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", tardes reducidas";
    }
}
