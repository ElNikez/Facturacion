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
        float precioLlamada = llamada.getDuracionDeLlamada() * precio();
        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) >= 16
                && llamada.getFecha().get(Calendar.HOUR_OF_DAY) < 20
                && precioLlamada < super.calcularPrecioLlamada(llamada))
            return precioLlamada;
        else
            return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", tardes reducidas";
    }
}
