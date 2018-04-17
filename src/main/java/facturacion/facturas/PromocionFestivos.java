package facturacion.facturas;

public class PromocionFestivos extends Promocion {

    private Tarifa tarifa;

    public PromocionFestivos(Tarifa tarifa, float precio) {
        super(tarifa, precio);
        this.tarifa = tarifa;
    }

    public float calcularPrecioLlamada(Llamada llamada) {
        return super.calcularPrecioLlamada(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", festivos gratis";
    }
}
