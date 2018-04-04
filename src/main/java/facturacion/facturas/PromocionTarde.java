package facturacion.facturas;

import java.util.Calendar;

public class PromocionTarde extends Promocion {

    private Tarifa tarifa;

    public PromocionTarde(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        if (llamada.getFecha().after(Calendar.HOUR_OF_DAY == 16) && llamada.getFecha().before(Calendar.HOUR_OF_DAY == 20))
//        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) > 16 && llamada.getFecha().get(Calendar.HOUR_OF_DAY) < 20)
            return llamada.getDuracionDeLlamada() * super.precio();
        else
            return tarifa.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", tardes reducidas";
    }
}
