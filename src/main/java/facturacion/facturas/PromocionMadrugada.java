package facturacion.facturas;

import java.util.Calendar;

public class PromocionMadrugada extends Promocion {

    private Tarifa tarifa;

    public PromocionMadrugada(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) > 0 && llamada.getFecha().get(Calendar.HOUR_OF_DAY) < 4)
            return llamada.getDuracionDeLlamada() * super.precio();
        else
            return tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", madrugadas extra-reducidas";
    }
}
