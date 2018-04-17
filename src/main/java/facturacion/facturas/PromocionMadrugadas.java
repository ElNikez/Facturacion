package facturacion.facturas;

import java.util.Calendar;

public class PromocionMadrugadas extends Promocion {

    private Tarifa tarifa;

    public PromocionMadrugadas(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) > 0 && llamada.getFecha().get(Calendar.HOUR_OF_DAY) < 4)
            return llamada.getDuracionDeLlamada() * precio();
        else
            return tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", madrugadas extra-reducidas";
    }
}
